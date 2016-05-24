package org.dziadzi.services.impl;

import org.dziadzi.nodes.Item;
import org.dziadzi.nodes.enums.ItemTypeName;
import org.dziadzi.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by DELL on 2016-05-06.
 */
@RestController
public class DecisionTreeServiceImpl {

	@Autowired
	private ItemService itemService;

	@RequestMapping("tree")
	public String getDecisionTree() {
		try {
			buildDecisionTree();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{}";
	}

	public void buildDecisionTree() throws Exception {
		BufferedReader datafile = readDataFile("learn.txt");

		Instances data = new Instances(datafile);
		data.setClassIndex(data.numAttributes() - 1);
		Classifier model = new J48();
		model.buildClassifier(data);
		List<Item> all = itemService.getAll();
		Integer correct = 0;

		for (Item item : all) {
			ItemTypeName classify = classify(item);
			if (ItemTypeName.FOOD.equals(classify) && item.getContainsFood()) {
				correct++;
			}
			if (ItemTypeName.OTHER.equals(classify) && !item.getContainsFood()) {
				correct++;
			}
		}
		System.out.println((double) correct / (double) all.size());

	}

	private Instance createInstance(String material, Integer weight, Integer height, Integer length,
			Integer width, Boolean isFragile, Boolean hasDate) {
		ArrayList<Attribute> attributeList = newArrayList(
				new Attribute("package", newArrayList("paper", "wrap", "wood", "plastic")),
				new Attribute("weight"), new Attribute("height"), new Attribute("length"),
				new Attribute("width"), new Attribute("isFragile", newArrayList("TRUE", "FALSE")),
				new Attribute("hasDate", newArrayList("TRUE", "FALSE")));
		Instances instances = new Instances("data", attributeList, 1);
		double values[] = new double[instances.numAttributes()];
		values[0] = instances.attribute(0).indexOfValue(material);
		values[1] = weight;
		values[2] = height;
		values[3] = length;
		values[4] = width;
		values[5] = instances.attribute(5).indexOfValue(isFragile.toString().toUpperCase());
		values[6] = instances.attribute(6).indexOfValue(hasDate.toString().toUpperCase());
		Instance instance = new DenseInstance(1, values);
		return instance;
	}

	public BufferedReader readDataFile(String filename) {
		BufferedReader inputReader = null;

		inputReader = new BufferedReader(new InputStreamReader(
				DecisionTreeServiceImpl.class.getClassLoader().getResourceAsStream(filename)));

		return inputReader;
	}

	public ItemTypeName classify(Item item) throws Exception {
		BufferedReader datafile = readDataFile("learn.txt");

		Instances data = new Instances(datafile);
		data.setClassIndex(data.numAttributes() - 1);
		Classifier model = new J48();
		model.buildClassifier(data);

		Instance toClassify = createInstance(item);
		toClassify.setDataset(data);
		double v = model.classifyInstance(toClassify);
		return v == 0 ? ItemTypeName.FOOD : ItemTypeName.OTHER;
	}

	private Instance createInstance(Item item) {
		return createInstance(item.getItemPackage().toString().toLowerCase(), item.getWeight(),
				item.getHeight(), item.getLength(), item.getWidth(), item.getFragile(),
				item.getHasDate());
	}
}

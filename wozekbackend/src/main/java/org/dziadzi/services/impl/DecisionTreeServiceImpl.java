package org.dziadzi.services.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.evaluation.NominalPrediction;
import weka.classifiers.trees.J48;
import weka.core.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by DELL on 2016-05-06.
 */
@RestController
public class DecisionTreeServiceImpl {

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
		BufferedReader datafile = readDataFile("stores.txt");

		Instances data = new Instances(datafile);
		data.setClassIndex(data.numAttributes() - 1);
		Classifier model = new J48();
		model.buildClassifier(data);
		for (Instance instance : data) {
			double v = model.classifyInstance(instance);
			System.out.println(v + instance.toString());
		}

		Instance toClassify = createInstance("paper", 5, 5, 50, 40, true, false);
		toClassify.setDataset(data);
		double v = model.classifyInstance(toClassify);
		System.out.println(v + toClassify.toString());
		System.out.println(model.toString());

		// Calculate overall accuracy of current classifier on all splits
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

	public static BufferedReader readDataFile(String filename) {
		BufferedReader inputReader = null;

		inputReader = new BufferedReader(new InputStreamReader(
				DecisionTreeServiceImpl.class.getClassLoader().getResourceAsStream(filename)));

		return inputReader;
	}

	public static Evaluation classify(Classifier model, Instances trainingSet, Instances testingSet)
			throws Exception {
		Evaluation evaluation = new Evaluation(trainingSet);

		model.buildClassifier(trainingSet);
		evaluation.evaluateModel(model, testingSet);

		return evaluation;
	}

	public static double calculateAccuracy(FastVector predictions) {
		double correct = 0;

		for (int i = 0; i < predictions.size(); i++) {
			NominalPrediction np = (NominalPrediction) predictions.elementAt(i);
			if (np.predicted() == np.actual()) {
				correct++;
			}
		}

		return 100 * correct / predictions.size();
	}

	public static Instances[][] crossValidationSplit(Instances data, int numberOfFolds) {
		Instances[][] split = new Instances[2][numberOfFolds];

		for (int i = 0; i < numberOfFolds; i++) {
			split[0][i] = data.trainCV(numberOfFolds, i);
			split[1][i] = data.testCV(numberOfFolds, i);
		}

		return split;
	}
}

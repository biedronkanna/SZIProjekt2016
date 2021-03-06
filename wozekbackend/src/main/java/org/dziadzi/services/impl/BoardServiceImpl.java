package org.dziadzi.services.impl;

import org.dziadzi.dtos.LocationDto;
import org.dziadzi.nodes.*;
import org.dziadzi.nodes.builders.*;
import org.dziadzi.nodes.enums.ItemPackage;
import org.dziadzi.repositories.ForkLiftRepository;
import org.dziadzi.repositories.LocationRepository;
import org.dziadzi.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.dziadzi.nodes.enums.StorageTypeName.FOOD_STORAGE;
import static org.dziadzi.nodes.enums.StorageTypeName.OTHER_STORAGE;
import static org.dziadzi.nodes.enums.traversal.Direction.N;

/**
 * Created by DELL on 2016-04-15.
 */
@RestController("board")
public class BoardServiceImpl implements BoardService {

	private static final Integer MAX_LONGITUDE = 10;
	private static final Integer MAX_LATITUDE = 10;

	@Autowired
	private LocationService locationService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private StorageService storageService;

	@Autowired
	private ForkLiftRepository forkLiftRepository;

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private DecisionTreeServiceImpl decisionTreeService;

	@Override
	@RequestMapping
	public List<List<LocationDto>> getBoard() {
		List<List<LocationDto>> board = new ArrayList<>();

		if (locationService.count() == 0) {
			setUpBoard();
		}
		for (int i = 0; i < MAX_LATITUDE; i++) {
			List<LocationDto> locationsRow = locationService.getLocationsRow(i);
			board.add(locationsRow);
		}
		return board;
	}

	@Override
	public void setUpBoard() {
		locationService.createLocations(MAX_LONGITUDE, MAX_LATITUDE);
		setupForklift();
		List<Location> storageLocations = locationService
				.getLocations(randomCoordinates(MAX_LONGITUDE), randomCoordinates(MAX_LATITUDE));
		setUpStorages(storageLocations);
		List<Location> difficultLocations = locationService
				.getLocations(randomCoordinates(MAX_LONGITUDE), randomCoordinates(MAX_LATITUDE));
		setUpDifficultToTraverse(difficultLocations);
		setUpItems();
	}

	private void setUpItems() {
		BufferedReader bufferedReader = decisionTreeService.readDataFile("items.txt");
		String line=null;
		try {
			while((line=bufferedReader.readLine())!=null){
				String[] split = line.split(",");
				Item item = ItemBuilder.anItem().withItemPackage(ItemPackage.valueOf(ItemPackage.class, split[0].toUpperCase()))
						.withWeight(Integer.parseInt(split[1]))
						.withHeight(Integer.parseInt(split[2]))
						.withLength(Integer.parseInt(split[3]))
						.withWidth(Integer.parseInt(split[4]))
						.withFragile(Boolean.parseBoolean(split[5]))
						.withHasDate(Boolean.parseBoolean(split[6]))
						.withContainsFood(Boolean.parseBoolean(split[7]))
								.build();
			itemService.createItem(item);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void setUpDifficultToTraverse(List<Location> difficultLocations) {
		for (Location location : difficultLocations) {
			location.setDificultTraverse(true);
			locationRepository.save(location, 0);
		}
	}

	private void setUpStorages(List<Location> storageLocations) {
		for (int i = 0; i < storageLocations.size(); i++) {
			Location location = storageLocations.get(i);
			StorageType type = StorageTypeBuilder.aStorageType()
					.withName(i%2==0? FOOD_STORAGE:OTHER_STORAGE).build();
			Storage storage = StorageBuilder.aStorage().withType(type)
					.build();
			storageService.createStorage(storage, location);
		}
	}

	private List<Integer> randomCoordinates(Integer maxCoordinate) {
		List<Integer> coords = IntStream.range(1, maxCoordinate).boxed()
				.collect(Collectors.toList());
		Collections.shuffle(coords);
		return coords;
	}

	private void setupForklift() {
		Location locationOne = locationService.getLocationEntity(0, 0);
		ForkLift forkLift = ForkLiftBuilder.aForkLift().withLocation(locationOne).withDirection(N)
				.build();
		forkLiftRepository.save(forkLift, 1);
	}
}

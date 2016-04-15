package org.dziadzi.services.impl;

import org.dziadzi.dtos.LocationDto;
import org.dziadzi.nodes.*;
import org.dziadzi.nodes.builders.*;
import org.dziadzi.nodes.enums.ItemTypeName;
import org.dziadzi.nodes.enums.StorageTypeName;
import org.dziadzi.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
	}

	private void setUpStorages(List<Location> storageLocations) {
		for (int i = 0; i < storageLocations.size(); i++) {
			Location location = storageLocations.get(i);
			StorageType type = StorageTypeBuilder.aStorageType()
					.withName(StorageTypeName.FOOD_STORAGE).build();
			Storage storage = StorageBuilder.aStorage().withType(type)
					.withName(type.getName().name() + " " + location.getX() + " "
							+ location.getY())
					.build();
			storageService.createStorage(storage, location);
		}
	}

	private List<Integer> randomCoordinates(Integer maxCoordinate) {
		List<Integer> coords = IntStream.range(0, maxCoordinate).boxed()
				.collect(Collectors.toList());
		Collections.shuffle(coords);
		return coords;
	}

	private void setupForklift() {
		ItemType forkliftType = ItemTypeBuilder.anItemType().withName(ItemTypeName.FORKLIFT)
				.build();
		Item one = ItemBuilder.anItem().withName("forklift one").withType(forkliftType).build();
		Location locationOne = locationService.getLocationEntity(1, 1);
		Item oneCreated = itemService.createItem(one);
		itemService.locateItem(oneCreated, locationOne);
	}
}

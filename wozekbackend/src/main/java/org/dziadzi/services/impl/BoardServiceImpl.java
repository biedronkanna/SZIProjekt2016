package org.dziadzi.services.impl;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.dziadzi.dtos.LocationDto;
import org.dziadzi.nodes.*;
import org.dziadzi.nodes.builders.ItemBuilder;
import org.dziadzi.nodes.builders.ItemTypeBuilder;
import org.dziadzi.nodes.enums.ItemTypeName;
import org.dziadzi.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		List<Location> lcoationsForPackages = locationService
				.getLocations(randomCoordinates(MAX_LONGITUDE), randomCoordinates(MAX_LATITUDE));
		setUpPackages(lcoationsForPackages);
	}

	private List<Integer> randomCoordinates(Integer maxCoordinate) {
		List<Integer> coords = IntStream.rangeClosed(0, maxCoordinate).boxed()
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

package org.dziadzi.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.dziadzi.dtos.LocationDto;
import org.dziadzi.services.BoardService;
import org.dziadzi.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by DELL on 2016-04-15.
 */
public class BoardServiceImpl implements BoardService {

	private static final Integer MAX_LONGITUDE = 10;
	private static final Integer MAX_LATITUDE = 10;

	@Autowired
	private LocationService locationService;

	@Override
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
        for(Integer lon)
	}
}

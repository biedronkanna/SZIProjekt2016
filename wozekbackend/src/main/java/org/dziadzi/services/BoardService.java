package org.dziadzi.services;

import org.dziadzi.dtos.LocationDto;

import java.util.List;

/**
 * Created by DELL on 2016-04-15.
 */
public interface BoardService {

	public List<List<LocationDto>> getBoard();

    public void setUpBoard();
}

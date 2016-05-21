package org.dziadzi.services;

import org.dziadzi.dtos.LocationDto;
import org.dziadzi.nodes.Location;

import java.util.List;

/**
 * Created by DELL on 2016-04-15.
 */
public interface LocationService {
    List<LocationDto> getLocationsRow(Integer y);

    void createLocations(int maxX, int maxY);

    Location getLocationEntity(int x, int y);

    Location findOne(Long aLong);

    void deleteLocations();


    Long count();

    List<Location> getLocations(List<Integer> longitues, List<Integer> lattitudes);
}

package org.dziadzi.services;

import org.dziadzi.dtos.LocationDto;

import java.util.List;

/**
 * Created by DELL on 2016-04-15.
 */
public interface LocationService {
    List<LocationDto> getLocationsRow(Integer latitude);

    void createLocations(int maxLongitude, int maxLatitude);


    void deleteLocations();


    Long count();
}

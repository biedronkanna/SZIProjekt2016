package org.dziadzi.services;

import org.dziadzi.dtos.LocationDto;
import org.dziadzi.nodes.Location;

import java.util.List;

/**
 * Created by DELL on 2016-04-15.
 */
public interface LocationService {
    List<LocationDto> getLocationsRow(Integer latitude);

    void createLocations(int maxLongitude, int maxLatitude);

    Location getLocationEntity(int longitude, int latitude);

    void deleteLocations();


    Long count();

    List<Location> getLocations(List<Integer> longitues, List<Integer> lattitudes);
}

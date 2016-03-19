package org.dziadzi.services.impl;

import org.dziadzi.converters.LocationToDtoConverter;
import org.dziadzi.dtos.LocationDto;
import org.dziadzi.nodes.Location;
import org.dziadzi.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static org.dziadzi.nodes.builders.LocationBuilder.aLocation;

/**
 * Created by kkuc on 2016-03-17.
 */

@RestController("locations")
public class LocationServiceImpl {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationToDtoConverter converter;

    @RequestMapping
    public List<List<LocationDto>> getLocations() {
        int maxLongitude = 10;
        int maxLatitude = 10;
        List<List<LocationDto>> board = new ArrayList<>();
        long count = locationRepository.count();
        if (count == 0) {
            createLocations(maxLongitude, maxLatitude);
        }
        for (int latitude = 0; latitude < maxLatitude; latitude++) {
            List<Location> row = locationRepository.findByLatitude(latitude);
            board.add(row.stream().map(converter).collect(Collectors.toList()));
        }
        return board;
    }

    private void createLocations(int maxLongitude, int maxLatitude) {
        for (int latitude = 0; latitude < maxLatitude; latitude++) {
            for (int longitude = 0; longitude < maxLongitude; longitude++) {
                Location toSave = aLocation().withLongitude(longitude).withLatitude(latitude).build();
                addNeighbours(toSave);
                locationRepository.save(toSave);
            }

        }
    }


    private void addNeighbours(Location toSave) {
        Location left = locationRepository.findByLongitudeAndLatitude(toSave.getLongitude() - 1, toSave.getLatitude());
        Location upperLeft = locationRepository.findByLongitudeAndLatitude(toSave.getLongitude() - 1, toSave.getLatitude() - 1);
        Location upper = locationRepository.findByLongitudeAndLatitude(toSave.getLongitude(), toSave.getLatitude() - 1);
        Location upperRight = locationRepository.findByLongitudeAndLatitude(toSave.getLongitude() + 1, toSave.getLatitude() - 1);
        List<Location> potentialNeighbours = newArrayList(left, upperLeft, upperRight, upper);
        potentialNeighbours.stream().filter(e -> e != null).forEach(e -> toSave.getNeighbours().add(e));

    }


    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteLocations() {
        locationRepository.deleteAll();
    }

    @RequestMapping(value = "count", method = RequestMethod.GET)
    public Long count() {
        return locationRepository.count();
    }


}

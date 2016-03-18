package org.dziadzi.services.impl;

import org.dziadzi.converters.LocationToDtoConverter;
import org.dziadzi.dtos.LocationDto;
import org.dziadzi.nodes.Location;
import org.dziadzi.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kkuc on 2016-03-17.
 */

@RestController
public class LocationServiceImpl {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationToDtoConverter converter;

    @RequestMapping("locations")
    public List<LocationDto> getLocations(){

        Location location = new Location();
        location.setLongitude(1);
        location.setLatitude(new BigDecimal(locationRepository.count()).intValue());
        for(Location l: locationRepository.findAll()){
            location.neighbours.add(l);
        }
        locationRepository.save(location);
        LinkedHashSet<Location> all = (LinkedHashSet<Location>) locationRepository.findAll();
        return all.stream().map(converter).collect(Collectors.toList());
    }


}

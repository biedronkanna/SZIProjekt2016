package org.dziadzi.converters;

import org.dziadzi.dtos.LocationDto;
import org.dziadzi.dtos.builders.LocationDtoBuilder;
import org.dziadzi.nodes.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by kkuc on 2016-03-18.
 */
@Component
public class LocationToDtoConverter implements Function<Location, LocationDto> {
    @Autowired
    private LocationToNeighbourConverter neighbourConverter;

    @Override
    public LocationDto apply(Location location) {
        LocationDto converted = LocationDtoBuilder.aLocationDto().withId(location.getId()).withLatitude(location.getLatitude())
                .withLongitude(location.getLongitude()).withNeighbours(location.neighbours.stream().map(neighbourConverter).collect(Collectors.toSet())).build();

        return converted;
    }
}

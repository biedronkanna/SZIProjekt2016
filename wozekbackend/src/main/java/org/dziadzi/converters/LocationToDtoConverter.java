package org.dziadzi.converters;

import org.dziadzi.dtos.StorageDto;
import org.dziadzi.dtos.LocationDto;
import org.dziadzi.dtos.builders.LocationDtoBuilder;
import org.dziadzi.nodes.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * Created by kkuc on 2016-03-18.
 */
@Component
public class LocationToDtoConverter implements Function<Location, LocationDto> {
    @Autowired
    private ItemToDtoConverter itemToDtoConverter;

    @Override
    public LocationDto apply(Location location) {
        StorageDto convertedItem = itemToDtoConverter.apply(location.getItem());
        LocationDto converted = LocationDtoBuilder.aLocationDto().withId(location.getId()).withY(location.getLatitude())
                .withX(location.getLongitude()).withStorage(convertedItem).build();

        return converted;
    }
}

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
    private StorageToDtoConverter storageToDtoConverter;

    @Override
    public LocationDto apply(Location location) {
        StorageDto convertedItem = storageToDtoConverter.apply(location.getStorage());
        LocationDto converted = LocationDtoBuilder.aLocationDto().withId(location.getId()).withY(location.getY())
                .withX(location.getX()).withStorage(convertedItem).build();

        return converted;
    }
}

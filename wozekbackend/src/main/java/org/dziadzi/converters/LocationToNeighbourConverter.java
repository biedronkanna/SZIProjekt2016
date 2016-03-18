package org.dziadzi.converters;

import org.dziadzi.dtos.NeighbourDto;
import org.dziadzi.dtos.builders.NeighbourDtoBuilder;
import org.dziadzi.nodes.Location;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * Created by kkuc on 2016-03-18.
 */
@Component
public class LocationToNeighbourConverter implements Function<Location,NeighbourDto> {



    @Override
    public NeighbourDto apply(Location location) {
        NeighbourDto converted = NeighbourDtoBuilder.aNeighbourDto().withId(location.getId()).withLatitude(location.getLatitude())
                .withLongitude(location.getLongitude()).build();
        return converted;
    }
}

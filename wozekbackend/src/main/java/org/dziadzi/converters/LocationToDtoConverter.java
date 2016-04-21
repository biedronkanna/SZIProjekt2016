package org.dziadzi.converters;

import org.dziadzi.dtos.*;
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
	@Autowired
	private ForkLiftToDtoConverter forkLiftToDtoConverter;

	@Override
	public LocationDto apply(Location location) {
		StorageDto convertedItem = storageToDtoConverter.apply(location.getStorage());
		ForkLiftDto forkLiftDto = forkLiftToDtoConverter.apply(location.getForkLift());
		LocationDto converted = LocationDtoBuilder.aLocationDto().withForkLift(forkLiftDto)
				.withId(location.getId()).withY(location.getY()).withX(location.getX())
				.withStorage(convertedItem).withDifficultToTraverse(location.getDificultTraverse())
				.build();

		return converted;
	}
}

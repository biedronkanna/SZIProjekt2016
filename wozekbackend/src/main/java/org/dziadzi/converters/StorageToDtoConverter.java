package org.dziadzi.converters;

import org.dziadzi.dtos.StorageTypeDto;
import org.dziadzi.dtos.StorageDto;
import org.dziadzi.dtos.builders.StorageDtoBuilder;
import org.dziadzi.nodes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * Created by DELL on 2016-04-15.
 */
@Component
public class StorageToDtoConverter implements Function<Storage, StorageDto> {
	@Autowired
	private StorageTypeToDtoConverter typeToDtoConverter;

	@Override
	public StorageDto apply(Storage storage) {
		if (storage == null) {
			return null;
		}
		StorageType type = storage.getType();
		StorageTypeDto convertedType = typeToDtoConverter.apply(type);

		return StorageDtoBuilder.aStorageDto().withName(storage.getName()).withId(storage.getId())
				.withType(convertedType).build();
	}
}

package org.dziadzi.converters;

import org.dziadzi.dtos.StorageTypeDto;
import org.dziadzi.dtos.builders.StorageTypeDtoBuilder;
import org.dziadzi.nodes.StorageType;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * Created by DELL on 2016-04-15.
 */
@Component
public class StorageTypeToDtoConverter implements Function<StorageType, StorageTypeDto> {

	@Override
	public StorageTypeDto apply(StorageType storageType) {
		if (storageType == null) {
			return null;
		}
		return StorageTypeDtoBuilder.aStorageTypeDto().withId(storageType.getId())
				.withName(storageType.getName().name()).build();
	}
}

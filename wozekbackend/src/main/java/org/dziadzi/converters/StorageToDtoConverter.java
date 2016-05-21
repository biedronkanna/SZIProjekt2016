package org.dziadzi.converters;

import org.dziadzi.dtos.*;
import org.dziadzi.dtos.builders.StorageDtoBuilder;
import org.dziadzi.nodes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by DELL on 2016-04-15.
 */
@Component
public class StorageToDtoConverter implements Function<Storage, StorageDto> {
	@Autowired
	private StorageTypeToDtoConverter typeToDtoConverter;

	@Autowired
	private ItemToDtoConverter itemToDtoConverter;

	@Override
	public StorageDto apply(Storage storage) {
		if (storage == null) {
			return null;
		}
		StorageType type = storage.getType();
		StorageTypeDto convertedType = typeToDtoConverter.apply(type);
		List<ItemDto> items = null;
		Set<Item> storedItems = storage.getStoredItems();
		if(storedItems !=null){
			items=storedItems.stream().map(itemToDtoConverter).collect(Collectors.toList());
		}

		return StorageDtoBuilder.aStorageDto().withItems(items).withId(storage.getId())
				.withType(convertedType).build();
	}
}

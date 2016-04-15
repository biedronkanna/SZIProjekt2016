package org.dziadzi.converters;

import org.dziadzi.dtos.StorageDto;
import org.dziadzi.dtos.ItemTypeDto;
import org.dziadzi.dtos.builders.StorageDtoBuilder;
import org.dziadzi.nodes.Item;
import org.dziadzi.nodes.ItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * Created by DELL on 2016-04-15.
 */
@Component
public class ItemToDtoConverter implements Function<Item, StorageDto> {
	@Autowired
	private ItemTypeToDtoConverter typeToDtoConverter;

	@Override
	public StorageDto apply(Item item) {
		if (item == null) {
			return null;
		}
		ItemType type = item.getType();
		ItemTypeDto convertedType = typeToDtoConverter.apply(type);

		return StorageDtoBuilder.anStorageDto().withName(item.getName()).withId(item.getId())
				.withType(convertedType).build();
	}
}

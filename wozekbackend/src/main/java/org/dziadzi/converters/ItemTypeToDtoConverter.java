package org.dziadzi.converters;

import org.dziadzi.dtos.ItemTypeDto;
import org.dziadzi.dtos.builders.ItemTypeDtoBuilder;
import org.dziadzi.nodes.ItemType;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * Created by DELL on 2016-04-15.
 */
@Component
public class ItemTypeToDtoConverter implements Function<ItemType, ItemTypeDto> {

	@Override
	public ItemTypeDto apply(ItemType itemType) {
		if (itemType == null) {
			return null;
		}
		return ItemTypeDtoBuilder.anItemTypeDto().withId(itemType.getId())
				.withName(itemType.getName().name()).build();
	}
}

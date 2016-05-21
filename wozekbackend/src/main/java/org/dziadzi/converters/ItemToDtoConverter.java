package org.dziadzi.converters;

import org.dziadzi.dtos.ItemDto;
import org.dziadzi.dtos.builders.ItemDtoBuilder;
import org.dziadzi.nodes.Item;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * Created by DELL on 2016-05-21.
 */
@Component
public class ItemToDtoConverter implements Function<Item, ItemDto> {
	@Override
	public ItemDto apply(Item item) {

		return ItemDtoBuilder.anItemDto().withId(item.getId()).withHasDate(item.getHasDate())
				.withFragile(item.getFragile()).withWidth(item.getWidth())
				.withLength(item.getLength()).withHeight(item.getHeight())
				.withWeight(item.getWeight())
				.withContainsFood(item.getContainsFood())
				.withItemPackage(item.getItemPackage()).build();
	}
}

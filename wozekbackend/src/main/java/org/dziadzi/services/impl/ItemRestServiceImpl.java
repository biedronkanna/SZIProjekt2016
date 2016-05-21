package org.dziadzi.services.impl;

import org.dziadzi.converters.ItemToDtoConverter;
import org.dziadzi.dtos.ItemDto;
import org.dziadzi.dtos.ItemTypeDto;
import org.dziadzi.nodes.Item;
import org.dziadzi.nodes.enums.ItemTypeName;
import org.dziadzi.services.ItemRestService;
import org.dziadzi.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by DELL on 2016-05-21.
 */
@RestController
public class ItemRestServiceImpl implements ItemRestService {

	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemToDtoConverter toDtoConverter;

    @Autowired
    private DecisionTreeServiceImpl decisionTreeService;

	@Override
	@RequestMapping(value = "items", method = RequestMethod.GET)
	public List<ItemDto> getAlItems() {
		List<Item> all = itemService.getAll();

		return all.stream().map(toDtoConverter).collect(Collectors.toList());
	}

	@Override
	@RequestMapping(value = "items/types", method = RequestMethod.GET)
	public ItemTypeDto classifyItem(@RequestParam("itemId") Long itemId) throws Exception {
        Item item = itemService.findOne(itemId);
        ItemTypeName type = decisionTreeService.classify(item);

        return new ItemTypeDto(type);
	}
}

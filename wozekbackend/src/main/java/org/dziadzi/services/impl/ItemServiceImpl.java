package org.dziadzi.services.impl;

import org.dziadzi.nodes.Item;
import org.dziadzi.nodes.Location;
import org.dziadzi.repositories.ItemRepository;
import org.dziadzi.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by DELL on 2016-04-15.
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public Item createItem(Item item) {
		return itemRepository.save(item, 1);
	}

	@Override
	public Item locateItem(Item item, Location location) {
		item.setLocation(location);
		return itemRepository.save(item, 1);
	}
}

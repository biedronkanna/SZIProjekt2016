package org.dziadzi.services;

import org.dziadzi.nodes.Item;
import org.dziadzi.nodes.Location;

import java.util.List;

/**
 * Created by DELL on 2016-04-15.
 */
public interface ItemService {

    Item createItem(Item item);

    Item locateItem(Item item, Location location);

    List<Item> getAll();

    Item findOne(Long itemId);
}

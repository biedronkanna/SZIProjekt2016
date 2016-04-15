package org.dziadzi.nodes.builders;

import org.dziadzi.nodes.*;

/**
 * Created by DELL on 2016-04-15.
 */
public class ItemBuilder {
    private Long id;
    private ItemType type;
    private Location location;
    private String name;

    private ItemBuilder() {
    }

    public static ItemBuilder anItem() {
        return new ItemBuilder();
    }

    public ItemBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ItemBuilder withType(ItemType type) {
        this.type = type;
        return this;
    }

    public ItemBuilder withLocation(Location location) {
        this.location = location;
        return this;
    }

    public ItemBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder but() {
        return anItem().withId(id).withType(type).withLocation(location).withName(name);
    }

    public Item build() {
        Item item = new Item();
        item.setId(id);
        item.setType(type);
        item.setLocation(location);
        item.setName(name);
        return item;
    }
}

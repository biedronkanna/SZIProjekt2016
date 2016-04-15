package org.dziadzi.nodes.builders;

import org.dziadzi.nodes.Item;
import org.dziadzi.nodes.ItemType;
import org.dziadzi.nodes.enums.ItemTypeName;

import java.util.Set;

/**
 * Created by DELL on 2016-04-15.
 */
public class ItemTypeBuilder {
    private Long id;
    private ItemTypeName name;
    private Set<Item> items;

    private ItemTypeBuilder() {
    }

    public static ItemTypeBuilder anItemType() {
        return new ItemTypeBuilder();
    }

    public ItemTypeBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ItemTypeBuilder withName(ItemTypeName name) {
        this.name = name;
        return this;
    }

    public ItemTypeBuilder withItems(Set<Item> items) {
        this.items = items;
        return this;
    }

    public ItemTypeBuilder but() {
        return anItemType().withId(id).withName(name).withItems(items);
    }

    public ItemType build() {
        ItemType itemType = new ItemType();
        itemType.setId(id);
        itemType.setName(name);
        itemType.setItems(items);
        return itemType;
    }
}

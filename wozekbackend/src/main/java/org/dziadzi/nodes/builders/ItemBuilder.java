package org.dziadzi.nodes.builders;

import org.dziadzi.nodes.*;
import org.dziadzi.nodes.enums.ItemPackage;

/**
 * Created by DELL on 2016-05-21.
 */
public class ItemBuilder {
    private Long id;
    private ItemType type;
    private Location location;
    private ItemPackage itemPackage;
    private Integer weight;
    private Integer height;
    private Integer length;
    private Integer width;
    private Boolean fragile;
    private Boolean hasDate;
    private Boolean containsFood;

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

    public ItemBuilder withItemPackage(ItemPackage itemPackage) {
        this.itemPackage = itemPackage;
        return this;
    }

    public ItemBuilder withWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public ItemBuilder withHeight(Integer height) {
        this.height = height;
        return this;
    }

    public ItemBuilder withLength(Integer length) {
        this.length = length;
        return this;
    }

    public ItemBuilder withWidth(Integer width) {
        this.width = width;
        return this;
    }

    public ItemBuilder withFragile(Boolean fragile) {
        this.fragile = fragile;
        return this;
    }

    public ItemBuilder withHasDate(Boolean hasDate) {
        this.hasDate = hasDate;
        return this;
    }

    public ItemBuilder withContainsFood(Boolean containsFood) {
        this.containsFood = containsFood;
        return this;
    }

    public ItemBuilder but() {
        return anItem().withId(id).withType(type).withLocation(location).withItemPackage(itemPackage).withWeight(weight).withHeight(height).withLength(length).withWidth(width).withFragile(fragile).withHasDate(hasDate).withContainsFood(containsFood);
    }

    public Item build() {
        Item item = new Item();
        item.setId(id);
        item.setType(type);
        item.setLocation(location);
        item.setItemPackage(itemPackage);
        item.setWeight(weight);
        item.setHeight(height);
        item.setLength(length);
        item.setWidth(width);
        item.setFragile(fragile);
        item.setHasDate(hasDate);
        item.setContainsFood(containsFood);
        return item;
    }
}

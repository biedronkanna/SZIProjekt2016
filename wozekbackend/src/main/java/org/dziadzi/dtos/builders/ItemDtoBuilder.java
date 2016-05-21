package org.dziadzi.dtos.builders;

import org.dziadzi.dtos.ItemDto;
import org.dziadzi.nodes.enums.ItemPackage;

/**
 * Created by DELL on 2016-05-21.
 */
public class ItemDtoBuilder {
    private Long id;
    private ItemPackage itemPackage;
    private Integer weight;
    private Integer height;
    private Integer length;
    private Integer width;
    private Boolean fragile;
    private Boolean hasDate;
    private Boolean containsFood;

    private ItemDtoBuilder() {
    }

    public static ItemDtoBuilder anItemDto() {
        return new ItemDtoBuilder();
    }

    public ItemDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ItemDtoBuilder withItemPackage(ItemPackage itemPackage) {
        this.itemPackage = itemPackage;
        return this;
    }

    public ItemDtoBuilder withWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public ItemDtoBuilder withHeight(Integer height) {
        this.height = height;
        return this;
    }

    public ItemDtoBuilder withLength(Integer length) {
        this.length = length;
        return this;
    }

    public ItemDtoBuilder withWidth(Integer width) {
        this.width = width;
        return this;
    }

    public ItemDtoBuilder withFragile(Boolean fragile) {
        this.fragile = fragile;
        return this;
    }

    public ItemDtoBuilder withHasDate(Boolean hasDate) {
        this.hasDate = hasDate;
        return this;
    }

    public ItemDtoBuilder withContainsFood(Boolean containsFood) {
        this.containsFood = containsFood;
        return this;
    }

    public ItemDtoBuilder but() {
        return anItemDto().withId(id).withItemPackage(itemPackage).withWeight(weight).withHeight(height).withLength(length).withWidth(width).withFragile(fragile).withHasDate(hasDate).withContainsFood(containsFood);
    }

    public ItemDto build() {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(id);
        itemDto.setItemPackage(itemPackage);
        itemDto.setWeight(weight);
        itemDto.setHeight(height);
        itemDto.setLength(length);
        itemDto.setWidth(width);
        itemDto.setFragile(fragile);
        itemDto.setHasDate(hasDate);
        itemDto.setContainsFood(containsFood);
        return itemDto;
    }
}

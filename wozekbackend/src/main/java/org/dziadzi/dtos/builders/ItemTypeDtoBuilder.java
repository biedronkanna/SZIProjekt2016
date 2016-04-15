package org.dziadzi.dtos.builders;

import org.dziadzi.dtos.ItemTypeDto;

/**
 * Created by DELL on 2016-04-15.
 */
public class ItemTypeDtoBuilder {
    private Long id;
    private String name;

    private ItemTypeDtoBuilder() {
    }

    public static ItemTypeDtoBuilder anItemTypeDto() {
        return new ItemTypeDtoBuilder();
    }

    public ItemTypeDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ItemTypeDtoBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ItemTypeDtoBuilder but() {
        return anItemTypeDto().withId(id).withName(name);
    }

    public ItemTypeDto build() {
        ItemTypeDto itemTypeDto = new ItemTypeDto();
        itemTypeDto.setId(id);
        itemTypeDto.setName(name);
        return itemTypeDto;
    }
}

package org.dziadzi.dtos;

import org.dziadzi.nodes.enums.ItemTypeName;

/**
 * Created by DELL on 2016-05-21.
 */
public class ItemTypeDto {
    public ItemTypeName itemTypeName;

    public ItemTypeDto(ItemTypeName itemTypeName) {
        this.itemTypeName = itemTypeName;
    }
}

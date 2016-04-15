package org.dziadzi.dtos.builders;

import org.dziadzi.dtos.StorageDto;
import org.dziadzi.dtos.ItemTypeDto;

/**
 * Created by DELL on 2016-04-15.
 */
public class StorageDtoBuilder {
    private Long id;
    private String name;
    private ItemTypeDto type;

    private StorageDtoBuilder() {
    }

    public static StorageDtoBuilder anStorageDto() {
        return new StorageDtoBuilder();
    }

    public StorageDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public StorageDtoBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public StorageDtoBuilder withType(ItemTypeDto type) {
        this.type = type;
        return this;
    }

    public StorageDtoBuilder but() {
        return anStorageDto().withId(id).withName(name).withType(type);
    }

    public StorageDto build() {
        StorageDto itemDto = new StorageDto();
        itemDto.setId(id);
        itemDto.setName(name);
        itemDto.setType(type);
        return itemDto;
    }
}

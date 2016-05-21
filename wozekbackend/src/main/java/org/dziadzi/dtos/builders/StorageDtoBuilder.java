package org.dziadzi.dtos.builders;

import org.dziadzi.dtos.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2016-05-21.
 */
public class StorageDtoBuilder {
    private Long id;
    private StorageTypeDto type;
    private List<ItemDto> items = new ArrayList<>();

    private StorageDtoBuilder() {
    }

    public static StorageDtoBuilder aStorageDto() {
        return new StorageDtoBuilder();
    }

    public StorageDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public StorageDtoBuilder withType(StorageTypeDto type) {
        this.type = type;
        return this;
    }

    public StorageDtoBuilder withItems(List<ItemDto> items) {
        this.items = items;
        return this;
    }

    public StorageDtoBuilder but() {
        return aStorageDto().withId(id).withType(type).withItems(items);
    }

    public StorageDto build() {
        StorageDto storageDto = new StorageDto();
        storageDto.setId(id);
        storageDto.setType(type);
        storageDto.setItems(items);
        return storageDto;
    }
}

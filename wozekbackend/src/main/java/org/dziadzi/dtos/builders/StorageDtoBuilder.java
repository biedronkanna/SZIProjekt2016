package org.dziadzi.dtos.builders;

import org.dziadzi.dtos.StorageDto;
import org.dziadzi.dtos.StorageTypeDto;

/**
 * Created by DELL on 2016-04-15.
 */
public class StorageDtoBuilder {
    private Long id;
    private String name;
    private StorageTypeDto type;

    private StorageDtoBuilder() {
    }

    public static StorageDtoBuilder aStorageDto() {
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

    public StorageDtoBuilder withType(StorageTypeDto type) {
        this.type = type;
        return this;
    }

    public StorageDtoBuilder but() {
        return aStorageDto().withId(id).withName(name).withType(type);
    }

    public StorageDto build() {
        StorageDto storageDto = new StorageDto();
        storageDto.setId(id);
        storageDto.setName(name);
        storageDto.setType(type);
        return storageDto;
    }
}

package org.dziadzi.dtos.builders;

import org.dziadzi.dtos.StorageTypeDto;

/**
 * Created by DELL on 2016-04-15.
 */
public class StorageTypeDtoBuilder {
    private Long id;
    private String name;

    private StorageTypeDtoBuilder() {
    }

    public static StorageTypeDtoBuilder aStorageTypeDto() {
        return new StorageTypeDtoBuilder();
    }

    public StorageTypeDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public StorageTypeDtoBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public StorageTypeDtoBuilder but() {
        return aStorageTypeDto().withId(id).withName(name);
    }

    public StorageTypeDto build() {
        StorageTypeDto storageTypeDto = new StorageTypeDto();
        storageTypeDto.setId(id);
        storageTypeDto.setName(name);
        return storageTypeDto;
    }
}

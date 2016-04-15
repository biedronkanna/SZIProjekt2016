package org.dziadzi.dtos;

/**
 * Created by DELL on 2016-04-15.
 */
public class StorageDto {

    private Long id;

    private String name;

    private StorageTypeDto type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StorageTypeDto getType() {
        return type;
    }

    public void setType(StorageTypeDto type) {
        this.type = type;
    }
}

package org.dziadzi.dtos;

/**
 * Created by DELL on 2016-04-15.
 */
public class StorageDto {

    private Long id;

    private String name;

    private ItemTypeDto type;

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

    public ItemTypeDto getType() {
        return type;
    }

    public void setType(ItemTypeDto type) {
        this.type = type;
    }
}

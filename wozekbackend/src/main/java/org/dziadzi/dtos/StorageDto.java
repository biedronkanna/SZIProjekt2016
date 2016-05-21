package org.dziadzi.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2016-04-15.
 */
public class StorageDto {

    private Long id;


    private StorageTypeDto type;

    private List<ItemDto> items = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StorageTypeDto getType() {
        return type;
    }

    public void setType(StorageTypeDto type) {
        this.type = type;
    }


    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }
}

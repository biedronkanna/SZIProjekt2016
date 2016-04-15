package org.dziadzi.dtos.builders;

import org.dziadzi.dtos.StorageDto;
import org.dziadzi.dtos.LocationDto;

/**
 * Created by DELL on 2016-04-15.
 */
public class LocationDtoBuilder {
    private Long id;
    private Integer y;
    private Integer x;
    private StorageDto item;

    private LocationDtoBuilder() {
    }

    public static LocationDtoBuilder aLocationDto() {
        return new LocationDtoBuilder();
    }

    public LocationDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public LocationDtoBuilder withY(Integer y) {
        this.y = y;
        return this;
    }

    public LocationDtoBuilder withX(Integer x) {
        this.x = x;
        return this;
    }

    public LocationDtoBuilder withStorage(StorageDto item) {
        this.item = item;
        return this;
    }

    public LocationDtoBuilder but() {
        return aLocationDto().withId(id).withY(y).withX(x).withStorage(item);
    }

    public LocationDto build() {
        LocationDto locationDto = new LocationDto();
        locationDto.setId(id);
        locationDto.setY(y);
        locationDto.setX(x);
        locationDto.setStorage(item);
        return locationDto;
    }
}

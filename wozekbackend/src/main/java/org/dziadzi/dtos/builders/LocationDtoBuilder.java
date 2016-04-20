package org.dziadzi.dtos.builders;

import org.dziadzi.dtos.*;

/**
 * Created by DELL on 2016-04-20.
 */
public class LocationDtoBuilder {
    private Long id;
    private Integer y;
    private Integer x;
    private StorageDto storage;
    private ForkLiftDto forkLift;

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

    public LocationDtoBuilder withStorage(StorageDto storage) {
        this.storage = storage;
        return this;
    }

    public LocationDtoBuilder withForkLift(ForkLiftDto forkLift) {
        this.forkLift = forkLift;
        return this;
    }

    public LocationDtoBuilder but() {
        return aLocationDto().withId(id).withY(y).withX(x).withStorage(storage).withForkLift(forkLift);
    }

    public LocationDto build() {
        LocationDto locationDto = new LocationDto();
        locationDto.setId(id);
        locationDto.setY(y);
        locationDto.setX(x);
        locationDto.setStorage(storage);
        locationDto.setForkLift(forkLift);
        return locationDto;
    }
}

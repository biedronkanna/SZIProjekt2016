package org.dziadzi.dtos.builders;

import org.dziadzi.dtos.LocationDto;
import org.dziadzi.dtos.NeighbourDto;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kkuc on 2016-03-19.
 */
public class LocationDtoBuilder {
    private Long id;
    private Integer y;
    private Integer x;
    private Set<NeighbourDto> neighbours = new HashSet<>();

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

    public LocationDtoBuilder withNeighbours(Set<NeighbourDto> neighbours) {
        this.neighbours = neighbours;
        return this;
    }

    public LocationDtoBuilder but() {
        return aLocationDto().withId(id).withY(y).withX(x).withNeighbours(neighbours);
    }

    public LocationDto build() {
        LocationDto locationDto = new LocationDto();
        locationDto.setId(id);
        locationDto.setY(y);
        locationDto.setX(x);
        locationDto.setNeighbours(neighbours);
        return locationDto;
    }
}

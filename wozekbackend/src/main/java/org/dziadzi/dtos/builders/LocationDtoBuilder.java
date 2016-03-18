package org.dziadzi.dtos.builders;

import org.dziadzi.dtos.LocationDto;
import org.dziadzi.dtos.NeighbourDto;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kkuc on 2016-03-18.
 */
public class LocationDtoBuilder {
    private Long id;
    private Integer latitude;
    private Integer longitude;
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

    public LocationDtoBuilder withLatitude(Integer latitude) {
        this.latitude = latitude;
        return this;
    }

    public LocationDtoBuilder withLongitude(Integer longitude) {
        this.longitude = longitude;
        return this;
    }

    public LocationDtoBuilder withNeighbours(Set<NeighbourDto> neighbours) {
        this.neighbours = neighbours;
        return this;
    }

    public LocationDtoBuilder but() {
        return aLocationDto().withId(id).withLatitude(latitude).withLongitude(longitude).withNeighbours(neighbours);
    }

    public LocationDto build() {
        LocationDto locationDto = new LocationDto();
        locationDto.setId(id);
        locationDto.setLatitude(latitude);
        locationDto.setLongitude(longitude);
        locationDto.setNeighbours(neighbours);
        return locationDto;
    }
}

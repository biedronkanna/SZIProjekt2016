package org.dziadzi.dtos.builders;

import org.dziadzi.dtos.NeighbourDto;

/**
 * Created by kkuc on 2016-03-18.
 */
public class NeighbourDtoBuilder {
    private Long id;
    private Integer latitude;
    private Integer longitude;

    private NeighbourDtoBuilder() {
    }

    public static NeighbourDtoBuilder aNeighbourDto() {
        return new NeighbourDtoBuilder();
    }

    public NeighbourDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public NeighbourDtoBuilder withLatitude(Integer latitude) {
        this.latitude = latitude;
        return this;
    }

    public NeighbourDtoBuilder withLongitude(Integer longitude) {
        this.longitude = longitude;
        return this;
    }

    public NeighbourDtoBuilder but() {
        return aNeighbourDto().withId(id).withLatitude(latitude).withLongitude(longitude);
    }

    public NeighbourDto build() {
        NeighbourDto neighbourDto = new NeighbourDto();
        neighbourDto.setId(id);
        neighbourDto.setLatitude(latitude);
        neighbourDto.setLongitude(longitude);
        return neighbourDto;
    }
}

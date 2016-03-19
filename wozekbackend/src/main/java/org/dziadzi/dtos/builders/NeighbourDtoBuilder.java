package org.dziadzi.dtos.builders;

import org.dziadzi.dtos.NeighbourDto;

/**
 * Created by kkuc on 2016-03-19.
 */
public class NeighbourDtoBuilder {
    private Long id;
    private Integer y;
    private Integer x;

    private NeighbourDtoBuilder() {
    }

    public static NeighbourDtoBuilder aNeighbourDto() {
        return new NeighbourDtoBuilder();
    }

    public NeighbourDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public NeighbourDtoBuilder withY(Integer y) {
        this.y = y;
        return this;
    }

    public NeighbourDtoBuilder withX(Integer x) {
        this.x = x;
        return this;
    }

    public NeighbourDtoBuilder but() {
        return aNeighbourDto().withId(id).withY(y).withX(x);
    }

    public NeighbourDto build() {
        NeighbourDto neighbourDto = new NeighbourDto();
        neighbourDto.setId(id);
        neighbourDto.setY(y);
        neighbourDto.setX(x);
        return neighbourDto;
    }
}

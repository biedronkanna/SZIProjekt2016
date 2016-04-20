package org.dziadzi.dtos.builders;

import org.dziadzi.dtos.ForkLiftDto;
import org.dziadzi.nodes.enums.traversal.Direction;

/**
 * Created by DELL on 2016-04-20.
 */
public class ForkLiftDtoBuilder {
    private Direction direction;

    private ForkLiftDtoBuilder() {
    }

    public static ForkLiftDtoBuilder aForkLiftDto() {
        return new ForkLiftDtoBuilder();
    }



    public ForkLiftDtoBuilder withDirection(Direction direction) {
        this.direction = direction;
        return this;
    }

    public ForkLiftDtoBuilder but() {
        return aForkLiftDto().withDirection(direction);
    }

    public ForkLiftDto build() {
        ForkLiftDto forkLiftDto = new ForkLiftDto();

        forkLiftDto.setDirection(direction);
        return forkLiftDto;
    }
}

package org.dziadzi.dtos;

import org.dziadzi.nodes.enums.traversal.Direction;

/**
 * Created by DELL on 2016-04-20.
 */
public class ForkLiftDto {

    private Direction direction;


    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}

package org.dziadzi.dtos;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kkuc on 2016-03-18.
 */
public class LocationDto {
    private Long id;
    private Integer y;
    private Integer x;
    private Set<NeighbourDto> neighbours = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Set<NeighbourDto> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Set<NeighbourDto> neighbours) {
        this.neighbours = neighbours;
    }
}

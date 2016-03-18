package org.dziadzi.dtos;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kkuc on 2016-03-18.
 */
public class LocationDto {
    private Long id;
    private Integer latitude;
    private Integer longitude;
    private Set<NeighbourDto> neighbours = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public Set<NeighbourDto> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Set<NeighbourDto> neighbours) {
        this.neighbours = neighbours;
    }
}

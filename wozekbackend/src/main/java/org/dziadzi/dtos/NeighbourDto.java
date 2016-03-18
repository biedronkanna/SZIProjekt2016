package org.dziadzi.dtos;

/**
 * Created by kkuc on 2016-03-18.
 */
public class NeighbourDto {
    private Long id;
    private Integer latitude;
    private Integer longitude;

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
}

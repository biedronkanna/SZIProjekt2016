package org.dziadzi.nodes.builders;

import org.dziadzi.nodes.Item;
import org.dziadzi.nodes.Location;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kkuc on 2016-03-19.
 */
public class LocationBuilder {
    private Long id;
    private Integer longitude;
    private Integer latitude;
    private Set<Location> neighbours = new HashSet<Location>();
    private Item item;

    private LocationBuilder() {
    }

    public static LocationBuilder aLocation() {
        return new LocationBuilder();
    }

    public LocationBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public LocationBuilder withLongitude(Integer longitude) {
        this.longitude = longitude;
        return this;
    }

    public LocationBuilder withLatitude(Integer latitude) {
        this.latitude = latitude;
        return this;
    }

    public LocationBuilder withNeighbours(Set<Location> neighbours) {
        this.neighbours = neighbours;
        return this;
    }

    public LocationBuilder withItem(Item item) {
        this.item = item;
        return this;
    }

    public LocationBuilder but() {
        return aLocation().withId(id).withLongitude(longitude).withLatitude(latitude).withNeighbours(neighbours).withItem(item);
    }

    public Location build() {
        Location location = new Location();
        location.setId(id);
        location.setLongitude(longitude);
        location.setLatitude(latitude);
        location.setNeighbours(neighbours);
        location.setItem(item);
        return location;
    }
}

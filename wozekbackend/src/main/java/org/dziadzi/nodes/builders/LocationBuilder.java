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
    private Integer x;
    private Integer y;
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

    public LocationBuilder withX(Integer x) {
        this.x = x;
        return this;
    }

    public LocationBuilder withY(Integer y) {
        this.y = y;
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
        return aLocation().withId(id).withX(x).withY(y).withNeighbours(neighbours).withItem(item);
    }

    public Location build() {
        Location location = new Location();
        location.setId(id);
        location.setX(x);
        location.setY(y);
        location.setNeighbours(neighbours);
        location.setItem(item);
        return location;
    }
}

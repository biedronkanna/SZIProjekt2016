package org.dziadzi.nodes;


import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kkuc on 2016-03-17.
 */
@NodeEntity
public class Location {

    @GraphId
    private Long id;

    private Integer longitude;
    private Integer latitude;

    @Relationship(type = "IS_NEIGHBOUR", direction = Relationship.UNDIRECTED)
    private Set<Location> neighbours = new HashSet<Location>();

    @Relationship(type = "IS_LOCATED_AT", direction = Relationship.INCOMING)
    private Item item;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Set<Location> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Set<Location> neighbours) {
        this.neighbours = neighbours;
    }
}

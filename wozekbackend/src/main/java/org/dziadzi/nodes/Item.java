package org.dziadzi.nodes;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by kkuc on 2016-03-17.
 */
@NodeEntity
public class Item {

    @GraphId
    private Long id;


    @Relationship(type = "IS_TYPE_OF", direction = Relationship.INCOMING)
    private ItemType type;

    @Relationship(type = "IS_STORED_IN")
    private Storage storage;

    @Relationship(type = "IS_LOCATED_AT")
    private Location location;


    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

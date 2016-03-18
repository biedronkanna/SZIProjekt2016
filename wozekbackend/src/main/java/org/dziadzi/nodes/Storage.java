package org.dziadzi.nodes;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

/**
 * Created by kkuc on 2016-03-17.
 */
@NodeEntity
public class Storage {


    @GraphId
    private Long id;

    @Relationship(type = "IS_LOCATED_AT")
    private Location location;

    @Relationship(type = "IS_STORED_IN", direction = Relationship.INCOMING)
    private Set<Item> storedItems;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Item> getStoredItems() {
        return storedItems;
    }

    public void setStoredItems(Set<Item> storedItems) {
        this.storedItems = storedItems;
    }
}

package org.dziadzi.nodes;

import org.dziadzi.nodes.enums.ItemTypeName;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

/**
 * Created by kkuc on 2016-03-17.
 */
@NodeEntity
public class ItemType {


    @GraphId
    private Long id;
    private ItemTypeName name;

    @Relationship(type = "SHOULD_BE_STORED_IN")
    private StorageType storageType;

    @Relationship(type= "IS_TYPE_OF")
    private Set<Item> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }


    public ItemTypeName getName() {
        return name;
    }

    public void setName(ItemTypeName name) {
        this.name = name;
    }
}

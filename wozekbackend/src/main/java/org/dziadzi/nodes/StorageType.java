package org.dziadzi.nodes;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

/**
 * Created by kkuc on 2016-03-17.
 */
@NodeEntity
public class StorageType {

    @GraphId
    private Long id;

    @Relationship(type = "SHOULD_BE_STORED_IN", direction = Relationship.INCOMING)
    private
    Set<ItemType> storageTypeFor;

    @Relationship(type = "IS_TYPE_OF")
    private
    Set<Storage> instances;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ItemType> getStorageTypeFor() {
        return storageTypeFor;
    }

    public void setStorageTypeFor(Set<ItemType> storageTypeFor) {
        this.storageTypeFor = storageTypeFor;
    }

    public Set<Storage> getInstances() {
        return instances;
    }

    public void setInstances(Set<Storage> instances) {
        this.instances = instances;
    }
}

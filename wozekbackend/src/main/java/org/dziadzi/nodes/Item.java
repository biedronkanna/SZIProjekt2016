package org.dziadzi.nodes;

import org.dziadzi.nodes.enums.ItemPackage;
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

    private ItemPackage itemPackage;
    private Integer weight;
    private Integer height;
    private Integer length;
    private Integer width;
    private Boolean fragile;
    private Boolean hasDate;
    private Boolean containsFood;



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

    public ItemPackage getItemPackage() {
        return itemPackage;
    }

    public void setItemPackage(ItemPackage itemPackage) {
        this.itemPackage = itemPackage;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Boolean getFragile() {
        return fragile;
    }

    public void setFragile(Boolean fragile) {
        this.fragile = fragile;
    }

    public Boolean getHasDate() {
        return hasDate;
    }

    public void setHasDate(Boolean hasDate) {
        this.hasDate = hasDate;
    }

    public Boolean getContainsFood() {
        return containsFood;
    }

    public void setContainsFood(Boolean containsFood) {
        this.containsFood = containsFood;
    }
}

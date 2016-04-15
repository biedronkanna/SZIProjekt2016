package org.dziadzi.nodes.builders;

import org.dziadzi.nodes.*;

import java.util.Set;

/**
 * Created by DELL on 2016-04-15.
 */
public class StorageBuilder {
    private Long id;
    private Location location;
    private Set<Item> storedItems;
    private StorageType type;
    private String name;

    private StorageBuilder() {
    }

    public static StorageBuilder aStorage() {
        return new StorageBuilder();
    }

    public StorageBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public StorageBuilder withLocation(Location location) {
        this.location = location;
        return this;
    }

    public StorageBuilder withStoredItems(Set<Item> storedItems) {
        this.storedItems = storedItems;
        return this;
    }

    public StorageBuilder withType(StorageType type) {
        this.type = type;
        return this;
    }

    public StorageBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public StorageBuilder but() {
        return aStorage().withId(id).withLocation(location).withStoredItems(storedItems).withType(type).withName(name);
    }

    public Storage build() {
        Storage storage = new Storage();
        storage.setId(id);
        storage.setLocation(location);
        storage.setStoredItems(storedItems);
        storage.setType(type);
        storage.setName(name);
        return storage;
    }
}

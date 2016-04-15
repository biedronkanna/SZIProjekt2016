package org.dziadzi.nodes.builders;

import org.dziadzi.nodes.*;
import org.dziadzi.nodes.enums.StorageTypeName;

import java.util.Set;

/**
 * Created by DELL on 2016-04-15.
 */
public class StorageTypeBuilder {
	private Long id;
	private StorageTypeName name;
	private Set<ItemType> storageTypeFor;
	private Set<Storage> instances;

	private StorageTypeBuilder() {
	}

	public static StorageTypeBuilder aStorageType() {
		return new StorageTypeBuilder();
	}

	public StorageTypeBuilder withId(Long id) {
		this.id = id;
		return this;
	}

	public StorageTypeBuilder withName(StorageTypeName name) {
		this.name = name;
		return this;
	}

	public StorageTypeBuilder withStorageTypeFor(Set<ItemType> storageTypeFor) {
		this.storageTypeFor = storageTypeFor;
		return this;
	}

	public StorageTypeBuilder withInstances(Set<Storage> instances) {
		this.instances = instances;
		return this;
	}

	public StorageTypeBuilder but() {
		return aStorageType().withId(id).withName(name).withStorageTypeFor(storageTypeFor).withInstances(instances);
	}

	public StorageType build() {
		StorageType storageType = new StorageType();
		storageType.setId(id);
		storageType.setName(name);
		storageType.setStorageTypeFor(storageTypeFor);
		storageType.setInstances(instances);
		return storageType;
	}
}

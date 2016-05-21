package org.dziadzi.services;

import org.dziadzi.nodes.*;

/**
 * Created by DELL on 2016-04-15.
 */
public interface StorageService {

	Storage createStorage(Storage toCreate, Location location);

	Storage storeItem(Long storageId, Item item);
}

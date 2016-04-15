package org.dziadzi.services;

import org.dziadzi.nodes.Location;
import org.dziadzi.nodes.Storage;

/**
 * Created by DELL on 2016-04-15.
 */
public interface StorageService {

	Storage createStorage(Storage toCreate, Location location);
}

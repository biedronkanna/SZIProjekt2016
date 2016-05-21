package org.dziadzi.services.impl;

import org.dziadzi.nodes.*;
import org.dziadzi.repositories.StorageRepository;
import org.dziadzi.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by DELL on 2016-04-15.
 */
@Service
public class StorageServiceImpl implements StorageService {

	@Autowired
	private StorageRepository storageRepository;

	@Override
	public Storage createStorage(Storage toCreate, Location location) {
		toCreate.setLocation(location);
		return storageRepository.save(toCreate, 1);
	}
	@Override
	public Storage storeItem(Long storageId, Item item) {
		Storage storage = storageRepository.findOne(storageId);
		if(item!=null&&storage!=null) {
			storage.getStoredItems().add(item);
		 return storageRepository.save(storage, 1);
		}
		return null;
	}
}

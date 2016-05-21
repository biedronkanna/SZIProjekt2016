package org.dziadzi.services;

import org.dziadzi.dtos.ItemDto;
import org.dziadzi.dtos.ItemTypeDto;

import java.util.List;

/**
 * Created by DELL on 2016-05-21.
 */

public interface ItemRestService {

	List<ItemDto> getAlItems();

	ItemTypeDto classifyItem(Long itemId) throws Exception;
}

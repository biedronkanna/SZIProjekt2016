package org.dziadzi.services;

import org.dziadzi.dtos.ItemDto;
import org.dziadzi.dtos.ItemTypeDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by DELL on 2016-05-21.
 */

public interface ItemRestService {

	List<ItemDto> getAlItems();

	ItemTypeDto classifyItem(Long itemId) throws Exception;

	@RequestMapping(value = "locations/{locationId}/items", method = RequestMethod.PUT)
	void locateItem(@PathVariable("locationId") Long locationId,
					@RequestParam("itemId") Long itemId) throws Exception;
}

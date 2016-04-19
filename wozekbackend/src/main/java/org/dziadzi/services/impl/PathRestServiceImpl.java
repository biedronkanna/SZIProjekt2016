package org.dziadzi.services.impl;

import org.dziadzi.nodes.Location;
import org.dziadzi.nodes.enums.traversal.Action;
import org.dziadzi.repositories.LocationRepository;
import org.dziadzi.services.PathRestService;
import org.dziadzi.services.PathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by DELL on 2016-04-19.
 */
@RestController
public class PathRestServiceImpl implements PathRestService {

	@Autowired
	private PathService pathService;

	@Autowired
	private LocationRepository locationRepository;

	@Override
    @RequestMapping(value="path",method = RequestMethod.GET)
	public List<Action> getPath(@RequestParam("startId") Long startId,
			@RequestParam("endId") Long endId) {

        Location start = locationRepository.findOne(startId, 2);
        Location end = locationRepository.findOne(endId, 2);
        return pathService.findShortestPath(start,
                end);
	}
}

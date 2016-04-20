package org.dziadzi.services.impl;

import com.google.common.collect.Iterables;
import org.dziadzi.nodes.Location;
import org.dziadzi.nodes.enums.traversal.Action;
import org.dziadzi.repositories.ForkLiftRepository;
import org.dziadzi.repositories.LocationRepository;
import org.dziadzi.services.PathRestService;
import org.dziadzi.services.PathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by DELL on 2016-04-19.
 */
@RestController
public class PathRestServiceImpl implements PathRestService {

	@Autowired
	private PathService pathService;

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private ForkLiftRepository forkLiftRepository;

	@Override
    @RequestMapping(value="path",method = RequestMethod.GET)
	public List<Action> getPath(@RequestParam("endId") Long endId) {
		Location start = Optional.ofNullable(Iterables.getFirst(forkLiftRepository.findAll(),null)).map(e->e.getLocation()).orElseThrow(IllegalStateException::new);
        Location end = locationRepository.findOne(endId, 2);
        return pathService.findShortestPath(start,
                end);
	}
}

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

import java.util.*;

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
	@RequestMapping(value = "path", method = RequestMethod.GET)
	public List<Action> getPath(@RequestParam("endId") Long endId) {
		Location start = Optional.ofNullable(Iterables.getFirst(forkLiftRepository.findAll(), null))
				.map(e -> e.getLocation()).orElseThrow(IllegalStateException::new);
		Location end = locationRepository.findOne(endId, 2);
		end = changeEndIfIsStorage(end);
		return pathService.findShortestPath(start, end);
	}

	private Location changeEndIfIsStorage(Location end) {
		if (end.getStorage() != null) {
			Set<Location> neighbours = end.getNeighbours();
			for (Location l : neighbours) {
				l = locationRepository.findOne(l.getId(), 2);
				if (l.getStorage() == null) {
					return l;
				}
			}
		}
		return null;
	}
}

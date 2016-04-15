package org.dziadzi.services.impl;

import org.dziadzi.nodes.Location;
import org.dziadzi.nodes.traversal.Action;
import org.dziadzi.services.PathService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by DELL on 2016-04-15.
 */
@Service
public class PathServiceImpl implements PathService {

	@Override
	public List<Action> findShortestPath(Location start, Location end) {
		PriorityQueue<Location> fringe = new PriorityQueue<>(
				(Location l1, Location l2) -> l1.getfCost() - l2.getfCost());
		Set<Location> explored = new HashSet<>();
		List<Action> path = new ArrayList<>();
        fringe.add(start);
        Location current=null;
        while(true){
            current=fringe.poll();
            explored.add(current);
            if(current.getId()==end.getId()){
                break;
            }
            Set<Location> neighbours = current.getNeighbours();
            for (Location neighbour: neighbours){
                if(isNotTraversble(neighbour)||explored.contains(neighbour)){
                    continue;
                }
                if(newPathIsShorter(current,neighbour)|| !explored.contains(neighbour)){
                    neighbour.s
                }
            }
        }
		return path;
	}

    private boolean newPathIsShorter(Location current, Location neighbour) {
        return false;
    }

    private boolean isNotTraversble(Location neighbour) {
        return neighbour.getStorage()!=null;
    }
}

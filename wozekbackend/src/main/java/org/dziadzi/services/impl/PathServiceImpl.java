package org.dziadzi.services.impl;

import org.dziadzi.nodes.Location;
import org.dziadzi.nodes.enums.traversal.Action;
import org.dziadzi.services.PathService;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.lang.Math.abs;

/**
 * Created by DELL on 2016-04-15.
 */
@Service
public class PathServiceImpl implements PathService {

	public static final int NORMAL_COST = 10;
	public static final int DIFFICULT_COST = 14;

	@Override
	public List<Action> findShortestPath(Location start, Location end) {
		PriorityQueue<Location> fringe = new PriorityQueue<>(
				(Location l1, Location l2) -> l1.getfCost() - l2.getfCost());
		Set<Location> explored = new HashSet<>();
		List<Action> path = new ArrayList<>();
		fringe.add(start);
		Location current = null;
		while (true) {
			current = fringe.poll();
			explored.add(current);
			if (current.getId() == end.getId()) {
				break;
			}
			Set<Location> neighbours = current.getNeighbours();
			for (Location neighbour : neighbours) {
				if (isNotTraversable(neighbour) || explored.contains(neighbour)) {
					continue;
				}
				boolean neighbourIsNotInFringe = !fringe.contains(neighbour);
				if (newPathIsShorter(current, neighbour) || neighbourIsNotInFringe) {
                    setCostsAndParent(end, current, neighbour);
					if (neighbourIsNotInFringe) {
						fringe.add(neighbour);
					}
				}
			}
		}
		return path;
	}

    private void setCostsAndParent(Location end, Location current, Location neighbour) {
        neighbour.setgCost(calculateDistanceToStart(current, neighbour));
        neighbour.sethCost(estimateDistanceToEnd(neighbour, end));
        neighbour.setParent(current);
    }

    private Integer estimateDistanceToEnd(Location neighbour, Location end) {
		return (abs(end.getX() - neighbour.getX()) + abs(neighbour.getY() - end.getY()))
				* NORMAL_COST;
	}

	private Integer calculateDistanceToStart(Location current, Location neighbour) {
		int cost = neighbour.getDificultTraverse() ? DIFFICULT_COST : NORMAL_COST;

		return current.getgCost() + cost;
	}

	private boolean newPathIsShorter(Location current, Location neighbour) {
		return neighbour.getgCost() > calculateDistanceToStart(current, neighbour);
	}

	private boolean isNotTraversable(Location neighbour) {
		return neighbour.getStorage() != null;
	}
}

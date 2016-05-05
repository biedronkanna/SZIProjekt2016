package org.dziadzi.services.impl;

import org.dziadzi.nodes.ForkLift;
import org.dziadzi.nodes.Location;
import org.dziadzi.nodes.enums.traversal.Action;
import org.dziadzi.nodes.enums.traversal.Direction;
import org.dziadzi.repositories.ForkLiftRepository;
import org.dziadzi.repositories.LocationRepository;
import org.dziadzi.services.PathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.lang.Math.abs;
import static org.dziadzi.nodes.enums.traversal.Action.LEFT;
import static org.dziadzi.nodes.enums.traversal.Action.RIGHT;
import static org.dziadzi.nodes.enums.traversal.Direction.E;
import static org.dziadzi.nodes.enums.traversal.Direction.N;
import static org.dziadzi.nodes.enums.traversal.Direction.S;
import static org.dziadzi.nodes.enums.traversal.Direction.W;

/**
 * Created by DELL on 2016-04-15.
 */
@Service
public class PathServiceImpl implements PathService {

	public static final int NORMAL_COST = 10;
	public static final int DIFFICULT_COST = 36;

	@Autowired
	private ForkLiftRepository forkLiftRepository;

	@Autowired
	private LocationRepository locationRepository;

	@Override
	public List<Action> findShortestPath(Location start, Location end) {
		PriorityQueue<Location> fringe = new PriorityQueue<>((Location l1, Location l2) -> {
			int fDiff = l1.getfCost() - l2.getfCost();
			int hDiff = l1.gethCost() - l2.gethCost();
			if (fDiff != 0)
				return fDiff;
			return hDiff;
		});
		Set<Location> explored = new HashSet<>();

		fringe.add(start);
		Location current = null;
		ForkLift forkLift = start.getForkLift();
		while (true) {
			current = fringe.poll();
			current = locationRepository.findOne(current.getId());
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
		clearCosts(fringe, explored);
		List<Location> path = rebuildPath(start, end);
		List<Action> actions = provideActions(forkLift, path);
		return actions;
	}

	private void clearCosts(PriorityQueue<Location> fringe, Set<Location> explored) {
		for (Location l : fringe) {
			l.setgCost(0);
			l.sethCost(0);
			locationRepository.save(l, 0);
		}
		for (Location l : explored) {
			l.setgCost(0);
			l.sethCost(0);
			locationRepository.save(l, 0);
		}
	}

	private List<Action> provideActions(ForkLift forkLift, List<Location> path) {
		List<Action> toReturn = new ArrayList<>();

		for (Location nextLocation : path) {
			Direction forkLiftDirection = forkLift.getDirection();
			Direction nextDirection = calculateNeededDirection(forkLift, nextLocation);
			List<Action> actions = provideSingleStepActions(forkLiftDirection, nextDirection);
			toReturn.addAll(actions);
			forkLift.setDirection(nextDirection);
			forkLift.setLocation(nextLocation);
		}
		forkLiftRepository.save(forkLift, 1);
		return toReturn;
	}

	private List<Action> provideSingleStepActions(Direction fd, Direction nd) {
		List<Action> toReturn = new ArrayList<>();
		if ((nd == N && fd == E) || (nd == E && fd == S) || (nd == S && fd == W)
				|| (nd == W && fd == N)) {
			toReturn.add(LEFT);
		}
		if ((nd == N && fd == W) || (nd == E && fd == N) || (nd == S && fd == E)
				|| (nd == W && fd == S)) {
			toReturn.add(RIGHT);
		}
		if ((nd == N && fd == S) || (nd == S && fd == N) || (nd == W && fd == E)
				|| (nd == E && fd == W)) {
			toReturn.add(RIGHT);
			toReturn.add(RIGHT);
		}

		toReturn.add(Action.MOVE);

		if (toReturn.size() > 3)
			throw new IllegalStateException();
		return toReturn;
	}

	private Direction calculateNeededDirection(ForkLift forkLift, Location nextLocation) {
		Location forkLiftLocation = forkLift.getLocation();
		if (getXDiff(nextLocation, forkLiftLocation) == 1
				&& getYDiff(nextLocation, forkLiftLocation) == 0)
			return W;
		if (getXDiff(nextLocation, forkLiftLocation) == -1
				&& getYDiff(nextLocation, forkLiftLocation) == 0)
			return E;
		if (getXDiff(nextLocation, forkLiftLocation) == 0
				&& getYDiff(nextLocation, forkLiftLocation) == -1)
			return N;
		if (getXDiff(nextLocation, forkLiftLocation) == 0
				&& getYDiff(nextLocation, forkLiftLocation) == 1)
			return S;
		throw new IllegalStateException("direction calculation exception");
	}

	private int getYDiff(Location nextLocation, Location forkLiftLocation) {
		return forkLiftLocation.getY() - nextLocation.getY();
	}

	private int getXDiff(Location nextLocation, Location forkLiftLocation) {
		return forkLiftLocation.getX() - nextLocation.getX();
	}

	private List<Location> rebuildPath(Location start, Location end) {
		List<Location> fromEndToStart = new ArrayList<>();
		Location current = end;
		while (current.getId() != start.getId()) {
			fromEndToStart.add(current);
			current = current.getParent();
		}
		Collections.reverse(fromEndToStart);
		return fromEndToStart;
	}

	private void setCostsAndParent(Location end, Location current, Location neighbour) {
		neighbour.setgCost(calculateDistanceToStart(current, neighbour));
		neighbour.sethCost(estimateDistanceToEnd(neighbour, end));
		locationRepository.save(neighbour, 0);
		neighbour.setParent(current);
	}

	private Integer estimateDistanceToEnd(Location neighbour, Location end) {
		return (abs(getXDiff(neighbour, end)) + abs(getYDiff(end, neighbour))) * NORMAL_COST;
	}

	private Integer calculateDistanceToStart(Location current, Location neighbour) {
		int cost = neighbour.getDificultTraverse() ? DIFFICULT_COST : NORMAL_COST;

		return current.getgCost() + cost;
	}

	private boolean newPathIsShorter(Location current, Location neighbour) {
		boolean newIsShorter = neighbour.getgCost() > calculateDistanceToStart(current, neighbour);
		return newIsShorter;
	}

	private boolean isNotTraversable(Location neighbour) {
		return neighbour.getStorage() != null;
	}
}

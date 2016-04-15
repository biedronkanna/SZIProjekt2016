package org.dziadzi.services;

import org.dziadzi.nodes.Location;
import org.dziadzi.nodes.traversal.Action;

import java.util.List;

/**
 * Created by DELL on 2016-04-15.
 */
public interface PathService {

    List<Action> findShortestPath(Location start, Location end);
}

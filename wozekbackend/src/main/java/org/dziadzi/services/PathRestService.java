package org.dziadzi.services;

import org.dziadzi.nodes.enums.traversal.Action;

import java.util.List;

/**
 * Created by DELL on 2016-04-19.
 */
public interface PathRestService {
    List<Action> getPath(Long endId);
}

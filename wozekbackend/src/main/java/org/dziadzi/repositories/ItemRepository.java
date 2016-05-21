package org.dziadzi.repositories;

import org.dziadzi.nodes.Item;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by DELL on 2016-04-15.
 */
public interface ItemRepository extends GraphRepository<Item> {

List<Item> findAll();
}

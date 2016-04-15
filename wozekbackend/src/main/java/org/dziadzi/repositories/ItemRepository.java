package org.dziadzi.repositories;

import org.dziadzi.nodes.Item;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by DELL on 2016-04-15.
 */
public interface ItemRepository extends GraphRepository<Item> {


}

package org.dziadzi.repositories;

import org.dziadzi.nodes.Location;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;
import java.util.Map;

/**
 * Created by kkuc on 2016-03-17.
 */
public interface LocationRepository extends GraphRepository<Location>{

    @Query( "START l1=node:Location(key= {0}), l2=node:Location(key = {1}) " +
            "MATCH p = shortestPath(l1-[*]-l2) " +
            "RETURN p")
    Iterable<Map<String,Object>> findShortestPath(String idOne, String idTwo);

    Location findByLongitudeAndLatitude(Integer longitude, Integer latitude);
    List<Location> findByLatitude(Integer latitude);




}

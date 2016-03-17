package org.dziadzi.nodes;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by kkuc on 2016-03-17.
 */
@NodeEntity
public class Storage {


    @GraphId
    Long id;



}

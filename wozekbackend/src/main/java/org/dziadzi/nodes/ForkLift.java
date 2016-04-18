package org.dziadzi.nodes;

import org.neo4j.ogm.annotation.*;

/**
 * Created by DELL on 2016-04-18.
 */
@NodeEntity
public class ForkLift {
	@GraphId
	private Long id;

	@Relationship(type = "IS_LOCATED_AT")
	private Location location;

}

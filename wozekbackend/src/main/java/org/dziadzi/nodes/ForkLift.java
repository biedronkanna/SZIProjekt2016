package org.dziadzi.nodes;

import org.dziadzi.nodes.enums.traversal.Direction;
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

	private Direction direction;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}

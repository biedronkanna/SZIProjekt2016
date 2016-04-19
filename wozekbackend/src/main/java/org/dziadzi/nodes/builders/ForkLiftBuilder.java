package org.dziadzi.nodes.builders;

import org.dziadzi.nodes.ForkLift;
import org.dziadzi.nodes.Location;
import org.dziadzi.nodes.enums.traversal.Direction;

/**
 * Created by DELL on 2016-04-19.
 */
public class ForkLiftBuilder {
	private Long id;
	private Location location;
	private Direction direction;

	private ForkLiftBuilder() {
	}

	public static ForkLiftBuilder aForkLift() {
		return new ForkLiftBuilder();
	}

	public ForkLiftBuilder withId(Long id) {
		this.id = id;
		return this;
	}

	public ForkLiftBuilder withLocation(Location location) {
		this.location = location;
		return this;
	}

	public ForkLiftBuilder withDirection(Direction direction) {
		this.direction = direction;
		return this;
	}

	public ForkLiftBuilder but() {
		return aForkLift().withId(id).withLocation(location).withDirection(direction);
	}

	public ForkLift build() {
		ForkLift forkLift = new ForkLift();
		forkLift.setId(id);
		forkLift.setLocation(location);
		forkLift.setDirection(direction);
		return forkLift;
	}
}

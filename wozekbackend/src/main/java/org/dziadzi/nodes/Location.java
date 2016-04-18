package org.dziadzi.nodes;

import org.dziadzi.nodes.enums.traversal.Action;
import org.neo4j.ogm.annotation.*;

import java.util.*;

/**
 * Created by kkuc on 2016-03-17.
 */
@NodeEntity
public class Location {

	@GraphId
	private Long id;

	private Integer x;
	private Integer y;

	@Relationship(type = "IS_NEIGHBOUR", direction = Relationship.UNDIRECTED)
	private Set<Location> neighbours = new HashSet<Location>();

	@Relationship(type = "IS_LOCATED_AT", direction = Relationship.INCOMING)
	private Item item;

	@Relationship(type = "IS_SITUATED_AT", direction = Relationship.INCOMING)
	private Storage storage;

	@Relationship(type = "IS_PARENT_OF", direction = Relationship.INCOMING)
	private Location parent;

	private Integer gCost=0;

	private Integer hCost=0;

    private Boolean isDifficultTraverse=false;

	private List<Action> reachedBy = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Set<Location> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(Set<Location> neighbours) {
		this.neighbours = neighbours;
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public Location getParent() {
		return parent;
	}

	public void setParent(Location parent) {
		this.parent = parent;
	}

	public Integer getgCost() {
		return gCost;
	}

	public void setgCost(Integer gCost) {
		this.gCost = gCost;
	}

	public Integer gethCost() {
		return hCost;
	}

	public void sethCost(Integer hCost) {
		this.hCost = hCost;
	}

	public Integer getfCost() {
		return getgCost() + gethCost();
	}

    public List<Action> getReachedBy() {
        return reachedBy;
    }

    public void setReachedBy(List<Action> reachedBy) {
        this.reachedBy = reachedBy;
    }

    public Boolean getDificultTraverse() {
        return isDifficultTraverse;
    }

    public void setDificultTraverse(Boolean dificultTraverse) {
        isDifficultTraverse = dificultTraverse;
    }
}

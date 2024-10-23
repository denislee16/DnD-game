package Tower;

import Unit.Hero;
import Game.Resource;

import java.io.*;
import java.util.ArrayList;

public abstract class Buildings implements Serializable {
    protected int treeCost;
    protected int stoneCost;
    protected int level;
    protected String name;

    public Buildings(int treeCost, int stoneCost, int level) {
        this.treeCost = treeCost;
        this.stoneCost = stoneCost;
        this.level = level;
    }

    public boolean levelUp(Resource resource) {
        if (resource.getTree() >= this.treeCost && resource.getStone() >= this.stoneCost) {
            resource.setStone(resource.getStone() - this.stoneCost);
            resource.setTree(resource.getTree() - this.treeCost);
            this.level = this.level + 1;
            System.out.println("Уровень здания "+ this.name + ": "+ this.level);
            System.out.println(resource);
            return true;
        } else return false;
    }

    public int getLevel() {
        return this.level;
    }

    public String getName() {
        return this.name;
    }

//public abstract void modification(Resource resource, ArrayList<Hero> Unitslist);

    @Override
    public String toString() {
        return this.name + " (" + this.level + ")";
    }
}

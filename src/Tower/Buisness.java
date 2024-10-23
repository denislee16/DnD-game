package Tower;

import Game.Resource;
import Unit.Hero;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Buisness extends Buildings {

    public Buisness(int treeCost, int stoneCost, int level) {
        super(treeCost, stoneCost, level);
        this.name = "Мастерская";
    }

    public void bonus(Resource resource) {
        resource.setTree(resource.getTree() + 5 );
        resource.setStone(resource.getStone() + 5 );
        System.out.println("\nЛюди с мастерской дали 5 дерева и 5 камней");
        System.out.println(resource);
    }

    public boolean modification(Resource resource) {
        if (levelUp(resource)) {
            bonus(resource);
            return true;
        } else {
            System.out.println("Не хватает ресурсов");
            return false;
        }
    }


}

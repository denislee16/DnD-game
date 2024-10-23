package Tower;

import Game.Resource;
import Unit.Hero;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Healer extends Buildings {

    public Healer(int treeCost, int stoneCost, int level) {
        super(treeCost, stoneCost, level);
        this.name = "Дом лекаря";
    }

    public void bonus(ArrayList<Hero> Unitslist, int point) {
        for (int i = 0; i < Unitslist.size(); i++) {
            Unitslist.get(i).setHealth(Unitslist.get(i).getHealth() + point);
        }
    }

    public boolean modification(Resource resource, ArrayList<Hero> Unitslist, int point) {
        if (levelUp(resource)) {
           bonus(Unitslist, point);
           return true;
        } else {
            System.out.println("Не хватает ресурсов");
            return false;
        }
    }
}

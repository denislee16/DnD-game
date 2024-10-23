package Tower;

import Game.Resource;
import Unit.Hero;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Arsenal extends Buildings {

    public Arsenal(int treeCost, int stoneCost, int level) {
        super(treeCost, stoneCost, level);
        this.name = "Арсенал";
    }

    public void bonus(ArrayList<Hero> Unitslist, int point) {
        for (int i = 0; i < Unitslist.size(); i++) {
            Unitslist.get(i).setDefence(Unitslist.get(i).getDefence() + point);
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

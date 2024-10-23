package Tower;

import Game.Resource;
import Unit.Hero;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Tavern extends Buildings {

    public Tavern(int treeCost, int stoneCost, int level) {
        super(treeCost, stoneCost, level);
        this.name = "Таверна";
    }

    public void bonus(ArrayList<Hero> Unitslist) {
        System.out.println("Что Вы хотите улучшить? +0.5 очков к перемещению (0) или снизить штраф на 0.5 очков (1)?");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        for (int i = 0; i < Unitslist.size(); i++) {
            if (choice == 0) {
                Unitslist.get(i).setTravel(Unitslist.get(i).getTravel() + 0.5);
            } else if (choice == 1) {
                Unitslist.get(i).setTreePenalty(Unitslist.get(i).getTreePenalty() - 0.5);
                Unitslist.get(i).setSwampPenalty(Unitslist.get(i).getSwampPenalty() - 0.5);
                Unitslist.get(i).setHillPenalty(Unitslist.get(i).getHillPenalty() - 0.5);
            }
        }
    }

    public boolean modification(Resource resource, ArrayList<Hero> Unitslist) {
        if (levelUp(resource)) {
           bonus(Unitslist);
           return true;
        } else {
            System.out.println("Не хватает ресурсов");
            return false;
        }
    }
}

package Tower;

import Game.Resource;
import Unit.Hero;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Academy extends Buildings {

    public Academy(int treeCost, int stoneCost, int level) {
        super(treeCost, stoneCost, level);
        this.name = "Академия";
    }

    public void bonus() {
        System.out.println("Введите через Enter значения здоровья, атаки, дальности атаки, зашиты, перемещения нового героя");
        Scanner in = new Scanner(System.in);
        int health = in.nextInt();
        int damage = in.nextInt();
        int range = in.nextInt();
        int defence = in.nextInt();
        int travel = in.nextInt();
        int cost = (health + damage + range + defence + travel) / 2;
        Hero newUnit = new Hero('I', health, damage, range, defence, travel, cost);
        try {
            FileOutputStream file = new FileOutputStream(new File("Здания", "myHero"));
            ObjectOutputStream objectOut = new ObjectOutputStream(file);
            objectOut.writeObject(newUnit);
            objectOut.close();
            file.close();
            System.out.println("Герой создан");
        } catch (Exception e) {
            System.out.println("Ошибка с файлом");
            e.printStackTrace();
        }
    }

    public boolean modification(Resource resource) {
        if (levelUp(resource)) {
            bonus();
            return true;
        } else {
            System.out.println("Не хватает ресурсов");
            return false;
        }
    }
}


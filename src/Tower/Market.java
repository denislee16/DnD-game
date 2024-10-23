package Tower;

import Game.Resource;
import Unit.Hero;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Market extends Buildings {

    public Market(int treeCost, int stoneCost, int level) {
        super(treeCost, stoneCost, level);
        this.name = "Рынок";
    }

    public void bonus(Resource resource) {
        Scanner in = new Scanner(System.in);
        int count;
        int courseForStone = ThreadLocalRandom.current().nextInt(5, 11);
        int courseForTree = ThreadLocalRandom.current().nextInt(1, 3);
        System.out.println("Вы хотите обменять дерево или камни?(0)");
        System.out.println("Курс:\n" +
                "Одно дерево - " + courseForStone + " камней\n" +
                "Пять камней - " + courseForTree + " дерева");
        int choice0 = in.nextInt();
        if (choice0 == 0) {
            System.out.println("Что Вы хотите обменять? Камни(0) или дерево (1)");
            int choice = in.nextInt();
            if (choice == 0) {
                System.out.println("Сколько камней хотите обменять? Количество камней должно быть кратно 5");
                count = in.nextInt();
                if (count <= resource.getStone() && count % 5 == 0) {
                    resource.setTree(resource.getTree() + courseForTree * count / 5);
                    resource.setStone(resource.getStone() - count);
                }
            } else if (choice == 1) {
                System.out.println("Сколько дерева хотите обменять?");
                count = in.nextInt();
                if (count <= resource.getTree()) {
                    resource.setStone(resource.getStone() + count * courseForStone);
                    resource.setTree(resource.getTree() - count);
                }
            } else {
                System.out.println("Это нельзя здесь обменять");
            }
            System.out.println(resource);
        }
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

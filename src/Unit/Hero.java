package Unit;

import Battlefield.Battlefield;
import Game.Money;
import Game.Resource;
import Game.Save;
import Battlefield.MyPenalty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Hero implements Serializable {

    private char name;
    private int health;
    private int damage;
    private int range;    //дальность атаки
    private int defence;
    private double travel; //Перемещение
    private int cost;

    private double treePenalty;
    private double swampPenalty;
    private double hillPenalty;

    private int x;
    private int y;

    public Hero(char name, int health, int damage, int range, int defence, double travel, int cost) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.range = range;
        this.defence = defence;
        this.travel = travel;
        this.cost = cost;
        penaltyPoints();
    }

    public void penaltyPoints() {
        if (this.name == 'S' || this.name == 'L' || this.name == 'A' || this.name == '1' || this.name == '4' || this.name == '7') {
            this.treePenalty = 1.2;
            this.swampPenalty = 1.5;
            this.hillPenalty = 2;

        } else if (this.name == 'B' || this.name == 'O' || this.name == 'W' || this.name == '2' || this.name == '5' || this.name == '8') {
            this.treePenalty = 1;
            this.swampPenalty = 1.8;
            this.hillPenalty = 2.2;
        } else {
            this.treePenalty = 1.2;
            this.swampPenalty = 1.5;
            this.hillPenalty = 2.2;
        }
    }


    public void move(Battlefield battlefield, Save save, ArrayList<MyPenalty> myPenaltyArrayList) {
        Scanner console = new Scanner(System.in);
        System.out.println("Введите перемещение по x:");
        int dx = console.nextInt();
        System.out.println("Введите перемещение по y:");
        int dy = console.nextInt();

        while (this.x + dx >= battlefield.getSize() || this.x + dx < 0 || this.y - dy < 0 || this.y - dy >= battlefield.getSize() ||
                battlefield.testCoord(this.x + dx, this.y - dy) || Math.abs(dx) + Math.abs(dy) > battlefield.penalty(this, this.x + dx, this.y - dy, save, myPenaltyArrayList)) {
            System.out.println("\nНекорректные координаты. Введите заново:\n");
            System.out.println("Введите перемещение по x:");
            dx = console.nextInt();
            System.out.println("Введите перемещение по y:");
            dy = console.nextInt();
        }
        battlefield.removeHero(this.x, this.y);
        battlefield.addHero(this, this.x + dx, this.y - dy);
    }

    public void attack(ArrayList<Hero> opponentList, Hero opponent, Battlefield battlefield, Money playersWallet, Resource resource) {
        Scanner console = new Scanner(System.in);
        System.out.println("Введите расстояние по х до врага:");
        int dx = console.nextInt();
        System.out.println("Введите расстояние по y до врага:");
        int dy = console.nextInt();

        if (Math.abs(opponent.x - this.x) + Math.abs(opponent.y - this.y) <= this.range && this.x + dx == opponent.x && this.y - dy == opponent.y) {
            System.out.println("\nЕсть попадание!");
            if (decrease_health(opponentList, opponent, battlefield, playersWallet)) {
                resource.setTree(resource.getTree() + 1);
                resource.setStone(resource.getStone() + 1);
                System.out.println(resource);
            }
        } else {
            System.out.println("\nОшибка с координатами. Вы потеряли ход\n");
        }
    }

    public boolean decrease_health(ArrayList<Hero> opponentList, Hero opponent, Battlefield battlefield, Money attackerWallet) {
        if (this.damage < opponent.defence) {
            opponent.defence = opponent.defence - this.damage;
        } else {
            opponent.health = opponent.health + opponent.defence - this.damage;
            opponent.defence = 0;
        }
        if (opponent.health <= 0) {
            battlefield.removeHero(opponent.x, opponent.y);
            opponentList.remove(opponent);
            // opponentHealth = opponentHealth - opponent.health;
            System.out.println(opponent.getName() + " убит");
            if (opponent.getName() == 'Б') {
                attackerWallet.setMoney(attackerWallet.getMoney() + 15);
            } else {
                attackerWallet.setSilver(attackerWallet.getSilver() + 10);
            }
            return true;
        } else return false;
    }

    public Number outTravel(double travel) {
        Double newTravel = travel;
        if (travel % 1 == 0) {
            return newTravel.intValue();
        } else {
            return newTravel.floatValue();
        }
    }

    @Override
    public String toString() {
        if (this.name == 'A' || this.name == 'W' || this.name == 'H' || this.name == '7' || this.name == '8' || this.name == '9' || this.name == 'I') {
            return "Имя героя: " + this.name + " Здоровье: " + this.health + " Атака: " + this.damage + " Дальность атаки: " +
                    this.range + " Защита: " + this.defence + " Перемещение: " + outTravel(this.travel) + " Стоимость: " + this.cost + " золота";
        } else {
            return "Имя героя: " + this.name + " Здоровье: " + this.health + " Атака: " + this.damage + " Дальность атаки: " +
                    this.range + " Защита: " + this.defence + " Перемещение: " + outTravel(this.travel) + " Стоимость: " + this.cost + " серебра";
        }
    }

    public char getName() {
        return this.name;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getDefence() {
        return this.defence;
    }

    public int getHealth() {
        return this.health;
    }

    public int getRange() {
        return this.range;
    }

    public double getTravel() {
        return this.travel;
    }

    public int getCost() {
        return this.cost;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setTravel(double travel) {
        this.travel = travel;
    }

    public void setTreePenalty(double treePenalty) {
        this.treePenalty = treePenalty;
    }

    public void setSwampPenalty(double swampPenalty) {
        this.swampPenalty = swampPenalty;
    }

    public void setHillPenalty(double hillPenalty) {
        this.hillPenalty = hillPenalty;
    }

    public double getTreePenalty() {
        return this.treePenalty;
    }

    public double getSwampPenalty() {
        return this.swampPenalty;
    }

    public double getHillPenalty() {
        return this.hillPenalty;
    }
}


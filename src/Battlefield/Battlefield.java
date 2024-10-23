package Battlefield;

import Game.Save;
import Unit.Hero;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Battlefield implements Serializable {

    private static final long serialVersionUID = 1L;
    private char[][] field;
    private int size = 10;
    private String name;

    public Battlefield() {
        field = new char[size][size];
        CreateField();
    }

    private void CreateField() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = '*';
            }
        }
    }

    public void barrier(ArrayList<MyPenalty> myPenaltyArrayList) {
        Scanner in = new Scanner(System.in);
        int x;
        int y;
        char symbol;
        int choice = 0;
        MyPenalty myPenalty = new MyPenalty();
        while (choice == 0) {
            System.out.println("Введите координаты поля битвы для редактирования:");
            System.out.println("Введите координату х:");
            x = in.nextInt();
            System.out.println("Введите координату у:");
            y = in.nextInt();
            System.out.println("Доступные препятствия:\n" +
                    "! – Дерево\n" +
                    "# - Болото\n" +
                    "@ - Холм\n" +
                    "Если хотите добавить свое препятствие, то нажмите 0. Иначе нажмите на любую другую цифру");
            int myPenaltyChoice = in.nextInt();
            System.out.println("Введите символ, который будет на введенной клетке:");
            symbol = in.next().charAt(0);
            this.field[this.size - y][x - 1] = symbol;
            draw();
            if (myPenaltyChoice == 0) {
                myPenalty.setSymbol(symbol);
                myPenalty.createPenalty();
                myPenaltyArrayList.add(myPenalty);
            }
            System.out.println("Хотите ли Вы отредактировать поле? 0, если да. Любую другую цифру, если нет");
            choice = in.nextInt();
        }
    }

    public int penalty(Hero unit, int x, int y, Save save, ArrayList<MyPenalty> myPenaltyArrayList) {
        //save.downloadPenalty(this.name, myPenaltyArrayList);
        double unitTravel = unit.getTravel();
        int j = unit.getY();
        int i = unit.getX();
        while ((j != y) || (i != x)) {
            if (field[j][i] == '!') {
                unitTravel -= unit.getTreePenalty();
            } else if (field[j][i] == '#') {
                unitTravel -= unit.getSwampPenalty();
            } else if (field[j][i] == '@') {
                unitTravel -= unit.getHillPenalty();
            }
            for(MyPenalty elem: myPenaltyArrayList) {
            if (field[j][i] == elem.getSymbol()) {
                    if (unit.getName() == 'S' || unit.getName() == 'L' || unit.getName() == 'A' || unit.getName() == '1' || unit.getName() == '4' || unit.getName() == '7') {
                        unitTravel -= elem.getWarriorPenalty();
                    } else if (unit.getName() == 'B' || unit.getName() == 'O' || unit.getName() == 'W' || unit.getName() == '2' || unit.getName() == '5' || unit.getName() == '8') {
                        unitTravel -= elem.getArcherPenalty();
                    } else {
                        unitTravel -= elem.getOtherPenalty();
                    }
                }
            }
            if ((i == x) && (j != y)) {
                if (j > y) {
                    j--;
                } else {
                    j++;
                }
            }
            if (i != x) {
                if (i > x) {
                    i--;
                } else {
                    i++;
                }
            }
        }
        int rez = (int) Math.floor(unitTravel);
        return rez;
    }

    public void addHero(Hero player, int x, int y) {
        while (field[y][x] != '*') {
            x = x + 1;
        }
        field[y][x] = player.getName();
        player.setXY(x, y);
    }

    public void removeHero(int x, int y) {
        if (x >= 0 && x < size && y >= 0 && y < size) {
            field[y][x] = '*';
        }
    }

    public boolean testCoord(int x, int y) {
        return field[y][x] != '*';
    }

    public void draw() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public String getName() {
        return name;
    }

    public char[][] getField() {
        return field;
    }

    public void setField(char[][] field) {
        this.field = field;
    }

    public int getSize() {
        return size;
    }

    public void setName(String name) {
        this.name = name;
    }
}
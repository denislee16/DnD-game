package Battlefield;

import Unit.Hero;

import java.io.*;
import java.util.Scanner;

public class MyPenalty implements  Serializable {
    private double warriorPenalty;
    private double archerPenalty;
    private double otherPenalty;
    private char symbol;
    private static final long serialVersionUID = 1L;

    public void createPenalty() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите через Enter значения штрафов для воинов, лучников и остальных");
        this.warriorPenalty = in.nextDouble();
        this.archerPenalty = in.nextDouble();
        this.otherPenalty = in.nextDouble();
    }

 /*   public void savePenalty(){
        try {
            FileOutputStream file = new FileOutputStream(new File("Карты", "penalty"));
            ObjectOutputStream objectOut = new ObjectOutputStream(file);
            objectOut.writeObject(this);
            objectOut.close();
            file.close();
            System.out.println("Все ок");
        } catch (Exception e) {
            System.out.println("Ошибка со штрафом");
            e.printStackTrace();
        }
    }
*/

    public char getSymbol() {
        return symbol;
    }

    public double getArcherPenalty() {
        return archerPenalty;
    }

    public double getWarriorPenalty() {
        return warriorPenalty;
    }

    public double getOtherPenalty() {
        return otherPenalty;
    }

    public void setArcherPenalty(double archerPenalty) {
        this.archerPenalty = archerPenalty;
    }

    public void setWarriorPenalty(double warriorPenalty) {
        this.warriorPenalty = warriorPenalty;
    }

    public void setOtherPenalty(double otherPenalty) {
        this.otherPenalty = otherPenalty;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

}

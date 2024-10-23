package Game;

import java.io.Serializable;
import java.util.ArrayList;

public class Resource implements Serializable {
    private int tree;
    private int stone;
    private ArrayList<String> buildingsTable = new ArrayList<>();

    public Resource (int tree, int stone) {
        this.tree = tree;
        this.stone = stone;
    }

    public void buildingsCost(){
        System.out.println("Стоимость зданий и их улучшений:");
        if(buildingsTable.isEmpty()){
            buildingsTable.add("Академия:\t 10 дерева,\t 6 камней");
            buildingsTable.add("Арсенал:\t 6 дерева,\t 5 камней");
            buildingsTable.add("Кузница:\t 6 дерева,\t 5 камней");
            buildingsTable.add("Дом лекаря:\t 6 дерева,\t 5 камней");
            buildingsTable.add("Рынок:\t\t 5 дерева,\t 5 камней");
            buildingsTable.add("Мастерская:\t 6 дерева,\t 5 камней");
            buildingsTable.add("Таверна:\t 6 дерева,\t 5 камней");
        }
        for (int i = 0; i < buildingsTable.size(); i++) {
            System.out.println(buildingsTable.get(i));
        }
    }

    public int getTree(){
        return this.tree;
    }

    public int getStone(){
        return this.stone;
    }

    public void setTree(int tree){
        this.tree = tree;
    }
    public void setStone(int stone){
        this.stone = stone;
    }

    @Override
    public String toString() {
        return "\nВаш баланс: " + this.tree + " дерева,\t" + this.stone + " камней";

    }
}

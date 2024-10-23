import Unit.Hero;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

public class Statistic implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date date;
    private int count; //номер катки
    private String result;
    private ArrayList<Character> playersUnit;
    private ArrayList<Character> botsUnit;

    public Statistic() {
        this.date = new Date();
        this.playersUnit = new ArrayList<>();
        this.botsUnit = new ArrayList<>();
    }

    public void copyPlayersUnitName(ArrayList<Hero> unitList) {
        for (int i = 0; i < unitList.size(); i++) {
            this.playersUnit.add(unitList.get(i).getName());
        }
    }

    public void copyBotsUnitName(ArrayList<Hero> unitList) {
        for (int i = 0; i < unitList.size(); i++) {
            this.botsUnit.add(unitList.get(i).getName());
        }
    }

    public static void showStat(ArrayList<Statistic> statisticsList) {
        System.out.println("Ваша статистика боев:");
        for (Statistic elem : statisticsList) {
            System.out.println(elem);
        }
    }

    public String showUnit(ArrayList<Character> unitList) {
        StringBuilder sb = new StringBuilder();
        for (char elem : unitList) {
            sb.append(elem);
            sb.append(", ");
        }
        String res = sb.toString();
        res = res.substring(0, res.length() - 2);
        return res;
    }

    public static void saveStatistic(ArrayList<Statistic> statisticsList) {
        try {
            FileOutputStream file = new FileOutputStream(new File("Статистика боев", "statistic"));
            ObjectOutputStream objectOut = new ObjectOutputStream(file);
            objectOut.writeObject(statisticsList);
            objectOut.close();
            file.close();
            System.out.println("Статистика сохранена");
        } catch (Exception e) {
            System.out.println("Ошибка с сохранением статистики");
            e.printStackTrace();
        }
    }

    public static void downloadStatistic(File statisticFile, ArrayList<Statistic> statisticsList) {
        if (statisticFile.length() != 0) {
            try {
                FileInputStream f = new FileInputStream(statisticFile);
                ObjectInputStream objectIn = new ObjectInputStream(f);
                statisticsList.addAll((ArrayList<Statistic>) objectIn.readObject());
                objectIn.close();
                f.close();
                showStat(statisticsList);
            } catch (Exception e) {
                System.out.println("Ошибка с загрузкой статистики");
                e.printStackTrace();
            }
        }
        /*else {
            statisticsList = new ArrayList<>();
        }
         */
    }

    public static void choiceFilter(ArrayList<Statistic> statisticsList) {
        Scanner in = new Scanner(System.in);
        Stream<Statistic> stream = statisticsList.stream();
        System.out.println("Хотите отфильтровать статистику(0)?");
        int choice1 = in.nextInt();
        if (choice1 == 0) {
            System.out.println("Показать выигранные сражения(0), проигранные (1) или показать результаты в обратном порядке (2)?");
            int choice2 = in.nextInt();
            switch (choice2) {
                case 0:
                    stream.filter(x -> Objects.equals(x.getResult(), "Победа")).forEach(x -> System.out.println(x)); //System.out::println
                    break;
                case 1:
                    stream.filter(x -> Objects.equals(x.getResult(), "Поражение")).forEach(x -> System.out.println(x));
                    break;
                case 2:
                    stream.sorted(new ReverseStatisticComparator()).forEach(x -> System.out.println(x));
                    break;
                default:
                    System.out.println("А так нельзя");
            }
        }
    }


    @Override
    public String toString() {
        return "\nНомер игры: " + this.count +
                "\nРезультат: " + this.result +
                "\nВаши герои: " + showUnit(this.playersUnit) +
                "\nГерои противника: " + showUnit(this.botsUnit) +
                "\nДата: " + this.date + "\n\n";
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public String getResult() {
        return result;
    }

    public ArrayList<Character> getPlayersUnit() {
        return playersUnit;
    }
}

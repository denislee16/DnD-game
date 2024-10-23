import Game.*;
import Tower.*;
import Unit.Hero;
import Battlefield.Battlefield;
import Battlefield.MyPenalty;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void table(ArrayList<String> info) {
        if (info.isEmpty()) {
            info.add("Имя героя: Мечник\nСимвол: S\t Здоровье: 50\t Атака: 5\t Дальность атаки: 1\t Защита: 8\t Перемещение: 3\t Стоимость: 10 серебра");
            info.add("Имя героя: Копьеносец\nСимвол: L\t Здоровье: 35\t Атака: 3\t Дальность атаки: 1\t Защита: 4\t Перемещение: 6\t Стоимость: 15 серебра");
            info.add("Имя героя: Топорщик\nСимвол: A\t Здоровье: 45\t Атака: 9\t Дальность атаки: 1\t Защита: 3\t Перемещение: 4\t Стоимость: 20 золота");
            info.add("Имя героя: Лучник(дл. лук)\nСимвол: B\t Здоровье: 30\t Атака: 6\t Дальность атаки: 5\t Защита: 8\t Перемещение: 2\t Стоимость: 15 серебра");
            info.add("Имя героя: Лучник\nСимвол: O\t Здоровье: 25\t Атака: 3\t Дальность атаки: 3\t Защита: 4\t Перемещение: 4\t Стоимость: 19 серебра");
            info.add("Имя героя: Арбалетчик\nСимвол: W\t Здоровье: 40\t Атака: 100\t Дальность атаки: 10\t Защита: 3\t Перемещение: 5\t Стоимость: 23 золота");
            info.add("Имя героя: Рыцарь\nСимвол: K\t Здоровье: 30\t Атака: 5\t Дальность атаки: 1\t Защита: 3\t Перемещение: 6\t Стоимость: 20 серебра");
            info.add("Имя героя: Кирасир\nСимвол: C\t Здоровье: 50\t Атака: 2\t Дальность атаки: 1\t Защита: 7\t Перемещение: 5\t Стоимость: 23 серебра");
            info.add("Имя героя: Конный лучник\nСимвол: H\t Здоровье: 25\t Атака: 3\t Дальность атаки: 3\t Защита: 2\t Перемещение: 5\t Стоимость: 25 золота");
        }
        for (int i = 0; i < info.size(); i++) {
            System.out.println(info.get(i));
        }
    }

    public static void boostUnit(ArrayList<Object> buildings, ArrayList<Hero> unitsList, Resource resource) {
        for (Object obj : buildings) {
            if (obj instanceof Arsenal) {
                ((Arsenal) obj).bonus(unitsList, ((Arsenal) obj).getLevel());
            } else if (obj instanceof Buisness) {
                ((Buisness) obj).bonus(resource);
            } else if ((obj instanceof Forge)) {
                ((Forge) obj).bonus(unitsList, ((Forge) obj).getLevel());
            } else if ((obj instanceof Healer)) {
                ((Healer) obj).bonus(unitsList, ((Healer) obj).getLevel());
            } else if ((obj instanceof Market)) {
                ((Market) obj).bonus(resource);
            } else if ((obj instanceof Tavern)) {
                ((Tavern) obj).bonus(unitsList);
            }
        }
    }

    public static void saveBuildings(Resource resource, ArrayList<Hero> unitsList, ArrayList<Object> buildings) {
        Scanner in = new Scanner(System.in);
        int choice;
        String name;
        String name1;
        int flag = 1;
        resource.buildingsCost();
        System.out.println(resource);

        HashMap<String, Integer> dict = null;
        File f = new File("Здания", "buildingsMap");
        if (f.length() == 0) {
            dict = new HashMap<>();
        } else {
            try {
                FileInputStream dictFile = new FileInputStream(f);
                ObjectInputStream dictIn = new ObjectInputStream(dictFile);
                dict = (HashMap<String, Integer>) dictIn.readObject();
                dictIn.close();
                dictFile.close();
            } catch (Exception e) {
                System.out.println("Ошибка с файлом");
                e.printStackTrace();
            }
        }
        while (flag == 1) {
            System.out.println("Хотите ли Вы улучшить или купить свои здания? 0 - да, любая другая цифра - нет");
            choice = in.nextInt();
            if (choice == 0) {
                System.out.println("Какое здание Вы хотите купить/улучшить?");
                name1 = in.nextLine();
                name = in.nextLine();
                switch (name) {
                    case "Академия":
                        Academy academy = null;
                        if (!dict.containsKey("Академия")) {
                            academy = new Academy(10, 6, 0);
                            if (academy.modification(resource)) {
                                dict.put("Академия", 1);
                                buildings.add(academy);
                            }
                        } else {
                            System.out.println("У Вас уже есть академия");
                        }
                        break;
                    case "Арсенал":
                        Arsenal arsenal;
                        if (!dict.containsKey("Арсенал")) {
                            arsenal = new Arsenal(6, 5, 0);
                            if (!arsenal.modification(resource, unitsList, 1)) {
                                arsenal = null;
                            } else {
                                dict.put("Арсенал", 1);
                                buildings.add(arsenal);
                            }
                        } else {
                            for (Object obj : buildings) {
                                if (obj instanceof Arsenal) {
                                    arsenal = (Arsenal) obj;
                                    arsenal.modification(resource, unitsList, 1);
                                }
                            }
                        }
                        break;
                    case "Мастерская":
                        Buisness buisness;
                        if (!dict.containsKey("Мастерская") || (dict.containsKey("Мастерская") && dict.get("Мастерская") < 4)) {
                            buisness = new Buisness(6, 5, 0);
                            if (buisness.modification(resource)) {
                                if (!dict.containsKey("Мастерская")) {
                                    dict.put("Мастерская", 1);
                                } else {
                                    dict.put("Мастерская", dict.get("Мастерская") + 1);
                                }
                                buildings.add(buisness);
                            }
                        } else {
                            System.out.println("У Вас много мастерских");
                        }
                        break;
                    case "Кузница":
                        Forge forge;
                        if (!dict.containsKey("Кузница")) {
                            forge = new Forge(6, 5, 0);
                            if (!forge.modification(resource, unitsList, 1)) {
                                forge = null;
                            } else {
                                dict.put("Кузница", 1);
                                buildings.add(forge);
                            }
                        } else {
                            for (Object obj : buildings) {
                                if (obj instanceof Forge) {
                                    forge = (Forge) obj;
                                    forge.modification(resource, unitsList, 1);
                                }
                            }
                        }
                        break;
                    case "Дом лекаря":
                        Healer healer;
                        if (!dict.containsKey("Дом лекаря")) {
                            healer = new Healer(6, 5, 0);
                            if (!healer.modification(resource, unitsList, 1)) {
                                healer = null;
                            } else {
                                dict.put("Дом лекаря", 1);
                                buildings.add(healer);
                            }
                        } else {
                            for (Object obj : buildings) {
                                if (obj instanceof Healer) {
                                    healer = (Healer) obj;
                                    healer.modification(resource, unitsList, 1);
                                }
                            }
                        }
                        break;
                    case "Рынок": //noLevelUp
                        Market market;
                        if (!dict.containsKey("Рынок")) {
                            market = new Market(5, 5, 0);
                            if (!market.modification(resource)) {
                                market = null;
                            } else {
                                dict.put("Рынок", 1);
                                buildings.add(market);
                            }
                        }
                        break;
                    case "Таверна":
                        Tavern tavern;
                        if (!dict.containsKey("Таверна")) {
                            tavern = new Tavern(6, 5, 0);
                            if (!tavern.modification(resource, unitsList)) {
                                tavern = null;
                            } else {
                                dict.put("Таверна", 1);
                                buildings.add(tavern);
                            }
                        } else {
                            for (Object obj : buildings) {
                                if (obj instanceof Tavern) {
                                    tavern = (Tavern) obj;
                                    tavern.modification(resource, unitsList);
                                }
                            }
                        }
                        break;

                    default:
                        System.out.println("Такого здания нет");
                }
            } else {
                flag = 2;
            }
        }
        try {
            FileOutputStream dictFile = new FileOutputStream(f);
            ObjectOutputStream objectOut = new ObjectOutputStream(dictFile);
            objectOut.writeObject(dict);
            objectOut.close();
            dictFile.close();
        } catch (Exception e) {
            System.out.println("Ошибка с сохранением словаря");
            e.printStackTrace();
        }
    }


    public static void botThinks(ArrayList<Hero> playersList, ArrayList<Hero> botList, Battlefield battlefield, Money botsMoney, Save save, ArrayList<MyPenalty> myPenaltyArrayList) {
        int flag = 0;
        for (int i = 0; i < botList.size(); i++) {
            for (int j = 0; j < playersList.size(); j++) {
                if (Math.abs(botList.get(i).getY() - playersList.get(j).getY()) + Math.abs(botList.get(i).getX() - playersList.get(j).getX()) <= botList.get(i).getRange()) {
                    System.out.println("Противник атаковал героя " + playersList.get(j).getName());
                    botList.get(i).decrease_health(playersList, playersList.get(j), battlefield, botsMoney);
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) {
                break;
            }
        }
        if (flag == 0) {
            if (botList.size() > 0) {
                int numOfHero = ThreadLocalRandom.current().nextInt(0, botList.size());
                int a = (int) botList.get(numOfHero).getTravel() / 2;
                int dx = ThreadLocalRandom.current().nextInt(-a, a + 1);
                int dy = ThreadLocalRandom.current().nextInt(-a, a + 1);
                while (botList.get(numOfHero).getX() + dx >= battlefield.getSize() || botList.get(numOfHero).getX() + dx < 0 ||
                        botList.get(numOfHero).getY() + dy < 0 || botList.get(numOfHero).getY() + dy >= battlefield.getSize() ||
                        battlefield.testCoord(botList.get(numOfHero).getX() + dx, botList.get(numOfHero).getY() + dy)
                        || Math.abs(dx) + Math.abs(dy) > battlefield.penalty(botList.get(numOfHero), botList.get(numOfHero).getX() + dx, botList.get(numOfHero).getY() + dy, save, myPenaltyArrayList)) {
                    dx = ThreadLocalRandom.current().nextInt(-a, a + 1);
                    dy = ThreadLocalRandom.current().nextInt(-a, a + 1);
                }
                battlefield.removeHero(botList.get(numOfHero).getX(), botList.get(numOfHero).getY());
                battlefield.addHero(botList.get(numOfHero), botList.get(numOfHero).getX() + dx, botList.get(numOfHero).getY() + dy);
                System.out.println("\nПротивник сделал ход");
            }
        }
    }

    public static void aliveUnit(ArrayList<Hero> player, ArrayList<Hero> bot) {
        System.out.println("\nВаши герои:");
        for (int i = 0; i < player.size(); i++) {
            System.out.println(player.get(i));
        }
        System.out.println("Герои противника:");
        for (int i = 0; i < bot.size(); i++) {
            System.out.println(bot.get(i));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Save progress = new Save();
        Statistic statistic = new Statistic();
        ArrayList<MyPenalty> myPenaltyArrayList = new ArrayList<>();
        Money playersMoney = new Money(40, ThreadLocalRandom.current().nextInt(0, 51));
        Money botsMoney = new Money(30, ThreadLocalRandom.current().nextInt(40, 56));
        Battlefield battlefield = new Battlefield();
        Player player = new Player();
        Bot bot = new Bot();
        ArrayList<String> units = new ArrayList<>();

        Resource resource = null;
        File resourceFile = new File("Ресурсы", "resource");
        if (resourceFile.length() != 0) {
            try {
                FileInputStream f = new FileInputStream(resourceFile);
                ObjectInputStream objectIn = new ObjectInputStream(f);
                resource = (Resource) objectIn.readObject();
                objectIn.close();
                f.close();
            } catch (Exception e) {
                System.out.println("Ошибка с загрузкой ресурсов");
                e.printStackTrace();
            }

        } else {
            resource = new Resource(100, 100);
        }

        File builfingsFile = new File("Здания", "buildings");
        ArrayList<Object> buildings = null;
        if (builfingsFile.length() != 0) {
            try {
                FileInputStream f = new FileInputStream(builfingsFile);
                ObjectInputStream objectIn = new ObjectInputStream(f);
                buildings = (ArrayList<Object>) objectIn.readObject();
                objectIn.close();
                f.close();
            } catch (Exception e) {
                System.out.println("Ошибка с загрузкой зданий");
                e.printStackTrace();
            }
        } else {
            buildings = new ArrayList<>();
        }

        File statisticFile = new File("Статистика боев", "statistic");
        ArrayList<Statistic> statisticsList = new ArrayList<>();
        Statistic.downloadStatistic(statisticFile, statisticsList);
        Statistic.choiceFilter(statisticsList);
        statisticsList.add(statistic);
        statistic.setCount(statisticsList.size());


        progress.map(battlefield, myPenaltyArrayList);


        System.out.println("На Ваш город готовится нападение! В Вашем сундуке " + playersMoney.getMoney() + " золота и " + playersMoney.getSilver() + " серебра." +
                " Покупайте героев и защищайте свой город!\nУ противника " + botsMoney.getMoney() + " золота и " + botsMoney.getSilver() + " серебра." + " И у них есть босс. Его имя - 'Б'. За его смерть Вы получите золото, за остальных - серебро\n");

        table(units);
        player.playerChoice(battlefield, playersMoney);
        bot.botChoice(battlefield, botsMoney);
        aliveUnit(player.getPlayerList(), bot.getBotList());

        statistic.copyPlayersUnitName(player.getPlayerList());
        statistic.copyBotsUnitName(bot.getBotList());

        if (buildings.isEmpty()) {
            System.out.println("\nУ Вас пока нет зданий");
        } else {
            System.out.println("Ваши здания:");
            for (int i = 0; i < buildings.size(); i++) {
                System.out.println(buildings.get(i));
            }
        }

        boostUnit(buildings, player.getPlayerList(), resource);
        saveBuildings(resource, player.getPlayerList(), buildings);

        Scanner in = new Scanner(System.in);
        int n = 1;
        int choice;

        while (!player.getPlayerList().isEmpty() && !bot.getBotList().isEmpty()) {
            aliveUnit(player.getPlayerList(), bot.getBotList());
            battlefield.draw();
            System.out.println("\nНажмите 0, если хотите переместиться. Нажмите 1, если хотите нанести удар. И любую другую цифру, если хотите пропустить ход\n");
            choice = in.nextInt();
            if (choice == 0) {
                System.out.println("Старайтесь избегать препятствия!\n" +
                        "! – Дерево\n" +
                        "# - Болото\n" +
                        "@ - Холм\n" +
                        "Выберите героя, которым хотите переместиться:"); //or coordinats?
                int flag = 1;
                char symbol = in.next().charAt(0);
                for (int i = 0; i < player.getPlayerList().size(); i++) {
                    if (symbol == player.getPlayerList().get(i).getName()) {
                        player.getPlayerList().get(i).move(battlefield, progress, myPenaltyArrayList);
                        flag = 0;
                        break;
                    }
                }
                if (flag == 1) {
                    System.out.println("Неправильно выбран герой. Вы пропускаете ход");
                }
                botThinks(player.getPlayerList(), bot.getBotList(), battlefield, botsMoney, progress, myPenaltyArrayList);
            } else if (choice == 1) {
                System.out.println("Выберите героя, которым хотите атаковать противника:");
                int flag = 1;
                char symbol = in.next().charAt(0);
                for (int i = 0; i < player.getPlayerList().size(); i++) {
                    if (symbol == player.getPlayerList().get(i).getName()) {
                        System.out.println("Кого хотите атаковать?");
                        symbol = in.next().charAt(0);
                        for (int j = 0; j < bot.getBotList().size(); j++) {
                            if (symbol == bot.getBotList().get(j).getName()) {
                                player.getPlayerList().get(i).attack(bot.getBotList(), bot.getBotList().get(j), battlefield, playersMoney, resource);
                                flag = 0;
                                break;
                            }
                        }
                        if (flag == 1) {
                            flag = 2;
                        }
                        break;
                    }
                }
                if (flag == 1) {
                    System.out.println("Неправильно выбран герой. Вы пропускаете ход");
                } else if (flag == 2) {
                    System.out.println("У врага такого героя нет. Вы пропускаете ход");
                }
                botThinks(player.getPlayerList(), bot.getBotList(), battlefield, botsMoney, progress, myPenaltyArrayList);
            }
            n++;
        }
        if (player.getPlayerList().isEmpty()) {
            System.out.println("Вы проиграли!");
            statistic.setResult("Поражение");
        } else {
            System.out.println("Вы выиграли! +10 дерева и камней в качестве бонуса");
            statistic.setResult("Победа");
            resource.setTree(resource.getTree() + 10);
            resource.setStone(resource.getStone() + 10);
            System.out.println(resource);
        }

        Statistic.saveStatistic(statisticsList);

        try {
            FileOutputStream f = new FileOutputStream(resourceFile);
            ObjectOutputStream objectOut = new ObjectOutputStream(f);
            objectOut.writeObject(resource);
            objectOut.close();
            f.close();
        } catch (Exception e) {
            System.out.println("Ошибка с сохранением ресурсов");
            e.printStackTrace();
        }

        try {
            FileOutputStream f = new FileOutputStream(builfingsFile);
            ObjectOutputStream objectOut = new ObjectOutputStream(f);
            objectOut.writeObject(buildings);
            objectOut.close();
            f.close();
        } catch (Exception e) {
            System.out.println("Ошибка с сохранением зданий");
            e.printStackTrace();
        }
    }
}
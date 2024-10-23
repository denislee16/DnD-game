package Game;

import Battlefield.Battlefield;
import Unit.Hero;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private ArrayList<Hero> playerList;

    public Player() {
        this.playerList = new ArrayList<>();
    }

    public void newUnit(Money money, Battlefield battlefield) {
        Scanner in = new Scanner(System.in);
        File f = new File("Здания", "myHero");
        if (f.length() != 0) {
            System.out.println("Хотите купить созданного героя(0)?");
            Hero unit = null;
            try {
                FileInputStream file = new FileInputStream(f);
                ObjectInputStream objectIn = new ObjectInputStream(file);
                unit = (Hero) objectIn.readObject();
                objectIn.close();
                file.close();
                System.out.println(unit);
            } catch (Exception e) {
                System.out.println("Ошибка с загрузкой нового героя");
                e.printStackTrace();
            }
            int choice = in.nextInt();
            if (choice == 0) {
                if (money.getMoney() >= unit.getCost()) {
                    battlefield.addHero(unit, 0, 9);
                    this.playerList.add(unit);
                } else {
                    System.out.println("Не хватает запасов");
                    unit = null;
                }
            }
        }
    }

    public void playerChoice(Battlefield battlefield, Money money) {
        Scanner console = new Scanner(System.in);
        newUnit(money, battlefield);
        System.out.println("\nВведите количество героев, которых Вы хотите приобрести. Будьте внимательны, иначе это количество может уменьшиться");
        int n = console.nextInt();
        char symbol;
        int value;
        for (int i = 0; i < n; i++) {
            System.out.println("\nВведите символ героя, которого хотите приобрести:");
            symbol = console.next().charAt(0);
            System.out.println("\nКакой валютой Вы хотите расплатиться? 0, если золотом. 1, если серебром. Вы можете обменять 1 золото на 2 серебра");
            value = console.nextInt();

            if (value > 1) {
                System.out.println("\nЭтим нельзя расплатиться. Уходите");
                break;
            }

            switch (symbol) {
                case 'S':
                    if (money.getSilver() >= 10 || money.getMoney() >= 5) {
                        Hero unit1 = new Hero('S', 50, 5, 1, 8, 3, 10);
                        battlefield.addHero(unit1, i, 9);
                        this.playerList.add(unit1);
                        if (value == 1 && money.getSilver() >= 10) {
                            money.setSilver(money.getSilver() - unit1.getCost());
                        } else {
                            money.setMoney(money.getMoney() - unit1.getCost() / 2);
                        }
                    } else {
                        System.out.println("Не хватает запасов");
                    }
                    break;
                case 'L':
                    if (money.getMoney() >= 7.5 || money.getSilver() >= 15) {
                        Hero unit2 = new Hero('L', 35, 3, 1, 4, 6, 15);
                        battlefield.addHero(unit2, i, 9);
                        this.playerList.add(unit2);
                        if (value == 1 && money.getSilver() >= 15) {
                            money.setSilver(money.getSilver() - unit2.getCost());
                        } else {
                            money.converter(unit2.getCost() / 2.0);
                        }
                    } else {
                        System.out.println("Не хватает запасов");
                    }
                    break;
                case 'A':
                    if (money.getMoney() >= 20 && value == 0) {
                        Hero unit3 = new Hero('A', 45, 9, 1, 3, 4, 20);
                        battlefield.addHero(unit3, i, 9);
                        this.playerList.add(unit3);
                        money.setMoney(money.getMoney() - unit3.getCost());
                    } else {
                        System.out.println("Этот герой стоит золото");
                    }
                    break;
                case 'B':
                    if (money.getMoney() >= 7.5 || money.getSilver() >= 15) {
                        Hero unit4 = new Hero('B', 30, 6, 5, 8, 2, 15);
                        battlefield.addHero(unit4, i, 9);
                        this.playerList.add(unit4);
                        if (value == 1 && money.getSilver() >= 15) {
                            money.setSilver(money.getSilver() - unit4.getCost());
                        } else {
                            money.converter(unit4.getCost() / 2.0);
                        }
                    } else {
                        System.out.println("Не хватает запасов");
                    }
                    break;
                case 'O':
                    if (money.getMoney() >= 9.5 || money.getSilver() >= 19) {
                        Hero unit5 = new Hero('O', 25, 3, 3, 4, 4, 19);
                        battlefield.addHero(unit5, i, 9);
                        this.playerList.add(unit5);
                        if (value == 1 && money.getSilver() >= 19) {
                            money.setSilver(money.getSilver() - unit5.getCost());
                        } else {
                            money.converter(unit5.getCost() / 2.0);
                        }
                    } else {
                        System.out.println("Не хватает запасов");
                    }
                    break;
                case 'W':
                    if (money.getMoney() >= 23 && value == 0) {
                        Hero unit6 = new Hero('W', 40, 100, 10, 3, 5, 23);
                        battlefield.addHero(unit6, i, 9);
                        this.playerList.add(unit6);
                        money.setMoney(money.getMoney() - unit6.getCost());
                    } else {
                        System.out.println("Этот герой стоит золото");
                    }
                    break;
                case 'K':
                    if (money.getMoney() >= 10 || money.getSilver() >= 20) {
                        Hero unit7 = new Hero('K', 30, 5, 1, 3, 6, 20);
                        battlefield.addHero(unit7, i, 9);
                        this.playerList.add(unit7);
                        if (value == 1 && money.getSilver() >= 20) {
                            money.setSilver(money.getSilver() - unit7.getCost());
                        } else {
                            money.setMoney(money.getMoney() - unit7.getCost() / 2);
                        }
                    } else {
                        System.out.println("Не хватает запасов");
                    }
                    break;
                case 'C':
                    if (money.getMoney() >= 11.5 || money.getSilver() >= 23) {
                        Hero unit8 = new Hero('C', 50, 2, 1, 7, 5, 23);
                        battlefield.addHero(unit8, i, 9);
                        this.playerList.add(unit8);
                        if (value == 1 && money.getSilver() >= 23) {
                            money.setSilver(money.getSilver() - unit8.getCost());
                        } else {
                            money.converter(unit8.getCost() / 2.0);
                        }

                    } else {
                        System.out.println("Не хватает запасов");
                    }
                    break;
                case 'H':
                    if (money.getMoney() >= 25 && value == 0) {
                        Hero unit9 = new Hero('H', 25, 3, 3, 2, 5, 25);
                        battlefield.addHero(unit9, i, 9);
                        this.playerList.add(unit9);
                        money.setMoney(money.getMoney() - unit9.getCost());
                    } else {
                        System.out.println("Этот герой стоит золото");
                    }
                    break;
                default:
                    System.out.println("Такого героя нельзя выбрать. Введите заново символ героя");
                    i--;
                    break;
            }
            System.out.println("\nВ Вашем сундуке осталось " + money.getMoney() + " золота и " + money.getSilver() + " серебра");
        }
        // return playerList;
    }

    public ArrayList<Hero> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(Hero unit) {
        this.playerList.add(unit);
    }
}

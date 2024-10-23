package Game;

import Battlefield.Battlefield;
import Unit.Hero;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Bot {
    private ArrayList<Hero> botList;

    public Bot() {
        this.botList = new ArrayList<>();
    }

    public void botChoice(Battlefield battlefield, Money botMoney) {
        if (botMoney.getMoney() >= 10 || botMoney.getSilver() >= 20) {
            int heroName = 10;
            int x = 0;
            while (heroName > 0) {
                switch (heroName) {
                    case 1: //s
                        if (botMoney.getMoney() >= 5 || botMoney.getSilver() >= 10) {
                            Hero botsunit1 = new Hero('1', 50, 5, 1, 8, 3, 10);
                            battlefield.addHero(botsunit1, x, 0);
                            this.botList.add(botsunit1);
                            if (botMoney.getSilver() >= 10) {
                                botMoney.setSilver(botMoney.getSilver() - botsunit1.getCost());
                            } else {
                                botMoney.setMoney(botMoney.getMoney() - botsunit1.getCost() / 2);
                            }
                            x++;
                            heroName = 0;
                        } else {
                            heroName = 0;
                        }
                        break;
                    case 2: //b
                        if (botMoney.getMoney() >= 7.5 || botMoney.getSilver() >= 15) {
                            Hero botsunit2 = new Hero('2', 30, 6, 5, 8, 2, 15);
                            battlefield.addHero(botsunit2, x, 0);
                            this.botList.add(botsunit2);
                            if (botMoney.getSilver() >= 15) {
                                botMoney.setSilver(botMoney.getSilver() - botsunit2.getCost());
                            } else {
                                botMoney.converter(botsunit2.getCost() / 2.0);
                            }
                            x++;
                            heroName = 0;
                        } else {
                            heroName = 0;
                        }
                        break;
                    case 3: //k
                        if (botMoney.getMoney() >= 10 || botMoney.getSilver() >= 20) {
                            Hero botsunit3 = new Hero('3', 30, 5, 1, 3, 6, 20);
                            battlefield.addHero(botsunit3, x, 0);
                            this.botList.add(botsunit3);
                            if (botMoney.getSilver() >= 20) {
                                botMoney.setSilver(botMoney.getSilver() - botsunit3.getCost());
                            } else {
                                botMoney.setMoney(botMoney.getMoney() - botsunit3.getCost() / 2);
                            }
                            x++;
                            heroName = 0;
                        } else {
                            heroName = 0;
                        }
                        break;
                    case 4:
                        if (botMoney.getMoney() >= 7.5 || botMoney.getSilver() >= 15) {
                            Hero botsunit4 = new Hero('4', 35, 3, 1, 4, 6, 15);
                            battlefield.addHero(botsunit4, x, 0);
                            this.botList.add(botsunit4);
                            if (botMoney.getSilver() >= 15) {
                                botMoney.setSilver(botMoney.getSilver() - botsunit4.getCost());
                            } else {
                                botMoney.converter( botsunit4.getCost() / 2.0);
                            }
                            x++;
                            heroName = ThreadLocalRandom.current().nextInt(1, 4);
                        } else {
                            heroName = 1;
                        }
                        break;
                    case 5:
                        if (botMoney.getMoney() >= 9.5 || botMoney.getSilver() >= 19) {
                            Hero botsunit5 = new Hero('5', 25, 3, 3, 4, 4, 19);
                            battlefield.addHero(botsunit5, x, 0);
                            this.botList.add(botsunit5);
                            if (botMoney.getSilver() >= 19) {
                                botMoney.setSilver(botMoney.getSilver() - botsunit5.getCost());
                            } else {
                                botMoney.converter( botsunit5.getCost() / 2.0);
                            }
                            x++;
                            heroName = ThreadLocalRandom.current().nextInt(1, 4);
                        } else {
                            heroName = 2;
                        }
                        break;
                    case 6:
                        if (botMoney.getMoney() >= 11.5 || botMoney.getSilver() >= 23) {
                            Hero botsunit6 = new Hero('6', 50, 2, 1, 7, 5, 23);
                            battlefield.addHero(botsunit6, x, 0);
                            this.botList.add(botsunit6);
                            if (botMoney.getSilver() >= 23) {
                                botMoney.setSilver(botMoney.getSilver() - botsunit6.getCost());
                            } else {
                                botMoney.converter(botsunit6.getCost() / 2.0);
                            }
                            x++;
                            heroName = ThreadLocalRandom.current().nextInt(1, 4);
                        } else {
                            heroName = 3;
                        }
                        break;
                    case 7:
                        if (botMoney.getMoney() >= 20) {
                            Hero botsunit7 = new Hero('7', 45, 9, 1, 3, 4, 20);
                            botMoney.setMoney(botMoney.getMoney() - botsunit7.getCost());
                            battlefield.addHero(botsunit7, x, 0);
                            this.botList.add(botsunit7);
                            x++;
                            heroName = ThreadLocalRandom.current().nextInt(4, 7);
                        } else {
                            heroName = 4;
                        }
                        break;

                    case 8:
                        if (botMoney.getMoney() >= 23) {
                            Hero botsunit8 = new Hero('8', 40, 7, 6, 3, 2, 23);
                            botMoney.setMoney(botMoney.getMoney() - botsunit8.getCost());
                            battlefield.addHero(botsunit8, x, 0);
                            this.botList.add(botsunit8);
                            x++;
                            heroName = ThreadLocalRandom.current().nextInt(4, 7);
                        } else {
                            heroName = 5;
                        }
                        break;
                    case 9:
                        if (botMoney.getMoney() >= 25) {
                            Hero botsunit9 = new Hero('9', 25, 3, 3, 2, 5, 25);
                            botMoney.setMoney(botMoney.getMoney() - botsunit9.getCost());
                            battlefield.addHero(botsunit9, x, 0);
                            this.botList.add(botsunit9);
                            x++;
                            heroName = ThreadLocalRandom.current().nextInt(4, 7);
                        } else {
                            heroName = 6;
                        }
                        break;
                    case 10: //BOSS
                        if (botMoney.getSilver() >= 40) {
                            Hero botsunit10 = new Hero('Б', 55, 10, 6, 10, 5, 40);
                            botMoney.setSilver(botMoney.getSilver() - botsunit10.getCost());
                            battlefield.addHero(botsunit10, x, 0);
                            this.botList.add(botsunit10);
                            x++;
                            heroName = ThreadLocalRandom.current().nextInt(7, 10);
                        }
                }
            }
        }
        System.out.println("\nВ сундуке противника осталось " + botMoney.getMoney() + " золота и " + botMoney.getSilver() + " серебра");
        //return botList;
    }

    public ArrayList<Hero> getBotList() {
        return botList;
    }
}

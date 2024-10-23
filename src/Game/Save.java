package Game;

import Battlefield.Battlefield;
import Battlefield.MyPenalty;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Save {
    private File folder;

    public Save() {
        this.folder = new File("Карты");
    }

    public void outlist() {
        for (File f : this.folder.listFiles()) {
            System.out.println(f.getName());
        }
    }

    public void map(Battlefield battlefield, ArrayList<MyPenalty> myPenaltyArrayList) {
        Scanner in = new Scanner(System.in);

        int flag = 1;
        while (flag == 1) {
            System.out.println("Вы хотите загрузить созданную карту (0), создать новую карту (1), отредактировать карту (2) или удалить карту(3)");
            int choice = in.nextInt();
            switch (choice) {
                case 0:
                    outlist();
                    downloadFile(battlefield);
                    // System.out.println( battlefield.getName());
                    downloadPenalty(battlefield.getName(), myPenaltyArrayList);
                    // battlefield.setField(downloadFile().getField());
                    flag = 0;
                    break;
                case 1:
                    battlefield.draw();
                    System.out.println("Хотите ли Вы отредактировать поле? 0, если да. Любую другую цифру, если нет");
                    int choice1 = in.nextInt();
                    if (choice1 == 0) {
                        battlefield.barrier(myPenaltyArrayList);
                    }
                    createFile(battlefield, myPenaltyArrayList);
                    savePenalty(myPenaltyArrayList, battlefield);
                    break;
                case 2:
                    outlist();
                    downloadFile(battlefield);
                    downloadPenalty(battlefield.getName(), myPenaltyArrayList);
                    battlefield.barrier(myPenaltyArrayList);
                    createFile(battlefield, myPenaltyArrayList);
                    savePenalty(myPenaltyArrayList, battlefield);
                    break;
                case 3:
                    System.out.println("Какую карту Вы хотите удалить?");
                    outlist();
                    String choice3 = in.nextLine();
                    String name = in.nextLine();
                    File f = new File(folder, name);
                    try {
                        f.delete();
                        System.out.println("Карта удалена");
                        // Files.delete(Paths.get(choice3));
                    } catch (Exception e) {
                        System.out.println("Ошибка с удалением файла");
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    public void createFile(Battlefield battlefield, ArrayList<MyPenalty> myPenaltyArrayList) {
        Scanner in = new Scanner(System.in);
        System.out.println("Как Вы хотите назвать карту?");
        String fname = in.nextLine();
        try {
            FileOutputStream file = new FileOutputStream(new File(this.folder, fname));
            ObjectOutputStream objectOut = new ObjectOutputStream(file);
            objectOut.writeObject(battlefield);
            objectOut.writeObject(myPenaltyArrayList);
            objectOut.close();
            file.close();
            System.out.println("Карта сохранена");
            battlefield.setName(fname);
        } catch (Exception e) {
            System.out.println("Ошибка с файлом");
            e.printStackTrace();
        }
    }

    public void downloadFile(Battlefield battlefield) {
        Scanner in = new Scanner(System.in);
        System.out.println("Какую карту Вы хотите загрузить?");
        String fname = in.nextLine();
        Battlefield newMap = new Battlefield();
        try {
            FileInputStream file = new FileInputStream(new File(this.folder, fname));
            ObjectInputStream objectIn = new ObjectInputStream(file);
            newMap = (Battlefield) objectIn.readObject();
            objectIn.close();
            file.close();
            System.out.println("Карта " + fname);
            battlefield.setName(fname);
            battlefield.setField(newMap.getField());
            battlefield.draw();
            newMap = null;

        } catch (Exception e) {
            System.out.println("Ошибка с загрузкой файла");
            e.printStackTrace();
        }
    }

    public void savePenalty(ArrayList<MyPenalty> myPenaltyArrayList, Battlefield battlefield) {
        try {
            FileOutputStream file = new FileOutputStream(new File("Штрафы", battlefield.getName()));
            ObjectOutputStream objectOut = new ObjectOutputStream(file);
            objectOut.writeObject(myPenaltyArrayList);
            objectOut.close();
            file.close();
        } catch (Exception e) {
            System.out.println("Ошибка с сохранением штрафов");
            e.printStackTrace();
        }
    }

    public void downloadPenalty(String fname, ArrayList<MyPenalty> myPenaltyArrayList) {
        try {
            ArrayList<MyPenalty> newObj = new ArrayList<MyPenalty>();
            FileInputStream file = new FileInputStream(new File("Штрафы", fname));
            ObjectInputStream objectIn = new ObjectInputStream(file);
            newObj = (ArrayList<MyPenalty>) objectIn.readObject();
            objectIn.close();
            file.close();
            if (!(myPenaltyArrayList.size() == newObj.size())) {
                myPenaltyArrayList.addAll(newObj);
            }
            newObj = null;
        } catch (Exception e) {
            System.out.println("Ошибка с загрузкой штрафов");
            e.printStackTrace();
        }
    }
/*
    public void savePenalty(MyPenalty myPenalty, Battlefield battlefield) {
        try {
            FileOutputStream file = new FileOutputStream(new File("Штрафы", battlefield.getName()));
            ObjectOutputStream objectOut = new ObjectOutputStream(file);
            objectOut.writeObject(myPenalty);
            objectOut.close();
            file.close();
        } catch (Exception e) {
            System.out.println("Ошибка с сохранением штрафов");
            e.printStackTrace();
        }
    }
    public void downloadPenalty(String fname, MyPenalty myPenalty) {
        try {

            MyPenalty newObj = new MyPenalty();
            FileInputStream file = new FileInputStream(new File("Штрафы", fname));
            ObjectInputStream objectIn = new ObjectInputStream(file);
            newObj = (MyPenalty) objectIn.readObject();
            objectIn.close();
            file.close();
            myPenalty.setSymbol(newObj.getSymbol());
            myPenalty.setArcherPenalty(newObj.getArcherPenalty());
            myPenalty.setOtherPenalty(newObj.getOtherPenalty());
            myPenalty.setWarriorPenalty(newObj.getWarriorPenalty());
            newObj = null;
            // myPenalty = newObj;
        } catch (Exception e) {
            System.out.println("Ошибка с загрузкой штрафов");
            e.printStackTrace();
        }
    }
 */
}

import Cat.Cat;
import Cat.ServiceFile;

import java.util.Random;
import java.util.Scanner;

public class Game {
    public static ServiceFile serviceFile;

    public void userInteraction() {
        boolean check = true;
        while (check) {
            try {
                System.out.println("""
                        [1] - Завести нового питомца        [2] - Поиграть
                        [3] - Покормить                     [4] - Сходить к ветеринару
                        [5] - Следующий день                [0] - Закончить симуляцию""");
                int question = new Scanner(System.in).nextInt();
                int num = 0;
                switch (question) {
                    case 1 -> {
                        System.out.println("[Добавление нового кота..]");
                        addNewCat();
                        serviceFile.printCats();
                        userInteraction();
                    }
                    case 2 -> {
                        System.out.println("[Симуляция игры с котом..]");
                        serviceFile = ServiceFile.read();
                        serviceFile.printCats();
                        num = chooseCat();
                        serviceFile.getCatList().get(num - 1).play();
                        serviceFile.printCats();
                    }
                    case 3 -> {
                        System.out.println("[Симуляция кормления кота..]");
                        serviceFile = ServiceFile.read();
                        serviceFile.printCats();
                        num = chooseCat();
                        serviceFile.getCatList().get(num - 1).eat();
                        serviceFile.printCats();
                    }
                    case 4 -> {
                        System.out.println("[Симуляция лечения..]");
                        serviceFile = ServiceFile.read();
                        serviceFile.printCats();
                        num = chooseCat();
                        serviceFile.getCatList().get(num - 1).treat();
                        serviceFile.printCats();
                    }
                    case 5 -> {
                        System.out.println("[Переход на следующий день..]");
                        serviceFile = ServiceFile.read();
                        serviceFile.getCatList().forEach(Cat::nextDay);
                        System.out.println("Наступил следующий день...");
                        System.out.println("#".repeat(100));
                        serviceFile.printCats();
                    }
                    case 0 -> check = false;
                }
            } catch (Exception e) {
                System.out.println("Введите соответствующий номер!");
                check = true;
            }
        }
    }

    public static void addNewCat() {
        serviceFile = ServiceFile.read();
        System.out.print("Введите имя кота: ");
        String name = new Scanner(System.in).nextLine();
        System.out.print("Введите возраст кота: ");
        int age = new Scanner(System.in).nextInt();
        try {
            Cat cat = new Cat.Create()
                    .setName(name)
                    .setAge(age)
                    .setSatiety(new Random().nextInt(80) + 20)
                    .setMood(new Random().nextInt(80) + 20)
                    .setHealth(new Random().nextInt(80) + 20)
                    .setAverage()
                    .create();
            serviceFile.addNewCat(cat);
            cat.setChoosable();
            System.out.println("Вы завели нового питомца по имени - " + name);
            System.out.println("_".repeat(100));
        } catch (Exception e) {
            System.out.println("Вы ввели неправильные данные кота!");
        }
    }

    private int chooseCat() {
        try {
            System.out.print("Введите номер кота: ");
            return new Scanner(System.in).nextInt();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

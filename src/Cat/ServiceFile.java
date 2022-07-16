package Cat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ServiceFile {
    ArrayList<Cat> cats;
    public List<Cat> catList;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = Paths.get("./cats.json");
    public ServiceFile() {
        try {
            cats = new ArrayList<>();
            catList = List.of(GSON.fromJson(Files.readString(PATH), Cat[].class));
            cats.addAll(catList);
            doSort();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ServiceFile read() {
        return new ServiceFile();
    }
    public List<Cat> getCatList() {
        writeFile(cats);
        cats.forEach(Cat::setChoosable);
        cats.forEach(Cat::setAverage);
        return cats;
    }

    public void addNewCat (Cat cat) {
        cats.add(cat);
        writeFile(cats);
        doSort();
    }
    public void writeFile(ArrayList<Cat> cats) {
        String json = GSON.toJson(cats);
        try {
            byte[] arr = json.getBytes();
            Files.write(PATH,arr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doSort() {
        cats.sort(Comparator.comparing(Cat::getAverage).reversed());
    }

    public void printCats() {
        int count = 1;
        System.out.println("+" + "-".repeat(90) + "+");
        System.out.printf("| %-2s | %-12s | %-10s | %-10s | %-10s | %-10s | %-16s |\n", "#","Имя","Возраст","Здоровье","Настроение","Сытость","Средний уровень");
        System.out.println("| ---+--------------+------------+------------+------------+------------+------------------|");
        for (Cat c : cats) {
            System.out.println(c.printCat(count));
            count++;
        }
        System.out.println("+" + "-".repeat(90) + "+");

    }
}

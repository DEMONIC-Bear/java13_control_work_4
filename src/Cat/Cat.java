package Cat;

import State.Adult;
import State.Choosable;
import State.Medium;
import State.Young;

import java.util.Random;

public class Cat {
    private String name;
    private int age;
    private int health;
    private int mood;
    private int satiety;
    private int average;
    private Choosable choosable;
    boolean eat = false;
    boolean play = false;
    boolean treat = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public int getSatiety() {
        return satiety;
    }

    public void setSatiety(int satiety) {
        this.satiety = satiety;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage() {
        average = (health + mood + satiety) / 3;
    }

    public static class Create {
        private String name;
        private int age;
        private int health;
        private int mood;
        private int satiety;
        private int average;

        public Create setName(String name) {
            this.name = name;
            return this;
        }

        public Create setAge(int age) throws Exception {
            errorChecking(age);
            this.age = age;
            return this;
        }

        private void errorChecking(int i) throws Exception {
            if (i < 1 || i > 18) {
                throw new Exception();
            }
        }

        public Create setHealth(int health) {
            this.health = health;
            return this;
        }

        public Create setMood(int mood) {
            this.mood = mood;
            return this;
        }

        public Create setSatiety(int satiety) {
            this.satiety = satiety;
            return this;
        }

        public Create setAverage() {
            this.average = (health + satiety + mood) / 3;
            return this;
        }

        public Cat create() {
            Cat cat = new Cat();
            cat.name = this.name;
            cat.age = this.age;
            cat.health = this.health;
            cat.mood = this.mood;
            cat.satiety = this.satiety;
            cat.average = this.average;
            return cat;
        }
    }

    public void setChoosable() {
        if (age <= 5) {
            choosable = new Young();
        } else if (age <= 10) {
            choosable = new Medium();
        } else {
            choosable = new Adult();
        }
    }
    
    public void eat() {
        if (eat) {
            System.out.println(name.replace("     *","") + " сегодн€ уже покушал!");
            System.out.println("#".repeat(100));
        } else {
            choosable.eat(this);
            System.out.println("¬ы покормили " + name);
            System.out.println("#".repeat(100));
            name = name + "     *";
        }
        eat = true;

    }

    public void play() {
        if (play) {
            System.out.println(name.replace("     *","") + " сегодн€ уже играл");
            System.out.println("#".repeat(100));
        } else {
            choosable.play(this);
            System.out.println("¬ы поиграли с " + name);
            System.out.println("#".repeat(100));
            name = name + "     *";
        }
        play = true;
    }

    public void treat() {
        if (treat) {
            System.out.println(name.replace("     *","") + " сегодн€ уже был у ветеринара");
            System.out.println("#".repeat(100));
        } else {
            choosable.treat(this);
            System.out.println("¬ы повезли " + name + " к ветеринару");
            System.out.println("#".repeat(100));
            name = name + "     *";
        }
        treat = true;
    }

    public void nextDay() {
        setSatiety(getSatiety() - (new Random().nextInt(5) + 1));
        setMood(getMood() + (new Random().nextInt(4) - 3));
        setHealth(getHealth() + (new Random().nextInt(4) - 3));
        setAverage();
        name = name.replace("     *","");
        eat = false;
        play = false;
        treat = false;
    }


    public String printCat(int i) {
        return String.format("| %-2s | %-12s | %-10s | %-10s | %-10s | %-10s | %-16s |", i, name, age, health, mood, satiety, average);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", health=" + health +
                ", mood=" + mood +
                ", satiety=" + satiety +
                ", average=" + average +
                ", choosable=" + choosable +
                ", eat=" + eat +
                ", play=" + play +
                ", treat=" + treat +
                '}';
    }
}

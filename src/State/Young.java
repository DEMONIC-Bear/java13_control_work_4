package State;

import Cat.Cat;

public class Young implements Choosable{
    @Override
    public void eat(Cat cat) {
        cat.setSatiety(cat.getSatiety() + 7);
        cat.setMood(cat.getMood() + 7);
        cat.setAverage();
    }

    @Override
    public void play(Cat cat) {
        cat.setSatiety(cat.getSatiety() - 3);
        cat.setMood(cat.getMood() + 7);
        cat.setHealth(cat.getHealth() + 7);
        cat.setAverage();
    }

    @Override
    public void treat(Cat cat) {
        cat.setSatiety(cat.getSatiety() - 3);
        cat.setMood(cat.getMood() - 3);
        cat.setHealth(cat.getHealth() + 7);
        cat.setAverage();
    }
}

package org.example;

public class Cat extends Animal {
    private boolean isFull;
    private static int catCount = 0;
    private static final int APPETITE = 10;

    public Cat(String name) {
        super(name, 200, 0);
        this.isFull = false;
        catCount++;
    }

    public static int getCatCount() {
        return catCount;
    }

    public boolean isFull() {
        return isFull;
    }

    public void eat(Bowl bowl) {
        if (bowl.getFood() >= APPETITE) {
            bowl.decreaseFood(APPETITE);
            isFull = true;
            System.out.println(name + " покушал. Сытость: " + isFull);
        } else {
            System.out.println(name + " не стал есть, в миске мало еды (нужно " + APPETITE + ").");
        }
    }
}
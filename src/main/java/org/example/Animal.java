package org.example;

public abstract class Animal {
    protected String name;
    protected int maxRun;
    protected int maxSwim;

    protected static int animalCount = 0;

    public Animal(String name, int maxRun, int maxSwim) {
        this.name = name;
        this.maxRun = maxRun;
        this.maxSwim = maxSwim;
        animalCount++;
    }

    public void run(int distance) {
        if (distance <= maxRun) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать " + distance + " м. (Максимум: " + maxRun + " м.)");
        }
    }

    public void swim(int distance) {
        if (maxSwim == 0) {
            System.out.println(name + " не умеет плавать.");
        } else if (distance <= maxSwim) {
            System.out.println(name + " проплыл " + distance + " м.");
        } else {
            System.out.println(name + " не может проплыть " + distance + " м. (Максимум: " + maxSwim + " м.)");
        }
    }
}
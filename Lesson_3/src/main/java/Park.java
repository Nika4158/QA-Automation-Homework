package org.example;

import java.util.ArrayList;
import java.util.List;

public class Park {

    public class Attraction {
        String name;
        String workingHours;
        double cost;

        public Attraction(String name, String workingHours, double cost) {
            this.name = name;
            this.workingHours = workingHours;
            this.cost = cost;
        }

        public void printInfo() {
            System.out.println("Аттракцион: " + name);
            System.out.println("Время работы: " + workingHours);
            System.out.println("Стоимость: " + cost + " руб.");
            System.out.println("-------------------------");
        }
    }

    private List<Attraction> attractions = new ArrayList<>();

    public void addAttraction(String name, String workingHours, double cost) {
        // Создаем объект внутреннего класса
        Attraction attraction = new Attraction(name, workingHours, cost);
        attractions.add(attraction);
    }

    public void printAllAttractions() {
        System.out.println(" Аттракционы парка ");
        for (Attraction attraction : attractions) {
            attraction.printInfo();
        }
    }
}
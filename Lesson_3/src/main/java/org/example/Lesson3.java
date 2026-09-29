package org.example;

public class Lesson3 {
    public static void main(String[] args) {
        System.out.println("Задание 2: Товары ");
        Product[] productsArray = new Product[5];

        productsArray[0] = new Product("Samsung S25 Ultra", "01.02.2025", "Samsung Corp.", "Korea", 5599, true);
        productsArray[1] = new Product("iPhone 16 Pro", "15.09.2024", "Apple Inc.", "USA", 1299, false);
        productsArray[2] = new Product("Xiaomi 15", "10.10.2024", "Xiaomi", "China", 899, true);
        productsArray[3] = new Product("Google Pixel 9", "20.08.2024", "Google", "USA", 999, false);
        productsArray[4] = new Product("Sony Xperia 1 VI", "01.06.2024", "Sony", "Japan", 1399, true);

        for (Product product : productsArray) {
            product.printInfo();
        }

        System.out.println("\nЗадание 3: Парк ");
        Park park = new Park();

        park.addAttraction("Колесо обозрения", "10:00 - 22:00", 500);
        park.addAttraction("Американские горки", "11:00 - 21:00", 800);
        park.addAttraction("Комната страха", "12:00 - 20:00", 400);

        park.printAllAttractions();
    }
}
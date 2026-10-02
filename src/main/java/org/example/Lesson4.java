package org.example;

public class Lesson4 {
    public static void main(String[] args) {
        System.out.println("Задание 1: Животные");

        Dog dog = new Dog("Бобик");
        dog.run(150);
        dog.run(600);
        dog.swim(5);
        dog.swim(15);

        Cat cat = new Cat("Барсик");
        cat.run(200);
        cat.run(250);
        cat.swim(5);

        System.out.println("\nВсего животных: " + Animal.animalCount);
        System.out.println("Собак: " + Dog.getDogCount());
        System.out.println("Котов: " + Cat.getCatCount());

        System.out.println("\nКоты и миска");
        Cat[] cats = {
                new Cat("Мурзик"),
                new Cat("Васька"),
                new Cat("Рыжик")
        };

        Bowl bowl = new Bowl(25);

        for (Cat c : cats) {
            c.eat(bowl);
        }

        System.out.println("\nИнформация о сытости");
        for (Cat c : cats) {
            System.out.println(c.name + " сыт: " + c.isFull());
        }

        bowl.addFood(10);
        System.out.println("Рыжик пробует поесть снова:");
        cats[2].eat(bowl);
        System.out.println("Рыжик сыт: " + cats[2].isFull());

        System.out.println("\nЗадание 2: Геометрические фигуры");

        Shape circle = new Circle(5, "Красный", "Синий");
        Shape rectangle = new Rectangle(4, 6, "Розовый", "Голубой");
        Shape triangle = new Triangle(3, 4, 5, "Зеленый", "Красный");

        System.out.println("Круг:");
        circle.printCharacteristics();

        System.out.println("Прямоугольник:");
        rectangle.printCharacteristics();

        System.out.println("Треугольник:");
        triangle.printCharacteristics();
    }
}

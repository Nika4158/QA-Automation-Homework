package org.example;

public interface Shape {
    double getArea();
    double getPerimeter();

    String getFillColor();
    void setFillColor(String color);

    String getBorderColor();
    void setBorderColor(String color);

    default void printCharacteristics() {
        System.out.printf("Периметр: %.1f\n", getPerimeter());
        System.out.printf("Площадь: %.1f\n", getArea());
        System.out.println("Цвет фона: " + getFillColor());
        System.out.println("Цвет границы: " + getBorderColor());
        System.out.println("-------------------------");
    }
}

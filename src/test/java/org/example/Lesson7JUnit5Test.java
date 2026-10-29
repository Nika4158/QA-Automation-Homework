package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Lesson7JUnit5Test {

    @Test
    @DisplayName("Тест факториала")
    void testFactorial() {
        assertEquals(1, Lesson7.calculateFactorial(0));
        assertEquals(120, Lesson7.calculateFactorial(5));
        assertThrows(IllegalArgumentException.class, () -> Lesson7.calculateFactorial(-1));
    }

    @Test
    @DisplayName("Тест площади треугольника")
    void testTriangleArea() {
        // Для треугольника со сторонами 3, 4, 5 площадь = 6.0
        assertEquals(6.0, Lesson7.calculateTriangleArea(3, 4, 5), 0.0001);
        // Проверка на несуществующий треугольник
        assertThrows(IllegalArgumentException.class, () -> Lesson7.calculateTriangleArea(1, 2, 10));
    }

    @Test
    @DisplayName("Тест арифметических операций")
    void testArithmetic() {
        assertEquals(5, Lesson7.add(2, 3));
        assertEquals(-1, Lesson7.subtract(2, 3));
        assertEquals(6, Lesson7.multiply(2, 3));
        assertEquals(2.0, Lesson7.divide(6, 3), 0.0001);
        assertThrows(ArithmeticException.class, () -> Lesson7.divide(5, 0));
    }

    @Test
    @DisplayName("Тест сравнения чисел")
    void testCompareNumbers() {
        assertEquals("a > b", Lesson7.compareNumbers(5, 3));
        assertEquals("a < b", Lesson7.compareNumbers(3, 5));
        assertEquals("a == b", Lesson7.compareNumbers(4, 4));
    }
}
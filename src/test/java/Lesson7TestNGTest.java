package org.example;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class Lesson7TestNGTest {

    @Test
    public void testFactorial() {
        assertEquals(Lesson7.calculateFactorial(0), 1);
        assertEquals(Lesson7.calculateFactorial(5), 120);
        assertThrows(IllegalArgumentException.class, () -> Lesson7.calculateFactorial(-1));
    }

    @Test
    public void testTriangleArea() {
        assertEquals(Lesson7.calculateTriangleArea(3, 4, 5), 6.0, 0.0001);
        assertThrows(IllegalArgumentException.class, () -> Lesson7.calculateTriangleArea(1, 2, 10));
    }

    @Test
    public void testArithmetic() {
        assertEquals(Lesson7.add(2, 3), 5);
        assertEquals(Lesson7.subtract(2, 3), -1);
        assertEquals(Lesson7.multiply(2, 3), 6);
        assertEquals(Lesson7.divide(6, 3), 2.0, 0.0001);
        assertThrows(ArithmeticException.class, () -> Lesson7.divide(5, 0));
    }

    @Test
    public void testCompareNumbers() {
        assertEquals(Lesson7.compareNumbers(5, 3), "a > b");
        assertEquals(Lesson7.compareNumbers(3, 5), "a < b");
        assertEquals(Lesson7.compareNumbers(4, 4), "a == b");
    }
}
package org.example;

public class Lesson2 {

    public static void main(String[] args) {
        System.out.println("--- Задание 1 ---");
        printThreeWords();

        System.out.println("\n--- Задание 2 ---");
        checkSumSign();

        System.out.println("\n--- Задание 3 ---");
        printColor();

        System.out.println("\n--- Задание 4 ---");
        compareNumbers();

        System.out.println("\n--- Задание 5 ---");
        System.out.println("Сумма 5 и 7 в диапазоне 10-20? " + isSumInRange(5, 7));

        System.out.println("\n--- Задание 6 ---");
        printSign(0);

        System.out.println("\n--- Задание 7 ---");
        System.out.println("Число -5 отрицательное? " + isNegative(-5));

        System.out.println("\n--- Задание 8 ---");
        printStringMultipleTimes("Java", 3);

        System.out.println("\n--- Задание 9 ---");
        System.out.println("2024 високосный? " + isLeapYear(2024));

        System.out.println("\n--- Задание 10 ---");
        int[] binaryArray = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        invertArray(binaryArray);
        printArray(binaryArray);

        System.out.println("\n--- Задание 11 ---");
        int[] filledArray = fillArray();
        printArray(filledArray);

        System.out.println("\n--- Задание 12 ---");
        int[] numbersArray = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        multiplyLessThanSix(numbersArray);
        printArray(numbersArray);

        System.out.println("\n--- Задание 13 ---");
        int[][] matrix = new int[5][5];
        fillDiagonals(matrix);
        printMatrix(matrix);

        System.out.println("\n--- Задание 14 ---");
        int[] customArray = createArray(5, 7);
        printArray(customArray);
    }

    // 1. Вывод трех слов в столбец
    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    // 2. Проверка суммы
    public static void checkSumSign() {
        int a = 10;
        int b = -15;
        int sum = a + b;
        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    // 3. Цвет по значению
    public static void printColor() {
        int value = 50;
        if (value < 0) {
            System.out.println("Красный");
        } else if (value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    // 4. Сравнение чисел
    public static void compareNumbers() {
        int a = 5;
        int b = 5;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    // 5. Сумма в диапазоне 10-20
    public static boolean isSumInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    // 6. Положительное или отрицательное (0 - положительное)
    public static void printSign(int number) {
        if (number >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }
    }

    // 7. Вернуть true, если отрицательное
    public static boolean isNegative(int number) {
        return number < 0; // 0 не меньше 0, значит вернется false (положительное)
    }

    // 8. Вывод строки N раз
    public static void printStringMultipleTimes(String str, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(str);
        }
    }

    // 9. Високосный год
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // 10. Инвертировать 0 и 1
    public static void invertArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
            } else {
                arr[i] = 0;
            }
        }
    }

    // 11. Заполнить массив 1..100
    public static int[] fillArray() {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    // 12. Умножить числа < 6 на 2
    public static void multiplyLessThanSix(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }
    }

    // 13. Диагонали матрицы
    public static void fillDiagonals(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][i] = 1;
            matrix[i][matrix.length - 1 - i] = 1;
        }
    }

    // 14. Создать массив с заданным значением
    public static int[] createArray(int len, int initialValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = initialValue;
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println("]");
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
package org.example;

public class Lesson5 {

    public static int processArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4) {
            throw new MyArraySizeException("Неверный размер массива: строк должно быть 4, а получено " + array.length);
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new MyArraySizeException("Неверный размер массива: в строке " + i + " должно быть 4 столбца, а получено " + array[i].length);
            }
        }

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                String value = array[i][j];
                try {
                    sum += Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j, value);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println("Тест 1: Корректный массив ");
        String[][] goodArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };
        try {
            int result = processArray(goodArray);
            System.out.println("Сумма всех элементов: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\nТест 2: Неверный размер (3x4) ");
        String[][] wrongSizeArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"}
        };
        try {
            int result = processArray(wrongSizeArray);
            System.out.println("Сумма: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }

        System.out.println("\nТест 3: Текст вместо числа ");
        String[][] wrongDataArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "Hello", "12"},
                {"13", "14", "15", "16"}
        };
        try {
            int result = processArray(wrongDataArray);
            System.out.println("Сумма: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }

        System.out.println("\nArrayIndexOutOfBoundsException: ");
        try {
            int[] arr = {1, 2, 3};
            System.out.println("Пытаемся получить 10-й элемент массива...");
            int value = arr[10];
            System.out.println("Этот текст не выведется: " + value);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Пойман ArrayIndexOutOfBoundsException!");
            System.out.println("Сообщение: " + e.getMessage());
            System.out.println("Причина: вы пытаетесь обратиться к элементу, которого не существует.");
        }
    }
}
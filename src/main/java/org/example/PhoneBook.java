package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    // Ключ - фамилия, Значение - список телефонов
    private Map<String, List<String>> phoneBook = new HashMap<>();

    // Метод добавления записи
    public void add(String lastName, String phoneNumber) {
        // Если фамилии еще нет в справочнике, создаем для нее новый список
        // computeIfAbsent делает это автоматически
        phoneBook.computeIfAbsent(lastName, k -> new ArrayList<>()).add(phoneNumber);
        System.out.println("Добавлено: " + lastName + " -> " + phoneNumber);
    }

    // Метод поиска номеров по фамилии
    public List<String> get(String lastName) {
        return phoneBook.getOrDefault(lastName, new ArrayList<>());
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        // Добавляем записи (есть однофамильцы)
        phoneBook.add("Иванов", "+7-900-123-45-67");
        phoneBook.add("Петров", "+7-900-765-43-21");
        phoneBook.add("Иванов", "+7-900-999-88-77"); // Второй Иванов
        phoneBook.add("Сидоров", "+7-900-111-22-33");

        // Ищем телефоны
        System.out.println("\n--- Поиск по фамилии 'Иванов' ---");
        List<String> ivanovPhones = phoneBook.get("Иванов");
        if (ivanovPhones.isEmpty()) {
            System.out.println("Записей не найдено.");
        } else {
            for (String phone : ivanovPhones) {
                System.out.println("Телефон: " + phone);
            }
        }

        System.out.println("\n--- Поиск по фамилии 'Смирнов' ---");
        List<String> smirnovPhones = phoneBook.get("Смирнов");
        if (smirnovPhones.isEmpty()) {
            System.out.println("Записей не найдено.");
        }
    }
}
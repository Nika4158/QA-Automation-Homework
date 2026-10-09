package org.example;

import java.util.*;

public class Lesson6 {

    // 1. Метод удаления студентов со средним баллом < 3
    public static void removeBadStudents(Set<Student> students) {
        // Используем Iterator, так как удалять из коллекции во время цикла for-each нельзя!
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getAverageGrade() < 3) {
                System.out.println("Отчислен: " + student.getName() + " (ср. балл: " + String.format("%.1f", student.getAverageGrade()) + ")");
                iterator.remove();
            }
        }
    }

    // 2. Метод перевода на следующий курс
    public static void promoteStudents(Set<Student> students) {
        for (Student student : students) {
            if (student.getAverageGrade() >= 3) {
                student.setCourse(student.getCourse() + 1);
                System.out.println("Переведен на следующий курс: " + student.getName() + " (теперь " + student.getCourse() + " курс)");
            }
        }
    }

    // 3. Метод печати студентов определенного курса
    public static void printStudents(Set<Student> students, int course) {
        System.out.println("\n--- Студенты " + course + " курса ---");
        boolean found = false;
        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println(student.getName());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Студентов на этом курсе нет.");
        }
    }

    public static void main(String[] args) {
        // Создаем коллекцию студентов (Set, чтобы не было дубликатов)
        Set<Student> students = new HashSet<>();

        students.add(new Student("Иванов", "ИС-21", 1, Arrays.asList(4, 5, 4, 5)));
        students.add(new Student("Петров", "ИС-21", 1, Arrays.asList(2, 2, 3, 2))); // Ср. балл < 3
        students.add(new Student("Сидоров", "ИС-22", 1, Arrays.asList(3, 3, 3, 3))); // Ровно 3
        students.add(new Student("Смирнова", "ИС-22", 1, Arrays.asList(5, 5, 5, 5)));
        students.add(new Student("Козлов", "ИС-21", 1, Arrays.asList(2, 4, 2, 3)));   // Ср. балл < 3

        System.out.println("=== Исходный список студентов ===");
        for (Student s : students) {
            System.out.println(s);
        }

        // Удаляем неуспевающих
        System.out.println("\n=== Удаление неуспевающих ===");
        removeBadStudents(students);

        // Переводим оставшихся на следующий курс
        System.out.println("\n=== Перевод на следующий курс ===");
        promoteStudents(students);

        // Выводим список студентов 2 курса
        printStudents(students, 2);
    }
}
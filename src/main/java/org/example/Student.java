package org.example;

import java.util.List;
import java.util.Objects;

public class Student {
    private String name;
    private String group;
    private int course;
    private List<Integer> grades; // Оценки по предметам

    public Student(String name, String group, int course, List<Integer> grades) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = grades;
    }

    // Метод для подсчета среднего балла
    public double getAverageGrade() {
        if (grades.isEmpty()) return 0;
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }

    // Геттеры и сеттеры
    public String getName() { return name; }
    public String getGroup() { return group; }
    public int getCourse() { return course; }
    public void setCourse(int course) { this.course = course; }
    public List<Integer> getGrades() { return grades; }

    // Важно для Set: сравнение объектов по имени и группе
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) && Objects.equals(group, student.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, group);
    }

    @Override
    public String toString() {
        return "Студент: " + name + " | Группа: " + group + " | Курс: " + course + " | Ср. балл: " + String.format("%.1f", getAverageGrade());
    }
}
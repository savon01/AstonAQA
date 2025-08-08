package org.example.lesson9;

import java.util.*;
import java.util.stream.Collectors;

public class Lesson9 {
    // Задача 1: Подсчет четных чисел в наборе
    public static int countEvenNumbers(int[] numbers) {
        return (int) Arrays.stream(numbers)
                .filter(num -> num % 2 == 0)
                .count();
    }

    // Задача 2.1: Подсчет вхождений "High" в коллекции
    public static int countHighOccurrences(List<String> collection) {
        return (int) collection.stream()
                .filter(s -> s.equals("High"))
                .count();
    }

    // Задача 2.2: Получение первого элемента коллекции
    public static String getFirstElement(List<String> collection) {
        return collection.isEmpty() ? "0" : collection.get(0);
    }

    // Задача 2.3: Получение последнего элемента коллекции
    public static String getLastElement(List<String> collection) {
        return collection.isEmpty() ? "0" : collection.get(collection.size() - 1);
    }

    // Задача 3: Сортировка строк и добавление в массив
    public static String[] sortStrings(List<String> collection) {
        return collection.stream()
                .sorted()
                .toArray(String[]::new);
    }

    // Задача 4.1: Средний возраст студентов мужского пола
    public static double getAverageMaleAge(List<Student> students) {
        return students.stream()
                .filter(student -> "M".equals(student.getGender()))
                .mapToInt(Student::getAge)
                .average()
                .orElse(0.0);
    }

    // Задача 4.2: Студенты мужского пола в призывном возрасте (18-27 лет)
    public static List<Student> getDraftEligibleStudents(List<Student> students) {
        return students.stream()
                .filter(student -> "M".equals(student.getGender()))
                .filter(student -> student.getAge() >= 18 && student.getAge() <= 27)
                .collect(Collectors.toList());
    }

    // Задача 5: Прием логинов от пользователя и фильтрация по начальной букве 'f'
    public static List<String> filterLoginsStartingWithF() {
        Scanner scanner = new Scanner(System.in);
        List<String> logins = new ArrayList<>();
        System.out.println("Введите логины (пустая строка для завершения):");

        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                break;
            }
            logins.add(input);
        }

        return logins.stream()
                .filter(login -> login.toLowerCase().startsWith("f"))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // Задача 1: Подсчет четных чисел
        Random random = new Random();
        int[] numbers = random.ints(10, 1, 100).toArray(); // 10 случайных чисел от 1 до 100
        System.out.println("Задача 1: Случайные числа: " + Arrays.toString(numbers));
        System.out.println("Количество четных чисел: " + countEvenNumbers(numbers));

        // Задача 2: Работа с коллекцией строк
        List<String> strings = Arrays.asList("Highload", "High", "Load", "Highload");
        System.out.println("\nЗадача 2: Коллекция строк: " + strings);
        System.out.println("2.1 Количество 'High': " + countHighOccurrences(strings));
        System.out.println("2.2 Первый элемент: " + getFirstElement(strings));
        System.out.println("2.3 Последний элемент: " + getLastElement(strings));

        // Проверка пустой коллекции
        List<String> emptyList = new ArrayList<>();
        System.out.println("Пустая коллекция:");
        System.out.println("2.2 Первый элемент (пустая): " + getFirstElement(emptyList));
        System.out.println("2.3 Последний элемент (пустая): " + getLastElement(emptyList));

        // Задача 3: Сортировка строк
        List<String> stringCollection = Arrays.asList("f10", "f15", "f2", "f4", "f4");
        System.out.println("\nЗадача 3: Исходная коллекция: " + stringCollection);
        String[] sortedArray = sortStrings(stringCollection);
        System.out.println("Отсортированный массив: " + Arrays.toString(sortedArray));

        // Задача 4: Работа со студентами
        List<Student> students = Arrays.asList(
                new Student("Иван", 20, "M"),
                new Student("Анна", 19, "F"),
                new Student("Петр", 25, "M"),
                new Student("Мария", 22, "F"),
                new Student("Алексей", 28, "M")
        );
        System.out.println("\nЗадача 4: Список студентов: " + students);
        System.out.println("4.1 Средний возраст студентов мужского пола: " + getAverageMaleAge(students));
        System.out.println("4.2 Студенты, подлежащие призыву: " + getDraftEligibleStudents(students));

        // Задача 5: Фильтрация логинов
        System.out.println("\nЗадача 5: Фильтрация логинов");
        List<String> filteredLogins = filterLoginsStartingWithF();
        System.out.println("Логины, начинающиеся на 'f': " + filteredLogins);
    }
}

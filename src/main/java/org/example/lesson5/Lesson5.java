package org.example.lesson5;

public class Lesson5 {
    public static void main(String[] args) {
        // Задача 1: Животные, коты, собаки, тарелка
        System.out.println("=== Задача 1: Животные ===");
        Dog dog = new Dog("Бобик");
        Cat cat1 = new Cat("Мурзик");
        Cat cat2 = new Cat("Барсик");

        // Тестирование бега и плавания
        dog.run(400);
        dog.swim(5);
        cat1.run(250);
        cat1.swim(10);
        cat2.run(150);

        // Подсчет животных
        System.out.println("Всего животных: " + Animal.getAnimalCount());
        System.out.println("Собак: " + Dog.getDogCount());
        System.out.println("Котов: " + Cat.getCatCount());

        // Коты и тарелка
        Plate plate = new Plate(30);
        Cat[] cats = {cat1, cat2};
        System.out.println("\nКоты едят из тарелки (еда: " + plate.getFood() + "):");
        cat1.eat(plate, 20);
        cat2.eat(plate, 15);

        // Вывод сытости котов
        System.out.println("\nСытость котов:");
        for (Cat cat : cats) {
            System.out.println(cat.isFull() ? cat + " сыт" : cat + " голоден");
        }

        // Добавление еды в тарелку
        plate.addFood(10);
        cat2.eat(plate, 10);

        // Задача 2: Геометрические фигуры
        System.out.println("\n=== Задача 2: Геометрические фигуры ===");
        Shape[] shapes = {
                new Circle(5, "Красный", "Черный"),
                new Rectangle(4, 6, "Синий", "Белый"),
                new Triangle(3, 4, 5, "Зеленый", "Желтый")
        };

        for (Shape shape : shapes) {
            System.out.println("Фигура: " + shape.getClass().getSimpleName());
            System.out.println("Периметр: " + String.format("%.2f", shape.getPerimeter()));
            System.out.println("Площадь: " + String.format("%.2f", shape.getArea()));
            System.out.println("Цвет заливки: " + shape.getFillColor());
            System.out.println("Цвет границы: " + shape.getBorderColor());
            System.out.println("-------------------");
        }
    }
}

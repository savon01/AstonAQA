package org.example.lesson4;

public class Park {
    // Внутренний класс для хранения информации об аттракционе
    public class Attraction {
        private String name;
        private String workingHours;
        private double cost;

        // Конструктор внутреннего класса
        public Attraction(String name, String workingHours, double cost) {
            this.name = name;
            this.workingHours = workingHours;
            this.cost = cost;
        }

        // Метод для вывода информации об аттракционе
        public void printInfo() {
            System.out.println("Аттракцион: " + name);
            System.out.println("Время работы: " + workingHours);
            System.out.println("Стоимость: " + cost + " руб.");
            System.out.println("-------------------");
        }
    }
}

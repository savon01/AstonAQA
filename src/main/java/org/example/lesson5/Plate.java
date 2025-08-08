package org.example.lesson5;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = Math.max(food, 0); // Не допускаем отрицательное количество еды
    }

    public boolean decreaseFood(int amount) {
        if (amount <= food && amount >= 0) {
            food -= amount;
            return true;
        }
        return false;
    }

    public void addFood(int amount) {
        if (amount > 0) {
            food += amount;
            System.out.println("Добавлено " + amount + " еды в тарелку. Теперь еды: " + food);
        } else {
            System.out.println("Нельзя добавить отрицательное количество еды.");
        }
    }

    public int getFood() {
        return food;
    }
}
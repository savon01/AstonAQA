package org.example.lesson5;

public class Cat extends Animal {
    private static int catCount = 0;
    private boolean isFull;

    public Cat(String name) {
        super(name, 200, 0); // Кот: бег 200 м, плавание 0 м
        this.isFull = false; // Голоден при создании
        catCount++;
    }

    public boolean isFull() {
        return isFull;
    }

    public void eat(Plate plate, int amount) {
        if (plate.decreaseFood(amount)) {
            isFull = true;
            System.out.println(name + " поел " + amount + " еды. Сытость: " + isFull);
        } else {
            System.out.println(name + " не поел, недостаточно еды в тарелке.");
        }
    }

    public static int getCatCount() {
        return catCount;
    }
}
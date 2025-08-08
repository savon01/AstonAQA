package org.example.lesson5;

public interface Shape {
    // Дефолтные методы для периметра и площади (заглушки, переопределяются в классах)
    default double getPerimeter() {
        return 0.0;
    }

    default double getArea() {
        return 0.0;
    }

    String getFillColor();
    String getBorderColor();
}
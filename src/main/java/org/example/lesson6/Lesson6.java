package org.example.lesson6;

import java.io.IOException;

public class Lesson6 {
    public static void main(String[] args) {
        // Пример данных
        String[] header = {"Value 1", "Value 2", "Value 3"};
        int[][] data = {
                {100, 200, 300},
                {400, 500, 600},
                {700, 800, 900}
        };

        // Создаем объект AppData
        AppData appData = new AppData(header, data);

        // Сохраняем данные в CSV-файл
        String fileName = "data.csv";
        try {
            appData.save(fileName);
            System.out.println("Данные сохранены в " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении файла: " + e.getMessage());
        }

        // Загружаем данные из CSV-файла
        try {
            AppData loadedData = AppData.load(fileName);
            System.out.println("\nЗагруженные данные:");
            loadedData.printData();
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке файла: " + e.getMessage());
        }
    }
}

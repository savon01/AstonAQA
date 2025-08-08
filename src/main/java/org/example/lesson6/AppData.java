package org.example.lesson6;

import java.io.*;
import java.util.Arrays;

public class AppData {
    private String[] header;
    private int[][] data;

    // Конструктор
    public AppData(String[] header, int[][] data) {
        this.header = header;
        this.data = data;
    }

    // Геттеры
    public String[] getHeader() {
        return header;
    }

    public int[][] getData() {
        return data;
    }

    // Метод для сохранения данных в CSV-файл
    public void save(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Записываем заголовок
            writer.write(String.join(";", header));
            writer.newLine();

            // Записываем данные
            for (int[] row : data) {
                String[] stringRow = new String[row.length];
                for (int i = 0; i < row.length; i++) {
                    stringRow[i] = String.valueOf(row[i]);
                }
                writer.write(String.join(";", stringRow));
                writer.newLine();
            }
        }
    }

    // Метод для загрузки данных из CSV-файла
    public static AppData load(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Читаем заголовок
            String headerLine = reader.readLine();
            if (headerLine == null) {
                throw new IOException("Файл пуст или заголовок отсутствует");
            }
            String[] header = headerLine.split(";");

            // Читаем данные
            String line;
            java.util.List<int[]> dataList = new java.util.ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(";");
                int[] row = new int[values.length];
                for (int i = 0; i < values.length; i++) {
                    row[i] = Integer.parseInt(values[i].trim());
                }
                dataList.add(row);
            }

            // Преобразуем List<int[]> в int[][]
            int[][] data = new int[dataList.size()][];
            for (int i = 0; i < dataList.size(); i++) {
                data[i] = dataList.get(i);
            }

            return new AppData(header, data);
        }
    }

    // Метод для вывода данных в консоль (для отладки)
    public void printData() {
        System.out.println("Заголовок: " + Arrays.toString(header));
        System.out.println("Данные:");
        for (int[] row : data) {
            System.out.println(Arrays.toString(row));
        }
    }
}
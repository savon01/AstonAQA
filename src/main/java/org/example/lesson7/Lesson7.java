package org.example.lesson7;

public class Lesson7 {
    // Метод для обработки массива и подсчета суммы
    public static int sumArrayElements(String[][] array) throws MyArraySizeException, MyArrayDataException {
        // Проверка размера массива
        if (array.length != 4) {
            throw new MyArraySizeException("Размер массива должен быть 4x4, получено строк: " + array.length);
        }
        for (String[] row : array) {
            if (row.length != 4) {
                throw new MyArraySizeException("Размер массива должен быть 4x4, получено столбцов: " + row.length);
            }
        }

        // Подсчет суммы элементов
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j].trim());
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Неверные данные в ячейке [" + i + "][" + j + "]: " + array[i][j]);
                }
            }
        }
        return sum;
    }

    // Метод main для демонстрации работы
    public static void main(String[] args) {
        // Тестовые массивы
        String[][] correctArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        String[][] wrongSizeArray = {
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9"},
                {"10", "11", "12"}
        };

        String[][] wrongDataArray = {
                {"1", "2", "3", "4"},
                {"5", "abc", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        // Тестирование корректного массива
        System.out.println("Тестирование корректного массива:");
        try {
            int sum = sumArrayElements(correctArray);
            System.out.println("Сумма элементов: " + sum);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        // Тестирование массива с неправильным размером
        System.out.println("\nТестирование массива с неправильным размером:");
        try {
            int sum = sumArrayElements(wrongSizeArray);
            System.out.println("Сумма элементов: " + sum);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        // Тестирование массива с неверными данными
        System.out.println("\nТестирование массива с неверными данными:");
        try {
            int sum = sumArrayElements(wrongDataArray);
            System.out.println("Сумма элементов: " + sum);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}

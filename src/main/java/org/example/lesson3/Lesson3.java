package org.example.lesson3;

public class Lesson3 {
    // Метод 1: Проверка, лежит ли сумма двух чисел в диапазоне [10, 20]
    public static boolean isSumInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    // Метод 2: Печать в консоль, положительное ли число (0 считается положительным)
    public static void printNumberSign(int number) {
        if (number >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }
    }

    // Метод 3: Проверка, является ли число отрицательным
    public static boolean isNegative(int number) {
        return number < 0;
    }

    // Метод 4: Печать строки указанное количество раз
    public static void printStringMultipleTimes(String str, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(str);
        }
    }

    // Метод 5: Проверка, является ли год високосным
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // Метод 6: Инверсия массива из 0 и 1
    public static void invertArray() {
        int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.print("Исходный массив: ");
        printArray(array);
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] == 0 ? 1 : 0;
        }
        System.out.print("Инвертированный массив: ");
        printArray(array);
    }

    // Метод 7: Заполнение массива числами от 1 до 100
    public static void fillArrayWithNumbers() {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        System.out.print("Массив чисел от 1 до 100: ");
        printArray(array);
    }

    // Метод 8: Умножение чисел меньше 6 на 2
    public static void multiplyLessThanSix() {
        int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.print("Исходный массив: ");
        printArray(array);
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] *= 2;
            }
        }
        System.out.print("Массив после умножения: ");
        printArray(array);
    }

    // Метод 9: Заполнение диагонали квадратного массива единицами
    public static void fillDiagonalWithOnes() {
        int n = 5; // Размер квадратного массива
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            matrix[i][i] = 1; // Заполняем главную диагональ
        }
        System.out.println("Квадратный массив с единицами на главной диагонали:");
        printMatrix(matrix);
    }

    // Метод 10: Создание массива длиной len с элементами initialValue
    public static int[] createArray(int len, int initialValue) {
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = initialValue;
        }
        return array;
    }

    // Вспомогательный метод для печати одномерного массива
    public static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    // Вспомогательный метод для печати двумерного массива
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    // Метод main для демонстрации работы всех методов
    public static void main(String[] args) {
        // Метод 1
        System.out.println("Сумма 10 и 5 в диапазоне [10, 20]: " + isSumInRange(10, 5));
        System.out.println("Сумма 5 и 3 в диапазоне [10, 20]: " + isSumInRange(5, 3));

        // Метод 2
        System.out.println("\nПроверка знака числа:");
        printNumberSign(10);
        printNumberSign(0);
        printNumberSign(-5);

        // Метод 3
        System.out.println("\nПроверка на отрицательность:");
        System.out.println("Число 10 отрицательное: " + isNegative(10));
        System.out.println("Число 0 отрицательное: " + isNegative(0));
        System.out.println("Число -5 отрицательное: " + isNegative(-5));

        // Метод 4
        System.out.println("\nПечать строки 3 раза:");
        printStringMultipleTimes("Hello", 3);

        // Метод 5
        System.out.println("\nПроверка високосного года:");
        System.out.println("2020 високосный: " + isLeapYear(2020));
        System.out.println("2100 високосный: " + isLeapYear(2100));
        System.out.println("2000 високосный: " + isLeapYear(2000));

        // Метод 6
        System.out.println("\nИнверсия массива:");
        invertArray();

        // Метод 7
        System.out.println("\nМассив чисел от 1 до 100:");
        fillArrayWithNumbers();

        // Метод 8
        System.out.println("\nУмножение чисел меньше 6 на 2:");
        multiplyLessThanSix();

        // Метод 9
        System.out.println("\nКвадратный массив:");
        fillDiagonalWithOnes();

        // Метод 10
        System.out.println("\nСоздание массива длиной 5 с initialValue 7:");
        int[] customArray = createArray(5, 7);
        printArray(customArray);
    }
}

package org.example.lesson4;

public class Lesson4 {
    public static void main(String[] args) {
        // Задача 1 и 2: Создание массива из 5 сотрудников
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Иванов Иван Иванович", "Инженер", "ivivan@mailbox.com", "892312312", 30000, 30);
        employees[1] = new Employee("Петров Петр Петрович", "Менеджер", "petrov@mail.com", "891234567", 50000, 45);
        employees[2] = new Employee("Сидорова Анна Сергеевна", "Бухгалтер", "sidorova@company.com", "893245678", 40000, 50);
        employees[3] = new Employee("Козлов Михаил Юрьевич", "Разработчик", "kozlov@dev.com", "890987654", 60000, 28);
        employees[4] = new Employee("Смирнова Елена Викторовна", "Директор", "smirnova@company.com", "892198765", 80000, 42);

        // Вывод информации о сотрудниках старше 40 лет
        System.out.println("Сотрудники старше 40 лет:");
        for (Employee employee : employees) {
            if (employee.getAge() > 40) {
                employee.printInfo();
            }
        }

        // Задача 3: Создание объектов парка и аттракционов
        Park park = new Park();
        Park.Attraction attraction1 = park.new Attraction("Колесо обозрения", "10:00-18:00", 500.0);
        Park.Attraction attraction2 = park.new Attraction("Американские горки", "11:00-20:00", 700.0);

        System.out.println("\nИнформация об аттракционах:");
        attraction1.printInfo();
        attraction2.printInfo();
    }
}

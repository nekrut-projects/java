public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[5];

        employees[0] = new Employee("Иванов", "Иван", "Иванович",
                            "8 901 654 12 30","ivanov45@gmail.com",
                                 45, "Менеджер", 43000);
        employees[1] = new Employee("Петров", "Иван", "Иванович",
                "8 901 654 12 80","petrov65@gmail.com",
                39, "Инженер", 55000);
        employees[2] = new Employee("Сидоров", "Василий", "Иванович",
                "8 961 654 22 30","sidorov25@gmail.com",
                26, "Менеджер", 40000);
        employees[3] = new Employee("Пупкин", "Василий", "Петрович",
                "8 921 684 12 30","pupkin29@gmail.com",
                41, "Инженер", 50000);
        employees[4] = new Employee("Черпаков", "Иван", "Иванович",
                "8 931 694 12 30","cherpak@gmail.com",
                42, "Инженер", 52000);

        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getAge() > 40) {
                employees[i].printInfo();
            }
        }


    }
}

public class Employee {
    private String surname;
    private String name;
    private String middleName;
    private String telephone;
    private String email;
    private int age;
    private String post;
    private int salary;

    public Employee(String surname, String name, String middleName, String telephone,
                    String email, int age, String post, int salary) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.telephone = telephone;
        this.email = email;
        this.age = age;
        this.post = post;
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void printInfo() {
        System.out.printf("ФИО сотрудника: %s %s %s\n", surname, name, middleName);
        System.out.printf("Контактная информация: \n\tтелефон - %s,\n\temail - %s\n", telephone, email);
        System.out.printf("Возраст: %d\n", age);
        System.out.printf("Занимаемая должность: %s\n", post);
        System.out.printf("Выплачиваемая зарплата: %d\n", salary);
        System.out.println("--------------------------------------");
    }
}

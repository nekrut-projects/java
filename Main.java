public class Main {
    public static void main(String[] args) {
        Cat cat = new Cat("Барсик", 200);
        Dog dog = new Dog("Бобик", 500, 10);
        Dog dog2 = new Dog("Тузик", 400, 8);

        cat.run(100);
        cat.swim(2);
        dog.run(300);
        dog.swim(5);

        System.out.println("Колличество котов: " + Cat.getCountCats());
        System.out.println("Колличество собак: " + Dog.getCountDogs());
        System.out.println("Колличество животных: " + Animal.getCountAnimals());

    }
}

package lesson_1.task_3;

public class Store {
    public static void main(String[] args) {
        new Apple().getWeight();
        Box<Apple> box1 = new Box<>(new Apple(), new Apple());
        Box<Apple> box3 = new Box<>(new Apple(), new Apple());
        box3.add(new Apple());
        Box<Apple> box4 = new Box<>(new Apple(), new Apple(), new Apple());

        Box<Orange> box2 = new Box<>(new Orange(), new Orange());
        System.out.println(box1.compare(box3));
        System.out.println(box2.compare(box4));

        box1.pourFruitsFrom(box4);

        System.out.println(box1.getFruits());
    }
}

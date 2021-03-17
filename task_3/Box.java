package lesson_1.task_3;

import java.util.ArrayList;
import java.util.Arrays;

public class Box <T extends Fruit>{
    private ArrayList<T> fruits;

    public Box(T... fruits) {
        this.fruits = new ArrayList<>();

        for (T fruit : fruits) {
            this.fruits.add(fruit);
        }
    }

    public Box(ArrayList<T> fruits) {
        this.fruits = fruits;
    }

    float getWeight(){
        float weight = 0;
        for(T fruit : fruits) {
            weight += fruit.getWeight();
        }
        return weight;
    }
    <E extends Fruit>boolean compare(Box<E> anotherBox) {
        if (this.getWeight() == anotherBox.getWeight()) {
            return true;
        }
        return false;
    }

    ArrayList<T> getFruits() {
        return this.fruits;
    }

    void pourFruitsFrom(Box<T> anotherBox) {
        this.fruits.addAll(anotherBox.getFruits());
        anotherBox.clearBox();
    }

    void add(T... fruit) {
        this.fruits.addAll(Arrays.asList(fruit));
    }

    void removeFruit(){
        fruits.remove(fruits.size() - 1);
    }

    void clearBox() {
        fruits.clear();
    }
}

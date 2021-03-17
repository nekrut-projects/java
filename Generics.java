package lesson_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5, 6};
        System.out.println(Arrays.toString(array));
        replaceItemsOfArray(array, 0, 3);
        System.out.println(Arrays.toString(array));

        System.out.println("ArrayList: " + returnArrayList(array));
    }
    public static <T> void replaceItemsOfArray(T[] array, int index1, int index2) {
        try {
            T temp = array[index2];
            array[index2] = array[index1];
            array[index1] = temp;
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Неправильный индекс элемента");
        }
    }

    public static <E> ArrayList<E> returnArrayList(E... array) {
        ArrayList<E> list = new ArrayList<>();
        for(E item : array) {
            list.add(item);
        }
        return list;
    }
}

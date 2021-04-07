package lesson6;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ArraysMethods {
    public static void main(String[] args) {
        int[] array = {1, 2, 2, 3, 4, 5, 8, 1};
        System.out.println(Arrays.toString(returnPartArrayAfterNumber(array, 4)));
    }
    private void main(){}
    public static int[] returnPartArrayAfterNumber(int[] array, int number) throws RuntimeException {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == number) {
                index = i;
            }
        }
        if (index < 0 || index == array.length - 1) {
            throw new RuntimeException();
        }
        return Arrays.copyOfRange(array, index + 1, array.length);
    }

    public  static boolean isArrayContainsValues(int[] array, int value1, int value2) {
        boolean bool1, bool2;
        bool1 = bool2 =false;
        for (int val : array) {
            if (val == value1) {
                bool1 = true;
            }
            if (val == value2) {
                bool2 = true;
            }
            if (bool1 && bool2) {
                return true;
            }
        }
        return false;
    }
}

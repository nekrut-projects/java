package lesson_3;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] array = {"lemon", "apricot", "apple", "pear", "lemon",
                          "peach", "apricot", "banana", "mandarin",
                          "lemon", "kiwi", "pomegranate", "banana",
                          "kiwi", "kiwi", "apple", "lemon", "mandarin"};

        System.out.println(Arrays.asList(array));

        Set<String> set = new HashSet<>(Arrays.asList(array));
        System.out.println(set);

        Map<String, Integer> map = new HashMap<>();

        int count;

        for (String elementSet : set) {
            count = 0;
            for (int j = 0; j < array.length; j++) {
                if (elementSet.equals(array[j])) {
                    ++count;
                }
            }
            map.put(elementSet, count);
        }

        System.out.println(map);
        System.out.println();

        ///////////////////////////////////////////////////////////////////////////////////////////

        PhoneDirectory phoneDirectory = new PhoneDirectory();
        phoneDirectory.add("Иванов", new String[]{"+7 910 658 24 10", "55-69-78"});
        phoneDirectory.add("Петров", "+7 988 002 15 80");
        phoneDirectory.add("Сидоров", new String[]{"+7 910 658 24 10", "+7 920 028 74 33", "305-61-08"});
        phoneDirectory.add("Петросян", "+7 902 158 03 02");

        System.out.println(phoneDirectory.get("Иванов"));

        System.out.println(phoneDirectory);
    }
}

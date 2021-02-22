package lesson_3;

import java.util.*;

public class PhoneDirectory {
    private HashMap<String, Set<String>> phonebook;

    public PhoneDirectory() {
        phonebook = new HashMap<>();
    }

    public Set<String> get(String surname) {
        return phonebook.get(surname);
    }

    public void add(String surname, String telephone) {
        add(surname, new String[]{telephone});
    }

    public void add(String surname, String[] telephones) {
        Set<String> setTel = new HashSet<>();
        setTel.addAll(Arrays.asList(telephones));

        add(surname, setTel);
    }

    public void add(String surname, Collection<String> telephone) {
        if (phonebook.containsKey(surname)) {
            phonebook.get(surname).addAll(telephone);
        } else {
            phonebook.put(surname, (Set<String>) telephone);
        }
    }

    @Override
    public String toString() {
        String str = "\nТелефонный справочник:\n";

        for (Map.Entry entry : phonebook.entrySet()) {
            str += "\t" + entry.getKey() + "\n";

            for (String tel: (Set<String>)entry.getValue()) {
                str += "\t\t" + tel + "\n";
            }
        }
        return str;
    }
}

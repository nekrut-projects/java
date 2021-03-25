package lesson3.task3;

import java.io.*;
import java.util.*;

public class TextReader {
    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }
        ArrayList<Reader> listStreams = new ArrayList<>();
        long beginTime = System.currentTimeMillis();

        try {
            for (String arg : args) {
                listStreams.add(new FileReader(arg));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Reader listStream : listStreams) {
            try (BufferedReader in = new BufferedReader(listStream, 1800)) {
                String resultStr;
                while ((resultStr = in.readLine()) != null) {
                    System.out.println(resultStr);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Такого файла не существует!");
            }
        }
        long exeTime = System.currentTimeMillis() - beginTime;
        System.out.printf("Время выполнения программы: %d сек. \n", exeTime);
    }
}

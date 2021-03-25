package lesson3.task1;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/main/java/lesson3/task1/testFile.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            buffer.append(i + " ");
        }

        try(FileOutputStream out = new FileOutputStream(file);
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
            out.write(buffer.toString().getBytes());
            int x;
            while ((x = in.read()) != -1) {
                System.out.print((char) x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        file.deleteOnExit();
    }
}

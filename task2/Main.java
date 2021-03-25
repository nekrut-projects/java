package lesson3.task2;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class Main {
    public static void main(String[] args) {
        File file;
        ArrayList<File> listFiles = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            String fileName = String.format("file_%s.txt", i);
            file = new File(fileName);
            listFiles.add(createAndFillFile(file));
        }

        ArrayList<InputStream> listStream = new ArrayList<>();
        for (File tempFile : listFiles) {
            try {
                listStream.add(new FileInputStream(tempFile));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        Enumeration<InputStream> enumeration = Collections.enumeration(listStream);
        try(BufferedInputStream inBuffSeq = new BufferedInputStream(new SequenceInputStream(enumeration))) {
            int x;
            while ((x = inBuffSeq.read()) != -1) {
                System.out.println((char) x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (File listFile : listFiles) {
            listFile.deleteOnExit();
        }
    }

    public static File createAndFillFile(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(OutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < 110; i++) {
                out.write(file.getName().charAt(5));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}

package lesson3;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//        task_1();
//        task_2();
        task_3("/home/nekrut/test.txt");
    }
    public static void task_1() {
        File file = createAndFillFile("testFile.txt", 50);

        try(BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            int x;
            while ((x = in.read()) != -1) {
                out.write(x);
            }
            byte[] array = out.toByteArray();
            System.out.println(Arrays.toString(array));
        } catch (IOException e) {
            e.printStackTrace();
        }
        file.deleteOnExit();
    }

    public static void task_2() {
        ArrayList<File> listFiles = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            String fileName = String.format("file_%s.txt", i);
            listFiles.add(createAndFillFile(fileName, 100));
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
                System.out.println(x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (File listFile : listFiles) {
            listFile.deleteOnExit();
        }
    }

    public static void task_3(String nameFile) {
        final int PAGE_SIZE = 1800;
        File file = new File(nameFile);

        try(RandomAccessFile inFile = new RandomAccessFile(file, "r");
            Scanner sc = new Scanner(System.in)) {
            long sizeFile = file.length();
            long countPages = sizeFile / PAGE_SIZE;
            System.out.println("Количество страниц: " + countPages);

            System.out.print("Введите страницу: ");
            long page = sc.nextLong() - 1;
            if (page > countPages) {
                page = countPages;
            } else if (page < 0) {
                page = 0;
            }
            inFile.seek(page * PAGE_SIZE);
            byte[] buff = new byte[PAGE_SIZE];
            inFile.read(buff);
            System.out.println(new String(buff));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File createAndFillFile(String fileName, long size) {
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(OutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            for (long i = 0; i < size; i++) {
                out.write(1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}

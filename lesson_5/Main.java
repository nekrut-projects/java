package lesson_5;

import java.util.Arrays;

public class Main {
    static final int SIZE_ARRAY = 10000000;

    public static void main(String[] args) {
        float[] array = new float[SIZE_ARRAY];
        Arrays.fill(array, 1.0f);

        Main obj = new Main();

        System.out.printf("Время требуемое для заполнения массива из %,d " +
                        "элементов, без использования многопоточности: %,d мс\n",
                        SIZE_ARRAY, obj.fillArray(array));

        Arrays.fill(array, 1.0f);

        System.out.printf("Время требуемое для заполнения массива из %,d " +
                          "элементов, с использованием многопоточности: %,d мс\n",
                           SIZE_ARRAY, obj.fillArrayMultithreaded(array));
    }

    private long fillArray(float[] array) {
        long timeStart = System.currentTimeMillis();

        for (int i = 0; i < array.length; i++) {
            array[i] = (float)(array[i] * Math.sin(0.2f + i / 5) *
                    Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        long timeEnd = System.currentTimeMillis();
        return timeEnd - timeStart;
    }

    private long fillArrayMultithreaded(float[] array) {
        float[] arr1 = new float[array.length /2];
        float[] arr2 = new float[array.length /2];

        long timeStart = System.currentTimeMillis();

        System.arraycopy(array, 0, arr1, 0, arr1.length);
        System.arraycopy(array, arr2.length, arr2, 0, arr2.length);

        Thread thread1 = new Thread(() -> {
                for (int i = 0; i < arr1.length; i++) {
                    arr1[i] = (float)(arr1[i] * Math.sin(0.2f + i / 5) *
                            Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            for (int i = 0, j = arr2.length/2; i < arr2.length; i++, j++) {
                arr2[i] = (float)(arr2[i] * Math.sin(0.2f + j / 5) *
                            Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
            }
        });
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(arr1, 0, array, 0, arr1.length);
        System.arraycopy(arr2, 0, array, arr2.length, arr2.length);

        long timeEnd = System.currentTimeMillis();
        return timeEnd - timeStart;
    }
}

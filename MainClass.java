package lesson5;

import java.util.concurrent.*;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static final CountDownLatch COUNT_DOWN_LATCH_BEGIN = new CountDownLatch(CARS_COUNT);
    public static final CountDownLatch COUNT_DOWN_LATCH_END = new CountDownLatch(CARS_COUNT);
    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Race race = new Race(new Road(60), new Tunnel(), new Road(40));

        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        ExecutorService pool = Executors.newFixedThreadPool(CARS_COUNT);

        for (Car car : cars) {
            System.out.println(car.getName());
            pool.execute(car);
        }
        try {
            COUNT_DOWN_LATCH_BEGIN.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            COUNT_DOWN_LATCH_END.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}

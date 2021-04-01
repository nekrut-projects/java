package lesson5;

import java.util.concurrent.CyclicBarrier;

public abstract class Stage {
    protected int length;
    protected String description;
    protected CyclicBarrier cb;

    protected Stage() {
        cb = new CyclicBarrier(MainClass.CARS_COUNT);
    }
    public String getDescription() {
        return description;
    }

    public abstract void go(Car c);
}

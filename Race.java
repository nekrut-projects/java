package lesson5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Race {
    private AtomicInteger winner;
    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() { return stages; }
    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
        winner = new AtomicInteger(0);
    }
    public void defineWinner(String nameCar) {
        if (winner.incrementAndGet() == 1) {
            System.out.println(nameCar + " - WINNER!!!!!!!!!!!!!!!!!");
        }
    }
}

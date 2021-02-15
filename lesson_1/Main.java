package lesson_1;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Human human = new Human("Иван", 2000, 1);
        Cat cat = new Cat("Барсик", 200, 1);
        Robot robot = new Robot("c3p0", 2000, 3);

        RunTrack runTrack = new RunTrack(20);
        Wall wall = new Wall(2);

        runTrack.passObstacle(human);
        wall.passObstacle(human);

        Obstacle[] obstacles = new Obstacle[]{
                                  new RunTrack(100), new Wall(1),
                                  new Wall(2), new RunTrack(1000),
                                  new Wall(1), new RunTrack(2000)};

        Movable[] movables = new Movable[] {
                new Cat("Барсик", 200, 1),
                new Human("Иван", 2000, 1),
                new Robot("c3p0", 2000, 3),
                new Robot("d8p1", 500, 5),
                new Cat("Барсик", 200, 1)};

        for (int i = 0; i < movables.length; i++) {
            for (int j = 0; j < obstacles.length; j++) {
                if (!obstacles[j].passObstacle(movables[i])){
                    break;
                }
            }
        }
    }
}

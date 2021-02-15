package lesson_1;

public class RunTrack extends Obstacle {
    private int length;

    public RunTrack(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    @Override
    public boolean passObstacle(Movable movable) {
        return movable.run(this.length);
    }
}

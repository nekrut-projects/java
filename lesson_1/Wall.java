package lesson_1;

public class Wall extends Obstacle {
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean passObstacle(Movable movable) {
        return movable.jump(this.height);
    }
}

package lesson_1;

public class Human implements Movable{
    private String name;
    private int lengthDistanceRun;
    private int heightJump;

    public Human(String name, int lengthDistanceRun, int heightJump) {
        this.name = name;
        this.lengthDistanceRun = lengthDistanceRun;
        this.heightJump = heightJump;
    }

    public String getName() {
        return name;
    }

    public int getHeightJump() {
        return heightJump;
    }

    public int getLengthDistanceRun() {
        return lengthDistanceRun;
    }

    @Override
    public boolean jump(int height) {
        if (getHeightJump() >= height){
            System.out.printf("%s перепрыгнул стену высотой %d метра\n", getName(), height);
            return true;
        } else {
            System.out.printf("Слишком высокая стена, %s её не перепрыгнет\n", getName());
            return false;
        }
    }

    @Override
    public boolean run(int distance) {
        if (getLengthDistanceRun() >= distance) {
            System.out.printf("%s пробежал %d метров\n", getName(), distance);
            return true;
        } else {
            System.out.printf("Слишком большая дистанция, %s её не пробежит\n", getName());
            return false;
        }
    }
}

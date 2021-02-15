package lesson_1;

public class Cat implements Movable {
    private String nickname;
    private int lengthDistanceRun;
    private int heightJump;

    public Cat(String name, int lengthDistanceRun, int heightJump) {
        this.nickname = name;
        this.lengthDistanceRun = lengthDistanceRun;
        this.heightJump = heightJump;
    }

    public int getLengthDistanceRun() {
        return lengthDistanceRun;
    }

    public int getHeightJump() {
        return heightJump;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public boolean jump(int height) {
        if (getHeightJump() >= height){
            System.out.printf("Кот %s перепрыгнул стену высотой %d метра\n", getNickname(), height);
            return true;
        } else {
            System.out.printf("Слишком высокая стена, кот %s её не перепрыгнет\n", getNickname());
            return false;
        }
    }

    @Override
    public boolean run(int distance) {
        if (getLengthDistanceRun() >= distance) {
            System.out.printf("Кот %s пробежал %d метров\n", getNickname(), distance);
            return true;
        } else {
            System.out.printf("Слишком большая дистанция, кот %s её не пробежит\n", getNickname());
            return false;
        }
    }
}

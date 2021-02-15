package lesson_1;

public class Robot implements Movable {
    private String serialNumber;
    private int lengthDistanceRun;
    private int heightJump;

    public Robot(String serialNumber, int lengthDistanceRun, int heightJump) {
        this.serialNumber = serialNumber;
        this.lengthDistanceRun = lengthDistanceRun;
        this.heightJump = heightJump;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public int getLengthDistanceRun() {
        return lengthDistanceRun;
    }

    public int getHeightJump() {
        return heightJump;
    }

    @Override
    public boolean jump(int height) {
        if (getHeightJump() >= height){
            System.out.printf("Робот №%s перепрыгнул стену высотой %d метра\n", getSerialNumber(), height);
            return true;
        } else {
            System.out.printf("Слишком высокая стена, робот №%s её не перепрыгнет\n", getSerialNumber());
            return false;
        }
    }

    @Override
    public boolean run(int distance) {
        if (getLengthDistanceRun() >= distance) {
            System.out.printf("Робот №%s пробежал %d метров\n", getSerialNumber(), distance);
            return true;
        } else {
            System.out.printf("Слишком большая дистанция, робот №%s её не пробежит\n", getSerialNumber());
            return false;
        }
    }
}

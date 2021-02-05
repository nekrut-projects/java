public class Plate {
    private int food;
    private int volume;

    public Plate(int volume) {
        this.volume = volume;
        this.food = 0;
    }

    public Plate(int volume, int food) {
        this.volume = volume;
        this.food = food <= volume ? food : volume;
    }

    public void decreaseFood(int n) {
        food -= n;
    }

    public void setFood(int food) {

        this.food = food;
    }

    public int getFood() {
        return food;
    }

    public int getVolume() {
        return volume;
    }

    public void info() {
        System.out.println("plate: " + food);
    }
}

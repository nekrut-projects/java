public class Dog extends Animal {
    private static int countDogs = 0;
    public Dog(String nickname, int runDistance, int swimDistance) {
        super(nickname, runDistance, swimDistance);
        countDogs++;
    }

    public static int getCountDogs() {
        return countDogs;
    }
}

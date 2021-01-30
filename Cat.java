public class Cat extends Animal {
    private static int countCats = 0;
    public Cat(String nickname, int runDistance) {
        super(nickname, runDistance);
        countCats++;
    }

    public static int getCountCats() {
        return countCats;
    }


    @Override
    public void swim(int distance) {
        System.out.println("Коты не плавают!");
    }
}

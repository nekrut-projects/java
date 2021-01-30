public abstract class Animal {
    protected int runDistance;
    protected int swimDistance;
    protected String nickname;
    private static int countAnimals = 0;

    public Animal(String nickname, int runDistance, int swimDistance) {
        this.nickname = nickname;
        this.runDistance = runDistance;
        this.swimDistance = swimDistance;
        countAnimals++;
    }

    public Animal(String nickname, int runDistance) {
        this.nickname = nickname;
        this.runDistance = runDistance;
        countAnimals++;
    }

    public static int getCountAnimals() {
        return countAnimals;
    }

    public void run(int distance) {
        if (this.runDistance >= distance) {
            System.out.printf("%s пробежал: %d метров\n", this.nickname, distance);
        } else {
            System.out.printf("%s не сможет пробежать такое расстояние!", this.nickname);
        }
    }

    public void swim(int distance){
        if (this.runDistance >= distance) {
            System.out.printf("%s проплыл: %d метров\n", this.nickname, distance);
        } else {
            System.out.printf("%s не сможет проплыть такое расстояние!", this.nickname);
        }
    };

}

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Human human = new Human("Bob");
        Plate plate = new Plate(80, 30);
        Cat [] cats = new Cat[8];

        for (int i = 0; i < cats.length; i++) {
            cats[i] = new Cat("Barsik " + i, random.nextInt(15) + 5);
        }


        for (int i = 0; i < cats.length; i++) {
            cats[i].eat(plate);
        }
        plate.info();

        human.fillPlateFood(plate);

        for (int i = 0; i < cats.length; i++) {
            cats[i].eat(plate);
        }
        plate.info();
    }
}

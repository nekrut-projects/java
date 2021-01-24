import java.util.Random;
import java.util.Scanner;

public class ThirdLesson {
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();


    public static void main(String[] args) {

        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado",
                          "broccoli", "carrot", "cherry", "garlic", "grape", "melon",
                          "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
                          "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

        playGuessNumber();
        playGuessWord(words);

        scanner.close();
    }
    /*
    * Метод в котором загадывается случайное число от 0 до 9 и
    *  пользователю дается 3 попытки угадать это число.
    * */
    public static void playGuessNumber () {
        int randNumber, counterAttempt, exit, inputNumber;

        do {
            randNumber = random.nextInt(10);
            counterAttempt = 3;
            do {
                do {
                    System.out.print("Введите число от 0 до 9: ");
                    if (scanner.hasNextInt()) {
                        inputNumber = scanner.nextInt();
                        break;
                    }
                    scanner.nextLine();
                } while (true);

                counterAttempt--;
                if (inputNumber > randNumber) {
                    System.out.println("Введенное число больше, чем загаданное!");
                } else if (inputNumber < randNumber) {
                    System.out.println("Введенное число меньше, чем загаданное!");
                } else {
                    System.out.println("Ура!!! Вы угадали число!");
                    break;
                }
                if (counterAttempt == 0) {
                    System.out.println("=============================");
                    System.out.println("Увы, но Вы не угадали число.");
                    System.out.println("=============================");
                    break;
                }
                System.out.println("------------------------------------------");
                System.out.printf("Осталось %d попытк%c!\n", counterAttempt, counterAttempt == 1 ? 'а' : 'и');
            } while (counterAttempt > 0);
            do {
                System.out.println("Хотите попытаться еще раз?\n1\t- Да;\n0\t- Нет.");
                exit = scanner.hasNextInt()? scanner.nextInt() : 5;
                scanner.nextLine();
            } while (exit != 0 && exit != 1);
        }while (exit != 0);
    }

    public static void playGuessWord (String[] dictionary){
        String randomWord = dictionary[random.nextInt(dictionary.length)];

        String userWord, tempStr;
        boolean exit = false;
        int lengthOutputString = 15;
        int tempLength;

        do {
            tempStr = "";
            System.out.print("Введите слово: ");
            userWord = scanner.nextLine().toLowerCase();

            if (randomWord.equals(userWord)) {
                System.out.println("Вы угадали слово!");
                exit = true;
            } else {
                tempLength = randomWord.length() >= userWord.length() ? userWord.length() : randomWord.length();
                for (int i = 0; i < tempLength; i++) {
                    tempStr += (randomWord.charAt(i) == userWord.charAt(i)) ? randomWord.charAt(i) : '#';
                }
                for (int i = 0; i < lengthOutputString - tempLength; i++) {
                    tempStr += "#";
                }
                System.out.printf("Вы не угадали слово.\n%s\n", tempStr);
            }
        } while (!exit);
    }
}

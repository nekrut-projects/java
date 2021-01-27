import java.util.Random;
import java.util.Scanner;

public class FourthLesson {
    public static int SIZE = 5;
    public static int DOTS_TO_WIN = 3;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] map;
    public static int[] coordinateLastMoveUser = new int[2]; // [0] - координата по оси Х, [1] - по оси Y;
    public static int[] coordinateLastMoveAI = new int[2]; // [0] - координата по оси Х, [1] - по оси Y;
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }

    public static boolean checkHorizontal(char[][] array, char symb) {
        int matchCounter = 0;

        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                if (array[y][x] == symb) {
                    matchCounter++;
                } else {
                    matchCounter = 0;
                }
                if (matchCounter == DOTS_TO_WIN){
                    return true;
                }
            }
            matchCounter = 0;
        }
        return false;
    }

    public static boolean checkVertical(char[][] array, char symb) {
        return checkHorizontal(turnOverArray(array), symb);
    }

    public static boolean checkMainDiagonal(char[][] array, char symb){
        int matchCounter = 0;

        for (int i = 0; i < SIZE; i++) {
            if (array[i][i] == symb) {
                matchCounter++;
            } else {
                matchCounter = 0;
            }
            if (matchCounter == DOTS_TO_WIN){
                return true;
            }
        }
        return false;
    }

    public static boolean checkSideDiagonal(char[][] array, char symb){
        return checkMainDiagonal(turnOverArray(array), symb);
    }

    public static char[][] turnOverArray(char[][] originalArray) {
        char[][] turnOverArray = new char[SIZE][SIZE];
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                turnOverArray[y][x] = originalArray[x][SIZE - 1 - y];
            }
        }
        return turnOverArray;
    }

    public static boolean checkWin(char symb) {
        return (checkHorizontal(map, symb) || checkVertical(map, symb) ||
                checkMainDiagonal(map, symb) || checkSideDiagonal(map, symb));
    }
    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean isPartMapFull(int minX, int maxX, int minY, int maxY) {
        for (int i = minY; i < maxY; i++) {
            for (int j = minX; j < maxX; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void aiTurn() {

        if (!blockTurnUser()) {
            int x, y;
            do {
                x = rand.nextInt(SIZE);
                y = rand.nextInt(SIZE);
            } while (!isCellValid(x, y));
            map[y][x] = DOT_O;
            coordinateLastMoveAI[0] = x;
            coordinateLastMoveAI[1] = y;
        }

        System.out.println("Компьютер походил в точку: " + (coordinateLastMoveAI[0] + 1) +
                            " " + (coordinateLastMoveAI[1] + 1));
    }
    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате: X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
        coordinateLastMoveUser[0] = x;
        coordinateLastMoveUser[1] = y;
    }

    public static boolean blockTurnUser() {
        boolean result = false;
        int x = coordinateLastMoveUser[0];
        int y = coordinateLastMoveUser[1];

        if (x == 0 || x == SIZE - 1 || y == 0 || y == SIZE - 1) {
            if (x == y || x + y == SIZE - 1 || x + y == (SIZE - 1) * 2) {
                result = blockCornerPoint();
            } else {
                result = blockEdgePoint();
            }
        } else {
            if (isPartMapFull(x - 1, x + 1, y - 1, y + 1)) {
                return result;
            }
            int randX, randY;
            do {
                randX = x - 1;
                randY = y - 1;
                randX += rand.nextInt(3);
                randY += rand.nextInt(3);
            } while (!isCellValid(randX, randY));
            map[randY][randX] = DOT_O;
            coordinateLastMoveAI[0] = randX;
            coordinateLastMoveAI[1] = randY;
            result = true;
        }
        return result;
    }
    public static boolean blockCornerPoint() {
        int x = 0;
        int y = 0;
        if (coordinateLastMoveUser[1] == 0 && SIZE - 1 == coordinateLastMoveUser[0]) {
            x = coordinateLastMoveUser[0] - 1;
        } else if (SIZE - 1 == coordinateLastMoveUser[1] && coordinateLastMoveUser[0] == 0) {
            y = coordinateLastMoveUser[1] - 1;
        } else if (coordinateLastMoveUser[1] + coordinateLastMoveUser[0] == (SIZE - 1) * 2){
            x = coordinateLastMoveUser[0] - 1;
            y = coordinateLastMoveUser[1] - 1;
        }

        if (isPartMapFull(x, x + 2, y, y + 2)) {
                return false;
        }
        int randX, randY;
        do {
            randX = x;
            randY = y;
            randX += rand.nextInt(2);
            randY += rand.nextInt(2);
        } while (!isCellValid(randX, randY));
        map[randY][randX] = DOT_O;
        coordinateLastMoveAI[0] = randX;
        coordinateLastMoveAI[1] = randY;
        return true;
    }

    public static boolean blockEdgePoint () {
        int x = 0;
        int y = 0;
        if (coordinateLastMoveUser[0] == 0) {
            y = coordinateLastMoveUser[1] - 1;
        } else if (coordinateLastMoveUser[0] == SIZE - 1) {
            y = coordinateLastMoveUser[1] - 1;
            x = coordinateLastMoveUser[0] - 1;
        } else if (coordinateLastMoveUser[1] == 0){
            x = coordinateLastMoveUser[0] - 1;
        } else if (coordinateLastMoveUser[1] == SIZE - 1) {
            x = coordinateLastMoveUser[0] - 1;
            y = coordinateLastMoveUser[1] - 1;
        }

        if (x == 0 || x == SIZE - 1) {
            if (isPartMapFull(x, x + 2, y , y + 3)) {
                return false;
            }
        } else if (y == 0 || y == SIZE - 1) {
            if (isPartMapFull(x, x + 3, y , y + 2)) {
                return false;
            }
        }

        int randX, randY;
        do {
            randX = x;
            randY = y;
            if (x == 0 || x == SIZE - 1) {
                randX += rand.nextInt(2);
                randY += rand.nextInt(3);
            } else if (y == 0 || y == SIZE - 1) {
                randX += rand.nextInt(3);
                randY += rand.nextInt(2);
            }
        } while (!isCellValid(randX, randY));
        map[randY][randX] = DOT_O;
        coordinateLastMoveAI[0] = randX;
        coordinateLastMoveAI[1] = randY;
        return true;
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return false;
        }
        if (map[y][x] == DOT_EMPTY) {
            return true;
        }
        return false;
    }
    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }
    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

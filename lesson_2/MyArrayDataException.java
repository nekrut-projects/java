package lesson_2;

public class MyArrayDataException extends NumberFormatException{
    private int x;
    private int y;

    public MyArrayDataException(String s, int x, int y) {
        super(s);
        this.x = x;
        this.y = y;
    }

    public MyArrayDataException(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}

package lesson_2;

public class MyArraySizeException extends ArrayIndexOutOfBoundsException{
    public MyArraySizeException(String s) {
        super(s);
    }

    public MyArraySizeException() {
    }
}

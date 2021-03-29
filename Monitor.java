package lesson4;

public class Monitor {
    private char[] chars = {'A', 'B', 'C'};
    private volatile char currentChar;

    public Monitor() {
        currentChar = chars[0];
    }
    public char getCurrentChar() {
        return currentChar;
    }
    public void changeCurrentChar(){
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == currentChar) {
                if (i == chars.length - 1) {
                    currentChar = chars[0];
                    break;
                }
                currentChar = chars[i + 1];
                break;
            }
        }
    }
}

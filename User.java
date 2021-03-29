package lesson4;

import java.util.Random;

public class User implements Runnable{
    private static int countUser = 0;
    private MFU mfu;
    private String name;

    User(MFU mfu, String name) {
        countUser++;
        this.mfu = mfu;
        this.name = name;
    }

    public void printDoc() {
        mfu.printDoc(name);
    }

    public void scanDoc() {
        mfu.scanDoc(name);
    }

    public void copyDoc() {
        mfu.copyDoc(name);
    }

    @Override
    public void run() {
        Random random = new Random();
        switch (random.nextInt(countUser)) {
            case 1:
                scanDoc();
                copyDoc();
                return;
            case 2:
                printDoc();
                scanDoc();
                printDoc();
                return;
            case 3:
                copyDoc();
                printDoc();
                scanDoc();
                return;
            default:
                printDoc();
            }


    }
}

package lesson4;

public class Main {
    public static void main(String[] args) {
        task_1();
//        task_2();
    }

    static void task_1() {
        Monitor mon = new Monitor();
        Thread threadOne = new Thread(new TestThread(mon, 'A'));
        Thread threadTwo = new Thread(new TestThread(mon, 'B'));
        Thread threadThree = new Thread(new TestThread(mon,'C'));

        threadOne.start();
        threadTwo.start();
        threadThree.start();

    }

    static void task_2() {
        MFU mfu = new MFU();

        String[] names = {"Bob", "Alex", "John", "Nick", "Cris", "Charly"};

        User[] users = new User[names.length];
        for (int i = 0; i < users.length; i++) {
            users[i] = new User(mfu, names[i]);
            new Thread(users[i]).start();
        }
    }
}

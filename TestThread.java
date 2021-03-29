package lesson4;

public class TestThread implements Runnable {
    private Monitor monitor;
    private char c;

    public TestThread(Monitor monitor, char c) {
        this.c = c;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        synchronized (monitor) {
            for (int i = 0; i < 5; i++) {
                while (c != monitor.getCurrentChar()) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(c);
                monitor.changeCurrentChar();
                monitor.notifyAll();
            }
        }
    }
}

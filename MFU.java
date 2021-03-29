package lesson4;

public class MFU {
    private final Object printMon;
    public final Object scanMon;
    private boolean printing = false;
    public boolean scanning = false;

    public MFU() {
        printMon = new Object();
        scanMon = new Scanner();
    }

    public void printDoc(String name) {
        synchronized (printMon) {
            try {
                System.out.println(name + " начал печатать документ.");
                Thread.sleep(200);
                System.out.println(name + " распечатал документ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void scanDoc(String name) {
        synchronized (scanMon) {
            try{
                System.out.println(name + " начал сканировать документ.");
                Thread.sleep(600);
                System.out.println(name + " сканировал документ.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void copyDoc(String name) {
        synchronized (scanMon) {
            synchronized (printMon) {
                try{
                    System.out.println(name + " начал копировать документ.");
                    Thread.sleep(800);
                    System.out.println(name + " закончил копировать документ.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

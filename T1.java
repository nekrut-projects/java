package lesson7;

public class T1 {
    @Test(priority = 3)
    public void test1(){
        System.out.println("test1");
    }

    @Test(priority = 3)
    private int test2() {
        System.out.println("test2");
        return 0;
    }

    @BeforeSuite
    public void test3(){
        System.out.println("BeforeSuite method");
    }

    @Test(priority = 8)
    private void test6() {
        System.out.println("test6");
    }

    @Test(priority = 1)
    private void test7() {
        System.out.println("test7");
    }

    @Test(priority = 6)
    private void test8() {
        System.out.println("test8");
    }

    private void test9() {
        System.out.println("test9");
    }

    @AfterSuite
    public void test4() {
        System.out.println("AfterSuite method");
    }
}

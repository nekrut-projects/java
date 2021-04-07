package lesson6;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestMethod1 {
    public static final int SPLIT_NUMBER = 4;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {new int[]{1, 0}, new int[]{1, 2, 8, 9, 4, 1, 0}, SPLIT_NUMBER},
            {new int[]{9, 7, 8}, new int[]{14, 42, 0, 5, 4, 6, 0, 5, 4, 9, 7, 8}, SPLIT_NUMBER},
            {new int[]{1, 1, 0}, new int[]{1, 2, 8, 4, 1, 1, 0}, SPLIT_NUMBER},
            {new int[]{9, 3, 1, 0}, new int[]{7, 6, 8, 9, 3, 1, 0}, SPLIT_NUMBER},
            {new int[]{1, 0}, new int[]{1, 2, 8, 9, 4, 0, 4}, SPLIT_NUMBER}
        });
    }
    private int[] resultArray;
    private int[] inputArray;
    private int number;

    public TestMethod1(int[] resultArray, int[] inputArray, int number) {
        this.resultArray = resultArray;
        this.inputArray = inputArray;
        this.number = number;
    }

    @Test
//    @Ignore(value = "Удалить для проверки")
    public void testReturnPartArrayAfterNumber() {
        Assert.assertArrayEquals(resultArray, ArraysMethods.returnPartArrayAfterNumber(inputArray, number));
    }

    @Test(expected = RuntimeException.class)
    @Ignore(value = "Удалить для проверки на RuntimeException")
    public void testRuntime() {
        Assert.assertArrayEquals(resultArray, ArraysMethods.returnPartArrayAfterNumber(inputArray, number));
    }
}

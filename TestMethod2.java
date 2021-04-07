package lesson6;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestMethod2 {
    public static final int SEARCH_VALUE_1 = 1;
    public static final int SEARCH_VALUE_2 = 4;
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {true, new int[]{1, 1, 4, 1, 4, 1, 4}, SEARCH_VALUE_1, SEARCH_VALUE_2},
                {false, new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}, SEARCH_VALUE_1, SEARCH_VALUE_2},
                {true, new int[]{1, 4, 4, 1, 4, 1, 1}, SEARCH_VALUE_1, SEARCH_VALUE_2},
                {true, new int[]{1, 4, 1, 1, 4, 4, 1}, SEARCH_VALUE_1, SEARCH_VALUE_2},
                {true, new int[]{1, 1, 1, 1, 1, 1, 1}, SEARCH_VALUE_1, SEARCH_VALUE_2}
        });
    }
    private boolean result;
    private int[] inputArray;
    private int value1;
    private int value2;

    public TestMethod2(boolean result, int[] inputArray, int value1, int value2) {
        this.result = result;
        this.inputArray = inputArray;
        this.value1 = value1;
        this.value2 = value2;
    }

    @Test
    public void testisArrayContainsValuesMeth() {
        Assert.assertEquals(result, ArraysMethods.isArrayContainsValues(inputArray, value1, value2));
    }
}

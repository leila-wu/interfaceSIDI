package testJunit.data;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CalculateTest {

    private int expected;
    private int first;
    private int second;

    public CalculateTest(int expectedResult, int firstNumber, int secondNumber) {
        this.expected = expectedResult;
        this.first = firstNumber;
        this.second = secondNumber;
    }

    @Parameters(name = "{0} vs {1}+{2}")
    public static Collection addedNumbers() {
        return Arrays.asList(new Integer[][] {
                { 3, 1, 2 },
                { 5, 2, 3 },
                { 7, 3, 4 },
                { 9, 4, 5 },
        });
    }

    @Test
    public void sum() {
        System.out.println("Addition with parameters : " + first + " and "
                + second);
        assertEquals(expected, first+second);
    }
}
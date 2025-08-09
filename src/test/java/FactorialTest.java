import org.example.Factorial;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class FactorialTest {

    @Test
    public void testFactorialZero() {
        assertEquals(Factorial.calculateFactorial(0), 1, "Factorial of 0 should be 1");
    }

    @Test
    public void testFactorialOne() {
        assertEquals(Factorial.calculateFactorial(1), 1, "Factorial of 1 should be 1");
    }

    @Test
    public void testFactorialPositiveNumber() {
        assertEquals(Factorial.calculateFactorial(5), 120, "Factorial of 5 should be 120");
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Number must be non-negative")
    public void testFactorialNegativeNumber() {
        Factorial.calculateFactorial(-1);
    }

    @Test
    public void testFactorialLargeNumber() {
        assertEquals(Factorial.calculateFactorial(10), 3_628_800, "Factorial of 10 should be 3,628,800");
    }
}

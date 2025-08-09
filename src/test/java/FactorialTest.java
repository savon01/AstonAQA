import org.example.Factorial;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FactorialTest {

    @Test
    void testFactorialZero() {
        assertEquals(1, Factorial.calculateFactorial(0), "Factorial of 0 should be 1");
    }

    @Test
    void testFactorialOne() {
        assertEquals(1, Factorial.calculateFactorial(1), "Factorial of 1 should be 1");
    }

    @Test
    void testFactorialPositiveNumber() {
        assertEquals(120, Factorial.calculateFactorial(5), "Factorial of 5 should be 120");
    }

    @Test
    void testFactorialNegativeNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Factorial.calculateFactorial(-1);
        });
        assertEquals("Number must be non-negative", exception.getMessage());
    }

    @Test
    void testFactorialLargeNumber() {
        assertEquals(3_628_800, Factorial.calculateFactorial(10), "Factorial of 10 should be 3,628,800");
    }
}
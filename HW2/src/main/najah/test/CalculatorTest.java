package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.najah.code.Calculator;

@TestMethodOrder(OrderAnnotation.class)
@Execution(ExecutionMode.CONCURRENT)
@DisplayName("Calculator Tests")
public class CalculatorTest {

    static Calculator calc;

    @BeforeAll
    static void setupAll() {
        calc = new Calculator();
        System.out.println("Setup complete");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("All tests finished");
    }

    @BeforeEach
    void setup() {
        System.out.println("Start test");
    }

    @AfterEach
    void tearDown() {
        System.out.println("End test");
    }

 
    @Test
    @Order(1)
    @DisplayName("Addition Test")
    void testAdd() {
        assertEquals(5, calc.add(2, 3));
    }

  
    @Test
    @DisplayName("Addition Multiple Values")
    void testAddMultiple() {
        assertEquals(0, calc.add());
        assertEquals(2, calc.add(2));
        assertEquals(10, calc.add(2, 3, 5));
        assertEquals(-2, calc.add(-5, 3));
    }

    
    @Test
    @Order(2)
    @DisplayName("Division Valid ")
    void testDivide() {
        assertAll(
            () -> assertEquals(2, calc.divide(6, 3)),
            () -> assertEquals(-2, calc.divide(-6, 3)),
            () -> assertEquals(0, calc.divide(0, 5)),
            () -> assertThrows(ArithmeticException.class, () -> calc.divide(5, 0))
        );
    }

    
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    @DisplayName("Parameterized Positive Numbers")
    void testPositive(int num) {
        assertTrue(num > 0);
    }

   
    @Test
    @Timeout(1)
    @DisplayName("Timeout Test")
    void testTimeout() throws InterruptedException {
        Thread.sleep(200);
    }

 
    @Test
    @DisplayName("Factorial Test")
    void testFactorial() {
        assertEquals(1, calc.factorial(0));
        assertEquals(1, calc.factorial(1));
        assertEquals(120, calc.factorial(5));
    }

    
    @Test
    @DisplayName("Factorial Negative ")
    void testFactorialNegative() {
        assertThrows(IllegalArgumentException.class, () -> calc.factorial(-1));
    }

  
    @Test
    @Disabled("Disabled to avoid failure")
    void failingTest() {
        assertEquals(10, calc.add(-5, -5));
    }

    
    @Test
    @DisplayName("Negative Addition")
    void testNegativeAdd() {
        assertEquals(-10, calc.add(-5, -5));
    }
}
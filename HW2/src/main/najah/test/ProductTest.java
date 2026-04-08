package main.najah.test;

import main.najah.code.Product;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Product Tests")
public class ProductTest {

    Product product;

    @BeforeEach
    void setup() throws Exception{
        product = new Product("Laptop", 1000);
    }

    
    @Test
    @DisplayName("Valid constructor")
    void testValidConstructor() {
        Product p = new Product("Phone", 500);

        assertAll(
                () -> assertEquals("Phone", p.getName()),
                () -> assertEquals(500, p.getPrice())
        );
    }

    @Test
    @DisplayName("Invalid constructor")
    void testInvalidConstructor() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product("Bad", -1));
    }

 
    @Test
    @DisplayName("Getters test")
    void testGetters() {
        assertAll(
                () -> assertEquals("Laptop", product.getName()),
                () -> assertEquals(1000, product.getPrice()),
                () -> assertEquals(0, product.getDiscount())
        );
    }

  

    @Test
    @DisplayName("Valid discount")
    void testValidDiscount() {
        product.applyDiscount(10);

        assertAll(
                () -> assertEquals(10, product.getDiscount()),
                () -> assertEquals(900, product.getFinalPrice())
        );
    }

    @Test
    @DisplayName("Discount = 0")
    void testZeroDiscount() {
        product.applyDiscount(0);

        assertEquals(1000, product.getFinalPrice());
    }

    @Test
    @DisplayName("Discount = 50 (edge)")
    void testMaxDiscount() {
        product.applyDiscount(50);

        assertEquals(500, product.getFinalPrice());
    }

    

    @Test
    @DisplayName("Negative discount")
    void testNegativeDiscount() {
        assertThrows(IllegalArgumentException.class,
                () -> product.applyDiscount(-10));
    }

    @Test
    @DisplayName("Discount > 50")
    void testAboveLimit() {
        assertThrows(IllegalArgumentException.class,
                () -> product.applyDiscount(100));
    }

   

    @ParameterizedTest
    @ValueSource(doubles = {1, 5, 10, 25, 49})
    @DisplayName("Parameterized valid discounts")
    void testParameterized(double d) {
        Product p = new Product("Tablet", 1000);
        p.applyDiscount(d);

        assertTrue(p.getFinalPrice() > 0);
        assertTrue(p.getFinalPrice() <= 1000);
    }

  

    @Test
    @DisplayName("Timeout test")
    void testTimeout() {
        assertTimeout(Duration.ofSeconds(1), () -> {
            product.applyDiscount(10);
            assertEquals(900, product.getFinalPrice());
        });
    }

  

    @Test
    @Disabled("Fails .")
    @DisplayName("Disabled test")
    void disabledTest() {
        Product p = new Product("Test", 1000);
        p.applyDiscount(33.333);

        assertEquals(666.67, p.getFinalPrice());
    }
}
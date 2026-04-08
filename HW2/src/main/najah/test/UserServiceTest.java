package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.najah.code.UserService;

@DisplayName("User Service Tests")
public class UserServiceTest {

    UserService service;

    @BeforeEach
    void setup() throws Exception{
        service = new UserService();
    }

    @Test
    @DisplayName("Valid Email Test")
    void testValidEmail() {
        assertTrue(service.isValidEmail("test@gmail.com"));
    }

    @Test
    @DisplayName("Invalid Email Test")
    void testInvalidEmail() {
        assertFalse(service.isValidEmail("invalid-email"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a@gmail.com", "user@yahoo.com"})
    @DisplayName("Parameterized Valid Emails")
    void testEmails(String email) {
        assertTrue(service.isValidEmail(email));
    }

    @Test
    @DisplayName("Authentication Test")
    void testAuth() {
        assertAll(
            () -> assertTrue(service.authenticate("admin", "1234")),
            () -> assertFalse(service.authenticate("user", "wrong"))
        );
    }
}
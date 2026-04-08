package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.najah.code.RecipeBook;
import main.najah.code.Recipe;

@DisplayName("RecipeBook Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RecipeBookTest {

    RecipeBook book;

    @BeforeEach
    void setup() {
        book = new RecipeBook();
        System.out.println("Start test");
    }

    @AfterEach
    void tearDown() {
        System.out.println("End test");
    }

    @Test
    @Order(1)
    @DisplayName("Add recipe")
    void testAddRecipe() {
        Recipe r = new Recipe();
        assertTrue(book.addRecipe(r));
        assertNotNull(book.getRecipes()[0]);
    }

    @Test
    @Order(2)
    @DisplayName("Duplicate recipe")
    void testDuplicateRecipe() {
        Recipe r = new Recipe();
        book.addRecipe(r);
        assertFalse(book.addRecipe(r));
    }

    @Test
    @Order(3)
    @DisplayName("Null recipe")
    void testNullRecipe() {
        assertThrows(NullPointerException.class,
                () -> book.addRecipe(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Cake", "Pizza"})
    @DisplayName("Parameterized add")
    void testParameterized(String name) {
        Recipe r = new Recipe();
        r.setName(name);
        book.addRecipe(r);

        boolean found = false;
        for (Recipe rec : book.getRecipes()) {
            if (rec != null && name.equals(rec.getName())) {
                found = true;
            }
        }

        assertTrue(found);
    }

    @Test
    @DisplayName("Delete recipe")
    void testDeleteRecipe() {
        Recipe r = new Recipe();
        r.setName("Cake");
        book.addRecipe(r);

        String deleted = book.deleteRecipe(0);

        assertEquals("Cake", deleted);
        assertEquals("", book.getRecipes()[0].getName());
    }

    @Test
    @DisplayName("Delete empty")
    void testDeleteEmpty() {
        assertNull(book.deleteRecipe(1));
    }

    @Test
    @DisplayName("Edit recipe")
    void testEditRecipe() {
        Recipe r = new Recipe();
        r.setName("Cake");
        book.addRecipe(r);

        Recipe newR = new Recipe();

        String oldName = book.editRecipe(0, newR);

        assertEquals("Cake", oldName);
    }

    @Test
    @DisplayName("Edit empty")
    void testEditEmpty() {
        assertNull(book.editRecipe(2, new Recipe()));
    }

    @Test
    @DisplayName("Full book")
    void testFullBook() {
        for (int i = 0; i < 4; i++) {
            book.addRecipe(new Recipe());
        }

        assertFalse(book.addRecipe(new Recipe()));
    }

    @Test
    @DisplayName("Timeout test")
    void testTimeout() {
        assertTimeout(java.time.Duration.ofSeconds(1), () -> {
            book.addRecipe(new Recipe());
        });
    }

 
    @Test
    @Disabled("Fails because index not checked ")
    void failingTest() {
        book.deleteRecipe(10);
    }
}
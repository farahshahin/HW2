package main.najah.test;

import org.junit.platform.suite.api.*;

@Suite
@SelectClasses({
	CalculatorTest.class,
    ProductTest.class,
    UserServiceTest.class,
    RecipeBookTest.class
})
public class TestSuite {
}
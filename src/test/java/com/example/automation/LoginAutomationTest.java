package com.example.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginAutomationTest {

    private static WebDriver driver;

    @BeforeAll
    static void setup() {
        try {
            // Set the path for GeckoDriver (Firefox driver)
            System.setProperty("webdriver.chrome.driver", "C:\Users\Admin\Downloads\geck\chromedriver-win64");
            WebDriver driver = new FirefoxDriver();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Failed to initialize WebDriver.");
        }
    }

    @Test
    void testLogin() {
        try {
            // Navigate to Sauce Demo login page
            driver.get("https://www.saucedemo.com");

            // Locate username, password fields, and login button
            WebElement usernameField = driver.findElement(By.id("user-name"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("login-button"));

            // Perform login action
            usernameField.sendKeys("standard_user");
            passwordField.sendKeys("secret_sauce");
            loginButton.click();

            // Validate successful login
            WebElement inventoryPageTitle = driver.findElement(By.className("title"));
            String expectedTitle = "PRODUCTS";
            String actualTitle = inventoryPageTitle.getText();

            // Assert title is correct (ignoring case)
            assertEquals(expectedTitle.toLowerCase(), actualTitle.toLowerCase(), "Login test failed: Title mismatch.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Login test failed due to an exception.");
        }
    }

    @Test
    void testWelcomeMessage() {
        // Created an instance of App
        App app = new App();

        // Providing the name for the message
        String result = app.welcomeMessage("Manasa");

        // Correct expected message based on input
        assertEquals("Hello, Manasa!", result, "The welcome message should be correct.");
    }

    @AfterAll
    static void tearDown() {
        try {
            // Close the browser after tests
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

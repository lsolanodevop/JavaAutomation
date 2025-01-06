package com.tests;

import com.pages.mainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class MainTest {

    private WebDriver driver;
    private mainPage mainPage;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        mainPage = new mainPage(driver);
        driver.get("https://qubika.com/");
    }
    @Test
    public void testStep2(){
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://qubika.com/");
        Assert.assertTrue(mainPage.fieldIsDisplayed("logo"));
    }

    @Test
    public void testStep3and4() {
        mainPage.clickOnContactUs();
        Assert.assertTrue(mainPage.fieldIsDisplayed("firstName"));
        Assert.assertTrue(mainPage.fieldIsDisplayed("email"));
        Assert.assertTrue(mainPage.fieldIsDisplayed("submit"));
    }
    @Test
    public void testStep5and6(){
        mainPage.clickOnContactUs();
        mainPage.submitForm();
        Assert.assertTrue(mainPage.fieldIsDisplayed("error-firstName"));
        Assert.assertTrue(mainPage.fieldIsDisplayed("error-lastName"));
        Assert.assertTrue(mainPage.fieldIsDisplayed("error-email"));
        Assert.assertTrue(mainPage.fieldIsDisplayed("error-company"));
        Assert.assertTrue(mainPage.fieldIsDisplayed("error-contact"));
        Assert.assertTrue(mainPage.fieldIsDisplayed("error-message"));
    }
    @Test
    public void step7(){
        mainPage.clickOnContactUs();
        mainPage.enterLastName("Parker");
        mainPage.enterEmail("peterparker@email.com");
        mainPage.enterCompany("Daily Bungle");
        mainPage.selectContactType();
        mainPage.enterMessage("Your friendly Neighbor!");
        mainPage.submitForm();
        Assert.assertTrue(mainPage.fieldIsDisplayed("error-firstName"));
    }
    @Test
    public void step8to10(){
        mainPage.clickOnContactUs();
        mainPage.enterFirstName("Test Name");
        mainPage.submitForm();
        Assert.assertTrue(mainPage.fieldIsDisplayed("error-lastName"));
        Assert.assertTrue(mainPage.fieldIsDisplayed("error-email"));
        Assert.assertTrue(mainPage.fieldIsDisplayed("error-company"));
        Assert.assertTrue(mainPage.fieldIsDisplayed("error-contact"));
        Assert.assertTrue(mainPage.fieldIsDisplayed("error-message"));
    } @Test
    public void step11(){
        mainPage.clickOnContactUs();
        mainPage.enterEmail("peterparker@email.com");
        mainPage.submitForm();
        Assert.assertTrue(mainPage.fieldIsDisplayed("error-firstName"));
        Assert.assertTrue(mainPage.fieldIsDisplayed("error-lastName"));
        Assert.assertTrue(mainPage.fieldIsDisplayed("error-company"));
        Assert.assertTrue(mainPage.fieldIsDisplayed("error-contact"));
        Assert.assertTrue(mainPage.fieldIsDisplayed("error-message"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
package com.tests;

import com.pages.mainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        driver = new ChromeDriver();
        mainPage = new mainPage(driver);
        driver.get("https://qubika.com/");
    }
    @Test
    public void testStep2(){
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://qubika.com/");
        Assert.assertEquals(mainPage.fieldIsDisplayed("logo"),true);
    }

    @Test
    public void testStep3and4() {
        mainPage.clickOnContactUs();
//        String actualErrorMessage = mainPage.getErrorMessage();
//        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "El mensaje de error no coincide.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
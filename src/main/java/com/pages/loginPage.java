package com.pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class loginPage extends BasePage {
    private final By user_input = By.id("user-name");
    private final By password_input = By.id("password");
    private final By submit_button = By.id("login-button");
    private final By error_message = By.xpath("//h3[contains(@data-test,'error')]");
    public loginPage(WebDriver driver) {
        super(driver);
    }

    public void inputData(String selector, String data){
        switch(selector){
            case "username":
                WebElement usernameElement = wait.until(ExpectedConditions.elementToBeClickable(user_input));
                usernameElement.clear();
                usernameElement.sendKeys(data);
                break;
            case "password":
                WebElement passwordElement = wait.until(ExpectedConditions.elementToBeClickable(password_input));
                passwordElement.clear();
                passwordElement.sendKeys(data);
                break;
        }
    }
    public void submit(){
        WebElement submitElement = wait.until(ExpectedConditions.elementToBeClickable(submit_button));
        submitElement.click();
    }

    public void assertErrorMessage(){
        try {
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(error_message));
            Assert.assertTrue(errorElement.isDisplayed());
        }
        catch (org.openqa.selenium.TimeoutException e) {
            Assert.fail("The element is not present in the form " + e.getMessage());
        }
    }
}

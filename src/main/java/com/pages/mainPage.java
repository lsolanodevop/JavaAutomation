package com.pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class mainPage extends BasePage {
    private final By logo = By.cssSelector("a.logo");
    private final By contactUsButton = By.xpath("//li//a[contains(@class, 'link-label') and text()='Contact Us']");
    private final By firstNameInput = By.xpath("//input[@name='firstname' and @type='text']");
    private final By lastNameInput = By.xpath("//input[@name='lastname' and @type='text']");
    private final By emailInput = By.xpath("//input[@name='email' and @type='email']");
    private final By companyInput = By.xpath("//input[@name='company' and @type='text']");
    private final By contactTypeSelector = By.xpath("//select[@name='contact_type']");
    private final By messageInput = By.xpath("//textarea[@name='message']");
    private final By submitButton = By.xpath("//div[@class='actions']//input[@type='submit']");
    private final By requiredMessageName = By.xpath("//div[contains(@class, 'hs_firstname')]//ul[contains(@class, 'hs-error-msgs')]//li/label[text()='Please complete this required field.']");
    private final By requiredMessageLastName = By.xpath("//div[contains(@class, 'hs_lastname')]//ul[contains(@class, 'hs-error-msgs')]//li/label[text()='Please complete this required field.']");
    private final By requiredMessageEmail = By.xpath("//div[contains(@class, 'hs_email')]//ul[contains(@class, 'hs-error-msgs')]//li/label[text()='Please complete this required field.']");
    private final By requiredMessageCompany = By.xpath("//div[contains(@class, 'hs_company')]//ul[contains(@class, 'hs-error-msgs')]//li/label[text()='Please complete this required field.']");
    private final By requiredMessageContactType = By.xpath("//div[contains(@class, 'hs_contact_type')]//ul[contains(@class, 'hs-error-msgs')]//li/label[text()='Please complete this required field.']");
    private final By requiredMessageMessage = By.xpath("//div[contains(@class, 'hs_message ')]//ul[contains(@class, 'hs-error-msgs')]//li/label[text()='Please complete this required field.']");

    public mainPage(WebDriver driver) {
        super(driver);
    }
    public boolean fieldIsDisplayed(String fieldToCheck) {
        switch (fieldToCheck) {
            case "firstName":
                checkVisibility(firstNameInput);
                return true;
            case "lastName":
                checkVisibility(lastNameInput);
                return true;
            case "email":
                checkVisibility(emailInput);
                return true;
            case "company":
                checkVisibility(companyInput);
                return true;
            case "message":
                checkVisibility(messageInput);
                return true;
            case "error-firstName":
                checkVisibility(requiredMessageName);
                return true;
            case "error-lastName":
                checkVisibility(requiredMessageLastName);
                return true;
            case "error-email":
                checkVisibility(requiredMessageEmail);
                return true;
            case "error-company":
                checkVisibility(requiredMessageCompany);
                return true;
            case "error-contact":
                checkVisibility(requiredMessageContactType);
                return true;
            case "error-message":
                checkVisibility(requiredMessageMessage);
                return true;
            case "submit":
                checkVisibility(submitButton);
                return true;
            case "logo":
                checkVisibility(logo);
                return true;
            default:
                return false;
        }
    }

    public void checkVisibility(By selector) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
            Assert.assertTrue(element.isDisplayed());
        } catch (org.openqa.selenium.TimeoutException e) {
            Assert.fail("The element is not present in the form " + e.getMessage());
        }
    }
    public void clickOnContactUs(){
        WebElement loginButtonElement = wait.until(ExpectedConditions.elementToBeClickable(contactUsButton));
        loginButtonElement.click();
    }
    public void enterFirstName(String name){
        WebElement usernameElement = wait.until(ExpectedConditions.elementToBeClickable(firstNameInput));
        usernameElement.clear();
        usernameElement.sendKeys(name);
    }
    public void enterLastName(String lastName){
        WebElement lastNameElement = wait.until(ExpectedConditions.elementToBeClickable(lastNameInput));
        lastNameElement.clear();
        lastNameElement.sendKeys(lastName);
    }
    public void enterEmail(String email){
        WebElement emailElement = wait.until(ExpectedConditions.elementToBeClickable(emailInput));
        emailElement.clear();
        emailElement.sendKeys(email);
    }
    public void enterCompany(String company){
        WebElement companyElement = wait.until(ExpectedConditions.elementToBeClickable(companyInput));
        companyElement.clear();
        companyElement.sendKeys(company);
    }
    public void selectContactType(){
        WebElement contactElement = wait.until(ExpectedConditions.presenceOfElementLocated(contactTypeSelector));
        Select contactType = new Select(contactElement);
        contactType.selectByIndex(1);
    }
    public void enterMessage(String message){
        WebElement messageElement = wait.until(ExpectedConditions.elementToBeClickable(messageInput));
        messageElement.clear();
        messageElement.sendKeys(message);
    }

    public void submitForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement submitButtonElement = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", submitButtonElement);
        submitButtonElement.click();
    }
}

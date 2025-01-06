package com.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class mainPage extends BasePage {
    private By logo = By.cssSelector("a.logo");
    private By contactUsButton = By.className(".contact-us-main-menu-button");
    private By firstNameInput = By.name("firstname");
    private By lastNameInput = By.name("lastname");
    private By emailInput = By.name("email");
    private By companyInput = By.name("company");
    private By contactTypeCombo = By.name("contact_type");
    private By messageInput = By.name("message");
    private By submitButton = By.xpath("//input[@type='submit' and @value='Submit']");
    private By requiredMessageName = By.xpath("//div[contains(@class, 'hs_firstname')]//ul[contains(@class, 'hs-error-msgs')]//li/label[text()='Please complete this required field.']");
    private By requiredMessageLastName = By.xpath("//div[contains(@class, 'hs_lastname')]//ul[contains(@class, 'hs-error-msgs')]//li/label[text()='Please complete this required field.']");
    private By requiredMessageEmail = By.xpath("//div[contains(@class, 'hs_email')]//ul[contains(@class, 'hs-error-msgs')]//li/label[text()='Please complete this required field.']");
    private By requiredMessageCompany = By.xpath("//div[contains(@class, 'hs_company')]//ul[contains(@class, 'hs-error-msgs')]//li/label[text()='Please complete this required field.']");
    private By requiredMessageContactType = By.xpath("//div[contains(@class, 'hs_contact_type')]//ul[contains(@class, 'hs-error-msgs')]//li/label[text()='Please complete this required field.']");
    private By requiredMessageMessage = By.xpath("//div[contains(@class, 'hs_message ')]//ul[contains(@class, 'hs-error-msgs')]//li/label[text()='Please complete this required field.']");

    public mainPage(WebDriver driver) {
        super(driver);
    }
    public Boolean fieldIsDisplayed(String fieldToCheck){
        return switch (fieldToCheck) {
            case "firstName" -> {
                checkVisibility(firstNameInput);
                yield true;
            }
            case "lastName" -> {
                checkVisibility(lastNameInput);
                yield true;
            }
            case "email" -> {
                checkVisibility(emailInput);
                yield true;
            }
            case "company" -> {
                checkVisibility(companyInput);
                yield true;
            }
            case "message" -> {
                checkVisibility(messageInput);
                yield true;
            }
            case "submit" -> {
                checkVisibility(submitButton);
                yield true;
            }
            case "logo" -> {
                checkVisibility(logo);
                yield true;
            }
            default -> false;
        };
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
        WebElement usernameElement = wait.until(ExpectedConditions.elementToBeClickable(lastNameInput));
        usernameElement.clear();
        usernameElement.sendKeys(lastName);
    }
    public void enterEmail(String email){
        WebElement usernameElement = wait.until(ExpectedConditions.elementToBeClickable(emailInput));
        usernameElement.clear();
        usernameElement.sendKeys(email);
    }
    public void enterCompany(String company){
        WebElement usernameElement = wait.until(ExpectedConditions.elementToBeClickable(companyInput));
        usernameElement.clear();
        usernameElement.sendKeys(company);
    }
    public void selectContactType(){
//        WebElement usernameElement = wait.until(ExpectedConditions.elementToBeClickable(firstNameInput));
//        usernameElement.clear();
//        usernameElement.sendKeys(name);
    }
    public void enterMessage(String message){
        WebElement usernameElement = wait.until(ExpectedConditions.elementToBeClickable(messageInput));
        usernameElement.clear();
        usernameElement.sendKeys(message);
    }
    public void submitForm(){
        WebElement loginButtonElement = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        loginButtonElement.click();
    }
}

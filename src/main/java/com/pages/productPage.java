package com.pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.math.BigDecimal;
import java.util.List;

public class productPage extends BasePage {
    private final By itemList = By.cssSelector("[data-test='inventory-item']");
    private final By addToCartSelector = By.xpath("//button[contains(@name,'add-to-cart')]");
    private final By goToCartSelector = By.xpath("//a[contains(@data-test,'shopping-cart-link')]");
    private final By continueShoppingButton = By.xpath("//button[contains(@id,'continue-shopping')]");
    private final By filterSelector = By.xpath("//select[contains(@class,'product_sort_container')]");
    private final By cartListSelector = By.cssSelector("cart_item");
    private final By backToProducts = By.cssSelector(".inventory_details_back_button");
    private final By goToCheckout = By.cssSelector(".checkout_button");
    private final By firstNameInput = By.xpath("//input[contains(@data-test,'firstName')]");
    private final By lastNameInput = By.xpath("//input[contains(@data-test,'lastName')]");
    private final By zipCodeInput = By.xpath("//input[contains(@data-test,'postalCode')]");
    private final By continueButton = By.xpath("//input[contains(@data-test,'continue')]");
    private final By errorSelector = By.cssSelector(".input_error");
    private final By itemTotalAmountLabel = By.cssSelector(".summary_subtotal_label");
    private final By placerOrderButton = By.id("finish");
    private final By thankYouMessage = By.className("complete-header");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logout = By.id("logout_sidebar_link");

    public productPage(WebDriver driver) {
        super(driver);
    }

    public By getItemTitle(String itemName) {
        String locator = "//div[contains(@data-test,'inventory-item-name') and text()='%s']";
        return By.xpath(String.format(locator, itemName));
    }
    public By getImageSelector(String itemName){
        String locator = "//img[contains(@data-test,'%s')]";
        return By.xpath(String.format(locator, itemName));
    }
    public By removeItem(String itemName){
        String locator = "//button[contains(@data-test,'%s')]";
        return By.xpath(String.format(locator, itemName));
    }
    public Boolean cartIsEmpty(){
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(cartListSelector));
    }

    public String validateSorting(){
        List<WebElement> items = driver.findElements(itemList);
        if(!items.isEmpty()){
            WebElement firstItem = items.getFirst();
            return firstItem.findElement(By.cssSelector("[data-test='inventory-item-name']")).getText();
        }
        else {
            return "Item not found";
        }
    }

    public void logoutSite(){
        this.ClickOnElement(this.menuButton);
        this.ClickOnElement(this.logout);
        String succesfulLogout = driver.getCurrentUrl();
        Assert.assertEquals(succesfulLogout,"https://www.saucedemo.com/");
    }

    public void validatePrices(){
        List<WebElement> items = driver.findElements(itemList);
        BigDecimal totalPrice = BigDecimal.ZERO;
            for (WebElement item : items) {
                WebElement priceTag = item.findElement(By.className("inventory_item_price"));
                String priceText = priceTag.getText().replace("$","");
                BigDecimal price = new BigDecimal(priceText);
                totalPrice = totalPrice.add(price);
            }
            WebElement itemTotal = wait.until(ExpectedConditions.visibilityOfElementLocated(itemTotalAmountLabel));
            String item = itemTotal.getText();
            Assert.assertTrue(item.contains(totalPrice.toString()));
        }

    public void placerOrderButton(){
        this.ClickOnElement(placerOrderButton);
    }

    public void backToProducts(){
        this.ClickOnElement(backToProducts);
    }
    public void goToCheckout(){
        this.ClickOnElement(goToCheckout);
    }
    public void goToPayment(){
        this.ClickOnElement(continueButton);
    }
    public int validateMandatoryFields(){
        List<WebElement> errorElements = driver.findElements(errorSelector);
        return errorElements.size();
    }
    public void validateOrderIsCompleted(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(thankYouMessage));
        Assert.assertTrue(element.isDisplayed());
    }

    public void checkVisibility(By selector) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
            Assert.assertTrue(element.isDisplayed());
        } catch (org.openqa.selenium.TimeoutException e) {
            Assert.fail("The element is not present in the form " + e.getMessage());
        }
    }
    public void ClickOnElement(By selector){
        WebElement elementToBeClicked = wait.until(ExpectedConditions.elementToBeClickable(selector));
        elementToBeClicked.click();
    }

    public void addToCart(){
        this.ClickOnElement(addToCartSelector);
    }

    public void goToCart(){
        this.ClickOnElement(goToCartSelector);
    }
    public void continueShopping(){
        this.ClickOnElement(continueShoppingButton);
    }

    public void selectFilter(String filter){
        WebElement filterOptions = wait.until(ExpectedConditions.presenceOfElementLocated(filterSelector));
        Select filterType = new Select(filterOptions);
        switch (filter){
            case "Z to A":
                    filterType.selectByIndex(1);
            break;
            case "Low to High":
                    filterType.selectByIndex(2);
            break;
            case "High to Low":
                    filterType.selectByIndex(3);
            break;
        }
    }
    public void inputData(String data, String inputToBeFilled){
        switch (inputToBeFilled) {
            case "firstName":
            WebElement firstNameElement = wait.until(ExpectedConditions.elementToBeClickable(firstNameInput));
            firstNameElement.clear();
            firstNameElement.sendKeys(data);
            break;
            case "lastName":
            WebElement lastNameElement = wait.until(ExpectedConditions.elementToBeClickable(lastNameInput));
            lastNameElement.clear();
            lastNameElement.sendKeys(data);
            break;
            case "zipCode":
            WebElement zipCodeElement = wait.until(ExpectedConditions.elementToBeClickable(zipCodeInput));
            zipCodeElement.clear();
            zipCodeElement.sendKeys(data);
            break;
        }
    }
}

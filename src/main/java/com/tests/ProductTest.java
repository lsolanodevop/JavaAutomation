package com.tests;
import com.pages.loginPage;
import com.pages.productPage;
import com.utils.siteData;
import com.utils.userData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

public class ProductTest {
    private WebDriver driver;
    private loginPage loginPage;
    private productPage productPage;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        loginPage = new loginPage(driver);
        productPage = new productPage(driver);
        driver.get("https://www.saucedemo.com/");
    }
    @Test
    public void invalidLogin(){
        loginPage.inputData("username", String.valueOf(userData.peter));
        loginPage.inputData("password", "public_sauce");
        loginPage.submit();
        loginPage.assertErrorMessage();
    }
    @Test
    public void addingAndRemovingProducts(){
        loginPage.inputData("username", String.valueOf(userData.standard_user));
        loginPage.inputData("password", "secret_sauce");
        loginPage.submit();
        productPage.ClickOnElement(productPage.getItemTitle(siteData.BACKPACK_TITLE));
        productPage.addToCart();
        productPage.goToCart();
        productPage.ClickOnElement(productPage.removeItem(siteData.BACKPACK_REMOVE_BUTTON));
        Assert.assertTrue(productPage.cartIsEmpty());
    }
    @Test
    public void sortProducts(){
        loginPage.inputData("username", String.valueOf(userData.standard_user));
        loginPage.inputData("password", "secret_sauce");
        loginPage.submit();
        productPage.selectFilter("Z to A");
        Assert.assertEquals(productPage.validateSorting(),siteData.REDSHIRT_TITLE);
        productPage.selectFilter("Low to High");
        Assert.assertEquals(productPage.validateSorting(),siteData.ONESIE_TITLE);
        productPage.selectFilter("High to Low");
        Assert.assertEquals(productPage.validateSorting(),siteData.FLEECE_TITLE);
    }
    @Test
    public void itemDetailViewTitle(){
        loginPage.inputData("username", String.valueOf(userData.standard_user));
        loginPage.inputData("password", "secret_sauce");
        loginPage.submit();
        productPage.ClickOnElement(productPage.getItemTitle(siteData.BIKELIGHT_TITLE));
        productPage.checkVisibility(productPage.getItemTitle(siteData.BIKELIGHT_TITLE));
    }
    @Test
    public void itemDetailViewImage(){
        loginPage.inputData("username", String.valueOf(userData.standard_user));
        loginPage.inputData("password", "secret_sauce");
        loginPage.submit();
        productPage.ClickOnElement(productPage.getImageSelector(siteData.BOLTSHIRT_IMAGE));
        productPage.checkVisibility(productPage.getItemTitle(siteData.BOLTSHIRT_TITLE));
    }
    @Test
    public void validateMandatoryFields(){
        loginPage.inputData("username", String.valueOf(userData.standard_user));
        loginPage.inputData("password", "secret_sauce");
        loginPage.submit();
        productPage.ClickOnElement(productPage.getItemTitle(siteData.ONESIE_TITLE));
        productPage.addToCart();
        productPage.goToCart();
        productPage.goToCheckout();
        productPage.goToPayment();
        Assert.assertEquals(productPage.validateMandatoryFields(),3);
    }
    @Test
    public void goToCheckout(){
        loginPage.inputData("username", String.valueOf(userData.standard_user));
        loginPage.inputData("password", "secret_sauce");
        loginPage.submit();
        productPage.ClickOnElement(productPage.getItemTitle(siteData.ONESIE_TITLE));
        productPage.addToCart();
        productPage.backToProducts();
        productPage.ClickOnElement(productPage.getItemTitle(siteData.BOLTSHIRT_TITLE));
        productPage.addToCart();
        productPage.goToCart();
        productPage.goToCheckout();
        productPage.inputData(String.valueOf(userData.peter),"firstName");
        productPage.inputData(String.valueOf(userData.parker),"lastName");
        productPage.inputData("BCR123","zipCode");
        productPage.goToPayment();
        productPage.validatePrices();
        productPage.placerOrderButton();
        productPage.validateOrderIsCompleted();
        productPage.logoutSite();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

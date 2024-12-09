package tests;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LocatorTest extends BaseTest {

    @Test (testName = "Examples of each type of locator", description = "Check working with known types of locators.", retryAnalyzer = Retry.class)
    @Description("Check working with known types of locators.")
    public void locatorTest() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("login-button"));
        driver.findElement(By.name("password"));
        driver.findElement(By.className("login_credentials"));
        driver.findElement(By.tagName("form"));

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        driver.findElement(By.linkText("Sauce Labs Bolt T-Shirt"));
        driver.findElement(By.partialLinkText("Bike"));

        //CSS Selector
        driver.findElement(By.cssSelector(".title"));
        driver.findElement(By.cssSelector(".btn.btn_primary"));
        driver.findElement(By.cssSelector(".pricebar .inventory_item_price"));
        driver.findElement(By.cssSelector("#item_0_title_link"));
        driver.findElement(By.cssSelector("button"));
        driver.findElement(By.cssSelector("li.social_facebook"));
        driver.findElement(By.cssSelector("[data-test='item-2-title-link']"));
        driver.findElement(By.cssSelector("[class~='btn_small']")); //mistake
        driver.findElement(By.cssSelector("[data-test|='about']"));
        driver.findElement(By.cssSelector("[data-test^='item-3']"));
        driver.findElement(By.cssSelector("[name$='backpack']"));
        driver.findElement(By.cssSelector("[data-test*='ogou']"));

        //xpath
        driver.findElement(By.xpath("//div//button[@name ='add-to-cart-sauce-labs-bolt-t-shirt']"));
        driver.findElement(By.xpath("//div[text()='Swag Labs']"));
        driver.findElement(By.xpath("//div[contains(@class,'item_name')][1]"));
        driver.findElement(By.xpath("//div[contains(text(),'superhero')]"));
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']//ancestor::div[1]"));
        driver.findElement(By.xpath("descendant::div[@class='pricebar'][1]"));
        driver.findElement(By.xpath("//div[@class='pricebar']/following::div"));
        driver.findElement(By.xpath("//option[text()='Name (A to Z)']//parent::select"));
        driver.findElement(By.xpath("//div[@class='footer_copy']//preceding::li[3]"));
        driver.findElement(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory ' and @name='add-to-cart-sauce-labs-backpack']"));
    }

}

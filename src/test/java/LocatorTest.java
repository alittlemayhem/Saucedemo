import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LocatorTest extends BaseTest{

    @Test
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
        driver.findElement(By.cssSelector(".select_container.product_sort_container"));
        driver.findElement(By.cssSelector(".shopping_cart_container .shopping_cart_link"));
        driver.findElement(By.cssSelector("#item_0_title_link"));
        driver.findElement(By.cssSelector("button"));
        driver.findElement(By.cssSelector("li.social_facebook"));
        driver.findElement(By.cssSelector("[data-test='item-2-title-link']"));
        driver.findElement(By.cssSelector("[data-test~='2']"));
        driver.findElement(By.cssSelector("[data-test|='about']"));
        driver.findElement(By.cssSelector("[data-test^=]='item-3'"));
        driver.findElement(By.cssSelector("[name$='backpack']"));
        driver.findElement(By.cssSelector("[data-test*='ogou']"));

        //xpath
        driver.findElement(By.xpath("//div//button[@name ='add-to-cart-sauce-labs-bolt-t-shirt']"));
        driver.findElement(By.xpath("//div[text()='Swag Labs']"));
        driver.findElement(By.xpath("//div/span[contains(@data-test,'container')]");
        driver.findElement(By.xpath("//div[contains(text(),'superhero')]"));
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']//ancestor::div[1]"));
        driver.findElement(By.xpath("descendant::div[@class='pricebar'][1]"));
        driver.findElement(By.xpath("//div[@class='pricebar']/following::div"));
        driver.findElement(By.xpath("//option[text()='Name (A to Z)']//parent::select"));
        driver.findElement(By.xpath("//div[@class='footer_copy']//preceding::li[3]"));
        driver.findElement(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory ' and @name='add-to-cart-sauce-labs-backpack']"));
    }

}

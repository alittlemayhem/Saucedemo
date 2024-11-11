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
        driver.findElement(By.cssSelector(""));
        driver.findElement(By.cssSelector(""));
        driver.findElement(By.cssSelector());
        driver.findElement(By.cssSelector());
        driver.findElement(By.cssSelector());
        driver.findElement(By.cssSelector());
        driver.findElement(By.cssSelector());
        driver.findElement(By.cssSelector());

        //xpath
        driver.findElement(By.xpath());
        driver.findElement(By.xpath());
        driver.findElement(By.xpath());
        driver.findElement(By.xpath());
        driver.findElement(By.xpath());
        driver.findElement(By.xpath());
        driver.findElement(By.xpath());
        driver.findElement(By.xpath());
        driver.findElement(By.xpath());
        driver.findElement(By.xpath());
    }

}

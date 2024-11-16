package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private final By TITLE = By.cssSelector(".title");
    private final String ITEM_PATTERN = "//*[@class='cart_item_label']//div[text()='%s']";
    private final String PRICE_PATTERN = "//*[@class='item_pricebar']//div[text()='%s']";


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public String getProduct(String product) {
        return driver.findElement(By.xpath(String.format(ITEM_PATTERN, product))).getText();
    }

    public String getPrice(String price) {
        return driver.findElement(By.xpath(String.format(PRICE_PATTERN, price))).getText();
    }

    public void removeProduct(String product) {
        String buttonId = "remove-".concat(String.join("-", product.toLowerCase().split("\\s")));
        driver.findElement(By.id(buttonId)).click();
    }

    public void goToCheckout() {
        driver.findElement(By.id("checkout")).click();
    }
}

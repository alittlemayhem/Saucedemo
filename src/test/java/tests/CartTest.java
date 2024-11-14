package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class CartTest extends BaseTest {

    ArrayList<String> products = new ArrayList<String>() {
        {
            add("Sauce Labs Backpack");
            add("Sauce Labs Bike Light");
            add("Sauce Labs Bolt T-Shirt");
            add("Sauce Labs Fleece Jacket");
            add("Sauce Labs Onesie");
            add("Test.allTheThings() T-Shirt (Red)");
        }
    };

    ArrayList<String> prices = new ArrayList<String>() {
        {
            add("29.99");
            add("9.99");
            add("15.99");
            add("49.99");
            add("7.99");
            add("15.99");
        }
    };

    @Test
    public void addOneProductToCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.addProductToCart(products.get(1));
        productsPage.switchToCart();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(cartPage.getTitle(), "Your Cart");
        softAssert.assertEquals(cartPage.getProduct(products.get(1)), "Sauce Labs Bike Light");
        softAssert.assertEquals(cartPage.getPrice(prices.get(1)), "$9.99");
        softAssert.assertAll();
    }

    @Test
    public void addSeveralProductsToCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.addProductToCart(products.get(0));
        productsPage.addProductToCart(products.get(3));

        productsPage.switchToCart();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getTitle(), "Your Cart");

        softAssert.assertEquals(cartPage.getProduct(products.get(0)), "Sauce Labs Backpack");
        softAssert.assertEquals(cartPage.getPrice(prices.get(0)), "$29.99");

        softAssert.assertEquals(cartPage.getProduct(products.get(3)), "Sauce Labs Fleece Jacket");
        softAssert.assertEquals(cartPage.getPrice(prices.get(3)), "$49.99");

        softAssert.assertAll();
    }

    @Test
    public void removeOneProductFromCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.addProductToCart(products.get(1));
        productsPage.addProductToCart(products.get(2));
        productsPage.switchToCart();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(cartPage.getTitle(), "Your Cart");
        softAssert.assertEquals(cartPage.getProduct(products.get(1)), "Sauce Labs Bike Light");
        softAssert.assertEquals(cartPage.getPrice(prices.get(1)), "$9.99");
        softAssert.assertEquals(cartPage.getProduct(products.get(2)), "Sauce Labs Bolt T-Shirt");
        softAssert.assertEquals(cartPage.getPrice(prices.get(2)), "$15.99");

        List<WebElement> removeButton = driver.findElements(By.xpath("//button[text() = 'Remove']"));

        softAssert.assertEquals(removeButton.size(), 2);

        cartPage.removeProduct(products.get(1));
        softAssert.assertTrue(driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt")).isDisplayed());

        List<WebElement> buttonDeleteAfterDelete = driver.findElements(By.xpath("//button[text() = 'Remove']"));
        softAssert.assertEquals(buttonDeleteAfterDelete.size(), 1);

        softAssert.assertAll();
    }

    @Test
    public void removeSeveralProductsToCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.addProductToCart(products.get(0));
        productsPage.addProductToCart(products.get(3));

        productsPage.switchToCart();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getTitle(), "Your Cart");

        softAssert.assertEquals(cartPage.getProduct(products.get(0)), "Sauce Labs Backpack");
        softAssert.assertEquals(cartPage.getPrice(prices.get(0)), "$29.99");

        softAssert.assertEquals(cartPage.getProduct(products.get(3)), "Sauce Labs Fleece Jacket");
        softAssert.assertEquals(cartPage.getPrice(prices.get(3)), "$49.99");

        List<WebElement> removeButton = driver.findElements(By.xpath("//button[text() = 'Remove']"));

        softAssert.assertEquals(removeButton.size(), 2);

        cartPage.removeProduct(products.get(3));

        List<WebElement> buttonDeleteAfterDelete = driver.findElements(By.xpath("//button[text() = 'Remove']"));
        softAssert.assertEquals(buttonDeleteAfterDelete.size(), 1);

        cartPage.removeProduct(products.get(0));

        buttonDeleteAfterDelete = driver.findElements(By.xpath("//button[text() = 'Remove']"));
        softAssert.assertEquals(buttonDeleteAfterDelete.size(), 0);

        softAssert.assertAll();
    }

    @Test
    public void checkSwitchToCheckout() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.addProductToCart(products.get(0));
        productsPage.addProductToCart(products.get(3));

        productsPage.switchToCart();

        cartPage.goToCheckout();
        assertEquals(
                checkoutPage.getTitle(),
                "Checkout: Your Information",
                "Did not switched to checkout");
    }
}
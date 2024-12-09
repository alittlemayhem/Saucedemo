package tests;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

    @Test (testName = "Addition of one product to cart", description = "Check that one prodcut can be added to cart.")
    @Description("Check that one product - Sauce Labs Bike Light - can be added to cart.")
    public void addOneProductToCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.addProductToCart(products.get(1));
        productsPage.switchToCart();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(
                cartPage.getTitle(),
                "Your Cart",
                "Shopping cart is not opened.");
        softAssert.assertEquals(
                cartPage.getProduct(products.get(1)),
                "Sauce Labs Bike Light",
                "Product 'Sauce Labs Bike Light' was not found.");
        softAssert.assertEquals(
                cartPage.getPrice(prices.get(1)),
                "$9.99",
                "Incorrect price is shown.");
        softAssert.assertAll();
    }

    @Test (testName = "Addition of several products to cart", description = "Check that several products can be added to cart.")
    @Description("Check that several products - Sauce Labs Backpack, Sauce Labs Fleece Jacket - can be added to cart.")
    public void addSeveralProductsToCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.addProductToCart(products.get(0));
        productsPage.addProductToCart(products.get(3));

        productsPage.switchToCart();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(
                cartPage.getTitle(),
                "Your Cart",
                "Shopping cart is not opened.");

        softAssert.assertEquals(
                cartPage.getProduct(products.get(0)),
                "Sauce Labs Backpack",
                "Product 'Sauce Labs Backpack' was not found.");
        softAssert.assertEquals(
                cartPage.getPrice(prices.get(0)),
                "$29.99",
                "Incorrect price is shown for Sauce Labs Backpack");

        softAssert.assertEquals(
                cartPage.getProduct(products.get(3)),
                "Sauce Labs Fleece Jacket",
                "Product 'Sauce Labs Fleece Jacket' was not found.");
        softAssert.assertEquals(
                cartPage.getPrice(prices.get(3)),
                "$49.99",
                "Incorrect price is shown for Sauce Labs Fleece Jacket");

        softAssert.assertAll();
    }

    @Test (testName = "Removal of product from cart", description = "Check that one product can be added and removed from cart.")
    @Description("Check that one product can be added and removed from cart, when there are multiple products")
    public void removeOneProductFromCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.addProductToCart(products.get(1));
        productsPage.addProductToCart(products.get(2));
        productsPage.switchToCart();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(
                cartPage.getTitle(),
                "Your Cart",
                "Shopping cart is not opened.");
        softAssert.assertEquals(cartPage.getProduct(products.get(1)),
                "Sauce Labs Bike Light",
                "Product 'Sauce Labs Bike Light' was not found.");
        softAssert.assertEquals(cartPage.getPrice(prices.get(1)),
                "$9.99",
                "Incorrect price is shown for first product.");
        softAssert.assertEquals(
                cartPage.getProduct(products.get(2)),
                "Sauce Labs Bolt T-Shirt",
                "Product 'Sauce Labs Bolt T-Shirt' was not found.");
        softAssert.assertEquals(cartPage.getPrice(prices.get(2)),
                "$15.99",
                "Incorrect price is shown for second product.");

        List<WebElement> removeButton = driver.findElements(By.xpath("//button[text() = 'Remove']"));

        softAssert.assertEquals(
                removeButton.size(),
                2,
                "Incorrect number of products are on the page.");

        cartPage.removeProduct(products.get(1));
        softAssert.assertTrue(driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt")).isDisplayed());

        List<WebElement> buttonDeleteAfterDelete = driver.findElements(By.xpath("//button[text() = 'Remove']"));
        softAssert.assertEquals(
                buttonDeleteAfterDelete.size(),
                1,
                "Incorrect number of products are on the page.");

        softAssert.assertAll();
    }

    @Test (testName = "Removal of several products from cart", description = "Check that several products can be added and removed from cart.")
    @Description("Check that all items can be removed from shopping cart")
    public void removeSeveralProductsToCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.addProductToCart(products.get(0));
        productsPage.addProductToCart(products.get(3));

        productsPage.switchToCart();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(
                cartPage.getTitle(),
                "Your Cart",
                "Shopping cart is not opened.");

        softAssert.assertEquals(
                cartPage.getProduct(products.get(0)),
                "Sauce Labs Backpack",
                "Product 'Sauce Labs Backpack' was not found.");
        softAssert.assertEquals(
                cartPage.getPrice(prices.get(0)),
                "$29.99",
                "Incorrect price is shown for first product.");

        softAssert.assertEquals(
                cartPage.getProduct(products.get(3)),
                "Sauce Labs Fleece Jacket",
                "Product 'Sauce Labs Fleece Jacket' was not found.");
        softAssert.assertEquals(
                cartPage.getPrice(prices.get(3)),
                "$49.99",
                "Incorrect price is shown for second product.");

        List<WebElement> removeButton = driver.findElements(By.xpath("//button[text() = 'Remove']"));

        softAssert.assertEquals(
                removeButton.size(),
                2,
                "Incorrect number of products are on the page.");

        cartPage.removeProduct(products.get(3));

        List<WebElement> buttonDeleteAfterDelete = driver.findElements(By.xpath("//button[text() = 'Remove']"));
        softAssert.assertEquals(
                buttonDeleteAfterDelete.size(),
                1,
                "Product was not removed.");

        cartPage.removeProduct(products.get(0));

        buttonDeleteAfterDelete = driver.findElements(By.xpath("//button[text() = 'Remove']"));
        softAssert.assertEquals(
                buttonDeleteAfterDelete.size(),
                0,
                "Page is not empty.");

        softAssert.assertAll();
    }

    @Test (testName = "Proceeding to checkout from cart", description = "Check that switching to Checkout page happens.")
    @Description("Check that switching to Checkout page 'Checkout: Your Information' happens.")
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
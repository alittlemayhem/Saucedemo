package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProductsTest extends  BaseTest{

    @Test
    public void testSwitchingToCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.switchToCart();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(
                cartPage.getTitle(),
                "Your Cart",
                "Shopping cart is not opened.");
    }
}

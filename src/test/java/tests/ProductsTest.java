package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProductsTest extends  BaseTest{

    @Test (testName = "Switching to SHopping Cart", description = "Check that it is possible to open Shopping cart from Products page.")
    @Description("Check that it is possible to open Shopping cart from Products page even when nothing was added to cart.")
    public void testSwitchingToCart() {
        loginPage.open();
        loginPage.login(user, password);

        productsPage.switchToCart();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(
                cartPage.getTitle(),
                "Your Cart",
                "Shopping cart is not opened.");
    }
}

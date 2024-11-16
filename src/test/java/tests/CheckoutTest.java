package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutTest extends BaseTest {

    @Test
    public void testCheckoutWithAllFields() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.switchToCart();
        cartPage.goToCheckout();
        checkoutPage.enterCheckoutData("Test Name", "Test last", "1234");
        assertEquals(
                checkoutPage.getTitle(),
                "Checkout: Overview",
                "Next step of checkout did not happen,");
    }

    @Test
    public void testCheckoutWithoutFisrtName() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.switchToCart();
        cartPage.goToCheckout();
        checkoutPage.enterCheckoutData("", "Test last", "1234");
        assertEquals(
                checkoutPage.getError(),
                "Error: First Name is required",
                "Checkout without first name occurred");
    }

    @Test
    public void testCheckoutWithoutLastName() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.switchToCart();
        cartPage.goToCheckout();
        checkoutPage.enterCheckoutData("Test Name", "", "1234");
        assertEquals(
                checkoutPage.getError(),
                "Error: Last Name is required",
                "Checkout without last name occurred");
    }

    @Test
    public void testCheckoutWithoutPostcode() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.switchToCart();
        cartPage.goToCheckout();
        checkoutPage.enterCheckoutData("Test Name", "Test last", "");
        assertEquals(
                checkoutPage.getError(),
                "Error: Postal Code is required",
                "Checkout without postcode occurred");
    }
}

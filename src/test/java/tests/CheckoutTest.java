package tests;

import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutTest extends BaseTest {

    @Test(testName = "Checkout with valid data", description = "Check that next page of checkout opens after entering valid data.")
    @Description("Check that next page of checkout - 'Checkout: Overview' - opens after entering valid data to form.")
    public void testCheckoutWithAllFields() {
        loginPage.open();
        loginPage.login(user, password);
        productsPage.switchToCart();
        cartPage.goToCheckout();
        checkoutPage.enterCheckoutData("Test Name", "Test last", "1234");
        assertEquals(
                checkoutPage.getTitle(),
                "Checkout: Overview",
                "Next step of checkout did not happen,");
    }

    @DataProvider(name = "checkoutData")
    public Object[][] checkoutData() {
        return new Object[][]{
                {"", "Test last", "1234", "Error: First Name is required"},
                {"Test Name", "", "1234", "Error: Last Name is required"},
                {"Test Name", "Test last", "", "Error: Postal Code is required"},
        };
    }

    @Test(dataProvider = "checkoutData", testName = "Check of negative checkout variations", description = "Check checkout with different invalid data.")
    @Description("Test of negative scenarios - when either first name, or last name, or zipcode is NOT entered.")
    public void testCheckoutWithInvalidData(String first_name, String last_name, String postcode, String expectedError) {
        loginPage.open();
        loginPage.login(user, password);
        productsPage.switchToCart();
        cartPage.goToCheckout();
        checkoutPage.enterCheckoutData(first_name, last_name, postcode);
        assertEquals(
                checkoutPage.getError(),
                expectedError,
                "Checkout without first name occurred");
    }
}

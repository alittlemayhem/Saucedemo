package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(testName = "Check of positive login", description = "Check login to Saucedemo with valid credentials.")
    public void checkLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(
                productsPage.getTitle(),
                "Products",
                "Transfer to Products page did not occur.");
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"", "", "Epic sadface: Username is required"},
                {"test_name", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"},
                {"standard_user", "1111", "Epic sadface: Username and password do not match any user in this service"},
        };
    }

    @Test(dataProvider = "loginData", testName = "Check of negative login variations", description = "Check login to Saucedemo with different invalid credentials.")
    public void negativeTestsOfLogin(String user, String password, String expectedError) {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(
                loginPage.getError(),
                expectedError,
                "Login with incorrect password happened");
    }
}

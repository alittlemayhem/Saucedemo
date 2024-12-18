package tests;


import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(testName = "Check of positive login", description = "Check login to Saucedemo with valid credentials.")
    @Description("Positive test of login to Saucedemo with valid credentials.")
    public void checkLogin() {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(
                productsPage.getTitle(),
                "Products",
                "Transfer to Products page did not occur.");
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"", password, "Epic sadface: Username is required"},
                {user, "", "Epic sadface: Password is required"},
                {"", "", "Epic sadface: Username is required"},
                {"test_name", password, "Epic sadface: Username and password do not match any user in this service"},
                {user, "1111", "Epic sadface: Username and password do not match any user in this service"},
        };
    }

    @Test(dataProvider = "loginData", testName = "Check of negative login variations", description = "Check login to Saucedemo with different invalid credentials.")
    @Description("Check login to Saucedemo with various invalid credentials - empty login/password fields, no creds, incorrect username/password")
    public void negativeTestsOfLogin(String user, String password, String expectedError) {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(
                loginPage.getError(),
                expectedError,
                "Login with incorrect password happened");
    }
}

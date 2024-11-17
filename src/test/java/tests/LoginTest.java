package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test
    public void checkLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(
                productsPage.getTitle(),
                "Products",
                "Transfer to Products page did not occur.");
    }

    @Test
    public void checkLoginWithEmptyUserName() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertEquals(
                loginPage.getError(),
                "Epic sadface: Username is required",
                "Login with empty user happened");
    }

    @Test
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals(
                loginPage.getError(),
                "Epic sadface: Password is required",
                "Login with empty password happened");
    }

    @Test
    public void checkLoginWithoutAnyCredentials() {
        loginPage.open();
        loginPage.login("", "");
        assertEquals(
                loginPage.getError(),
                "Epic sadface: Username is required",
                "Login with empty user happened");
    }

    @Test
    public void checkLoginWithIncorrectName() {
        loginPage.open();
        loginPage.login("test_name", "secret_sauce");
        assertEquals(
                loginPage.getError(),
                "Epic sadface: Username and password do not match any user in this service",
                "Login with incorrect username happened");
    }

    @Test
    public void checkLoginWithIncorrectPassword() {
        loginPage.open();
        loginPage.login("standard_user", "1111");
        assertEquals(
                loginPage.getError(),
                "Epic sadface: Username and password do not match any user in this service",
                "Login with incorrect password happened");
    }
}

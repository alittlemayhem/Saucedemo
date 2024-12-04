package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By USER_INPUT = By.id("user-name");
    private final By PASSWORD = By.id("password");
    private final By LOGIN_BUTTON = By.id("login-button");
    private final By ERROR_MESSAGE = By.xpath("//h3[@data-test = 'error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open login page")
    public void open() {
        driver.get("https://www.saucedemo.com/");
    }

    @Step("Enter system with credentials: login - {user} and password - {password}")
    public void login(String user, String password) {
        driver.findElement(USER_INPUT).sendKeys(user);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    @Step("Get error message")
    public String getError() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}

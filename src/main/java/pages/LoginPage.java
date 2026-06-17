package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private final By emailField = By.xpath(".//input[@name='name' and @type='text']");
    private final By passwordField = By.xpath(".//input[@name='Пароль' and @type='password']");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By registerLink = By.xpath(".//a[@href='/register']");
    private final By forgotPasswordLink = By.xpath(".//a[@href='/forgot-password']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввести email: {email}")
    public void enterEmail(String email) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        field.clear();
        field.sendKeys(email);
    }

    @Step("Ввести пароль")
    public void enterPassword(String password) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        field.clear();
        field.sendKeys(password);
    }

    @Step("Нажать кнопку 'Войти'")
    public void clickLoginButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        button.click();
    }

    @Step("Выполнить вход с email: {email}")
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    @Step("Нажать на ссылку 'Зарегистрироваться'")
    public void clickRegisterLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(registerLink));
        link.click();
    }

    @Step("Нажать на ссылку 'Восстановить пароль'")
    public void clickForgotPasswordLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink));
        link.click();
    }

    @Step("Проверить, что страница входа загружена")
    public boolean isLoginPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Дождаться загрузки страницы входа")
    public void waitForLoginPageLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        System.out.println("Страница входа загружена");
    }
}
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends BasePage {
    // Локаторы страницы регистрации
    private final By nameField = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath("//input[@name='Пароль' and @type='password']");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By loginLink = By.xpath("//a[@href='/login']");
    private final By errorMessage = By.xpath("//p[@class='input__error text_type_main-default']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void enterName(String name) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        field.clear();
        field.sendKeys(name);
    }

    public void enterEmail(String email) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        field.clear();
        field.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        field.clear();
        field.sendKeys(password);
    }

    public void clickRegisterButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        button.click();
    }

    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }

    public void clickLoginLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        link.click();
    }

    public String getErrorMessage() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return error.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isRegisterPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(registerButton));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForRegisterPageLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerButton));
    }
}
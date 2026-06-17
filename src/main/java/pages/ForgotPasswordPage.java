package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ForgotPasswordPage extends BasePage {

    private final By loginLink = By.xpath(".//a[@href='/login']");
    private final By restoreButton = By.xpath(".//button[text()='Восстановить']");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать на ссылку 'Войти' на странице восстановления пароля")
    public void clickLoginLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        link.click();
    }

    @Step("Дождаться загрузки страницы восстановления пароля")
    public void waitForPageLoad() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(restoreButton));
            System.out.println("Страница восстановления пароля загружена");
        } catch (Exception e) {
            // Если кнопка "Восстановить" не найдена, пробуем найти ссылку "Войти"
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginLink));
            System.out.println("Страница восстановления пароля загружена (альтернативная проверка)");
        }
    }
}
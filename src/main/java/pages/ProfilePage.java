package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage {

    private final By logoutButton = By.xpath("//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public void clickLogoutButton() {
        System.out.println("Поиск кнопки 'Выход'...");
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        button.click();
        System.out.println("Кнопка 'Выход' нажата");
    }

    public void waitForProfilePageLoad() {
        System.out.println("Ожидание загрузки страницы профиля...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
        System.out.println("Страница профиля загружена");
    }
}
package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

    // Локаторы главной страницы
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");
    private final By constructorButton = By.xpath("//p[text()='Конструктор']");
    private final By logo = By.xpath("//div[contains(@class, 'AppHeader_header__logo')]");

    private final By pageLoaded = By.xpath("//h1[text()='Соберите бургер']");

    // Табы конструктора
    private final By bunsTab = By.xpath("//span[text()='Булки']");
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");

    // Активный таб
    private final By activeTab = By.xpath("//div[contains(@class, 'tab_tab_type_current')]//span");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void clickLoginButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        button.click();
    }

    public void clickPersonalAccount() {
        WebElement accountButton = wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton));
        accountButton.click();
    }

    public void clickConstructor() {
        WebElement constructor = wait.until(ExpectedConditions.elementToBeClickable(constructorButton));
        constructor.click();
    }

    public void clickLogo() {
        WebElement logoElement = wait.until(ExpectedConditions.elementToBeClickable(logo));
        logoElement.click();
    }

    public void goToBunsTab() {
        WebElement buns = wait.until(ExpectedConditions.elementToBeClickable(bunsTab));
        buns.click();
        wait.until(ExpectedConditions.textToBe(activeTab, "Булки"));
    }

    public void goToSaucesTab() {
        WebElement sauces = wait.until(ExpectedConditions.elementToBeClickable(saucesTab));
        sauces.click();
        wait.until(ExpectedConditions.textToBe(activeTab, "Соусы"));
    }

    public void goToFillingsTab() {
        WebElement fillings = wait.until(ExpectedConditions.elementToBeClickable(fillingsTab));
        fillings.click();
        wait.until(ExpectedConditions.textToBe(activeTab, "Начинки"));
    }

    public String getActiveTabText() {
        WebElement active = wait.until(ExpectedConditions.visibilityOfElementLocated(activeTab));
        return active.getText();
    }

    public boolean isMainPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(pageLoaded));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForMainPageLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageLoaded));
        System.out.println("Главная страница загружена");
    }
}
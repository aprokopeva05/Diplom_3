import config.BrowserDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;
import java.time.Duration;

public abstract class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected RegisterPage registerPage;
    protected ProfilePage profilePage;
    protected ForgotPasswordPage forgotPasswordPage;

    protected static final String BASE_URL = "https://qa-stellarburgers.education-services.ru";

    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        System.out.println("Запуск браузера: " + browser);

        driver = BrowserDriver.createWebDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("Открытие URL: " + BASE_URL);
        driver.get(BASE_URL);

        wait.until(driver -> ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));

        System.out.println("Текущий URL: " + driver.getCurrentUrl());

        // Инициализация Page Objects
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        profilePage = new ProfilePage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);

        mainPage.waitForMainPageLoad();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Браузер закрыт");
        }
    }
}
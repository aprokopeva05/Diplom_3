import config.BrowserDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import pages.*;

public abstract class BaseTest {
    protected WebDriver driver;
    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected RegisterPage registerPage;
    protected ProfilePage profilePage;
    protected ForgotPasswordPage forgotPasswordPage;

    protected static final String BASE_URL = "https://qa-stellarburgers.education-services.ru";

    @BeforeEach
    public void setUp() {
        driver = BrowserDriver.createWebDriver();

        System.out.println("Открытие URL: " + BASE_URL);
        driver.get(BASE_URL);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
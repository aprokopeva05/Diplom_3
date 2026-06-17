package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserDriver {

    /*
    Переменные окружения, прописанные в системе:
    WEBDRIVERS - путь к папке с драйверами для браузеров
    YANDEX_BROWSER_DRIVER_FILENAME - имя файла драйвера Яндекс браузера (Хромдрайвера нужной версии)
    YANDEX_BROWSER_PATH - путь к исполняемому файлу Яндекс браузера в системе
     */

    public static WebDriver createWebDriver() {
        String browser = System.getProperty("browser", "chrome");

        switch (browser.toLowerCase()) {
            case "yandex":
                return createYandexDriver();
            case "chrome":
            default:
                return createChromeDriver();
        }
    }

    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--start-maximized");

        System.out.println("Создан драйвер для Google Chrome");
        return new ChromeDriver(options);
    }

    private static WebDriver createYandexDriver() {
        // Используем переменные окружения или значения по умолчанию
        String webDriversPath = System.getenv("WEBDRIVERS");
        String driverFileName = System.getenv("YANDEX_BROWSER_DRIVER_FILENAME");
        String yandexPath = System.getenv("YANDEX_BROWSER_PATH");

        // Если переменные окружения не заданы, используем значения по умолчанию
        if (webDriversPath == null) {
            webDriversPath = System.getProperty("user.dir") + "/src/test/resources/drivers";
        }

        if (driverFileName == null) {
            driverFileName = "yandexdriver.exe";
        }

        if (yandexPath == null) {
            yandexPath = System.getProperty("user.home") +
                    "\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";
        }

        System.setProperty("webdriver.chrome.driver",
                String.format("%s/%s", webDriversPath, driverFileName));

        ChromeOptions options = new ChromeOptions();
        options.setBinary(yandexPath);
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--start-maximized");

        System.out.println("Создан драйвер для Яндекс.Браузера");
        return new ChromeDriver(options);
    }
}
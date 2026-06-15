import api.UserApi;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;

@Epic("UI Автотесты Stellar Burgers")
@Feature("Регистрация пользователя")
@DisplayName("Тесты регистрации")
public class RegistrationTest extends BaseTest {

    private User testUser;
    private String accessToken = null;

    @BeforeEach
    @Step("Подготовка тестовых данных: создание уникального пользователя")
    public void prepareTestData() {
        String uniqueSuffix = String.valueOf(System.currentTimeMillis());
        testUser = new User(
                "testuser_" + uniqueSuffix + "@test.com",
                "password123",
                "Тестовый пользователь"
        );
    }

    @AfterEach
    @Step("Удаление тестового пользователя через API")
    public void deleteTestUser() {
        if (accessToken != null && !accessToken.isEmpty()) {
            UserApi.deleteUser(accessToken);
            System.out.println("Пользователь удален: " + testUser.getEmail());
        }
    }

    @Test
    @Story("Успешная регистрация")
    @DisplayName("Успешная регистрация пользователя")
    @Description("Тест проверяет, что пользователь может успешно зарегистрироваться с валидными данными")
    @Step("Тест успешной регистрации: переход на страницу регистрации -> заполнение формы -> проверка перехода на страницу входа")
    public void testSuccessfulRegistration() {
        mainPage.clickLoginButton();
        loginPage.waitForLoginPageLoad();
        loginPage.clickRegisterLink();
        registerPage.waitForRegisterPageLoad();

        registerPage.register(
                testUser.getName(),
                testUser.getEmail(),
                testUser.getPassword()
        );

        loginPage.waitForLoginPageLoad();
        assertTrue(loginPage.isLoginPageLoaded(),
                "После успешной регистрации должен быть переход на страницу входа");
        assertTrue(driver.getCurrentUrl().contains("/login"),
                "URL должен быть страницей входа");

        Response loginResponse = UserApi.loginUser(testUser);
        if (loginResponse.getStatusCode() == 200) {
            accessToken = UserApi.getAccessToken(loginResponse);
            System.out.println("Получен токен для удаления пользователя: " + testUser.getEmail());
        }
    }

    @Test
    @Story("Ошибка при регистрации")
    @DisplayName("Ошибка при коротком пароле")
    @Description("Тест проверяет, что при вводе пароля менее 6 символов появляется ошибка")
    @Step("Тест регистрации с коротким паролем: переход на страницу регистрации -> заполнение формы с паролем -> проверка сообщения об ошибке")
    public void testRegistrationWithShortPassword() {
        String shortPassword = "12345";

        mainPage.clickLoginButton();
        loginPage.waitForLoginPageLoad();
        loginPage.clickRegisterLink();
        registerPage.waitForRegisterPageLoad();

        registerPage.register(
                testUser.getName(),
                testUser.getEmail(),
                shortPassword
        );

        String errorMessage = registerPage.getErrorMessage();
        assertTrue(errorMessage.contains("Некорректный пароль"),
                "Должно появиться сообщение о некорректном пароле. Фактическое сообщение: " + errorMessage);
    }
}
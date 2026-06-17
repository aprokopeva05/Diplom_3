import api.UserApi;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Step;
import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;

@Epic("UI Автотесты Stellar Burgers")
@Feature("Вход в аккаунт")
@DisplayName("Тестирование входа в аккаунт")
public class LoginTest extends BaseTest {

    private User testUser;
    private String accessToken;

    @BeforeEach
    @Step("Создание тестового пользователя через API")
    public void createTestUser() {
        String uniqueSuffix = String.valueOf(System.currentTimeMillis());
        testUser = new User(
                "logintest_" + uniqueSuffix + "@test.com",
                "validpass123",
                "Тестовый пользователь"
        );

        Response response = UserApi.createUser(testUser);
        assertEquals(200, response.getStatusCode());
        accessToken = UserApi.getAccessToken(response);
    }

    @AfterEach
    @Step("Удаление тестового пользователя")
    public void deleteTestUser() {
        if (accessToken != null) {
            UserApi.deleteUser(accessToken);
        }
    }

    @Test
    @Story("Вход через главную страницу")
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной странице")
    @Description("Проверка, что пользователь может войти через главную кнопку входа")
    @Step("Выполнение теста входа через главную кнопку")
    public void testLoginViaMainButton() {
        mainPage.clickLoginButton();
        loginPage.waitForLoginPageLoad();
        loginPage.login(testUser.getEmail(), testUser.getPassword());
        mainPage.waitForMainPageLoad();

        assertTrue(mainPage.isMainPageLoaded(),
                "После успешного входа должна открыться главная страница");
    }

    @Test
    @Story("Вход через личный кабинет")
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("Проверка, что пользователь может войти через кнопку личного кабинета")
    @Step("Выполнение теста входа через личный кабинет")
    public void testLoginViaPersonalAccountButton() {
        mainPage.clickPersonalAccount();
        loginPage.waitForLoginPageLoad();
        loginPage.login(testUser.getEmail(), testUser.getPassword());
        mainPage.waitForMainPageLoad();

        assertTrue(mainPage.isMainPageLoaded(),
                "После успешного входа должна открыться главная страница");
    }

    @Test
    @Story("Вход через страницу регистрации")
    @DisplayName("Вход через ссылку 'Войти' в форме регистрации")
    @Description("Проверка, что пользователь может войти через форму регистрации")
    @Step("Выполнение теста входа через форму регистрации")
    public void testLoginViaRegisterForm() {
        mainPage.clickLoginButton();
        loginPage.waitForLoginPageLoad();
        loginPage.clickRegisterLink();
        registerPage.waitForRegisterPageLoad();
        registerPage.clickLoginLink();
        loginPage.waitForLoginPageLoad();
        loginPage.login(testUser.getEmail(), testUser.getPassword());
        mainPage.waitForMainPageLoad();

        assertTrue(mainPage.isMainPageLoaded(),
                "После успешного входа должна открыться главная страница");
    }

    @Test
    @Story("Вход через страницу восстановления пароля")
    @DisplayName("Вход через ссылку 'Войти' в форме восстановления пароля")
    @Description("Проверка, что пользователь может войти через форму восстановления пароля")
    @Step("Выполнение теста входа через форму восстановления пароля")
    public void testLoginViaForgotPasswordForm() {
        System.out.println("Начинаем тест входа через восстановление пароля");

        mainPage.clickLoginButton();
        loginPage.waitForLoginPageLoad();

        loginPage.clickForgotPasswordLink();
        forgotPasswordPage.waitForPageLoad();

        forgotPasswordPage.clickLoginLink();
        loginPage.waitForLoginPageLoad();

        loginPage.login(testUser.getEmail(), testUser.getPassword());
        mainPage.waitForMainPageLoad();

        assertTrue(mainPage.isMainPageLoaded(),
                "После успешного входа должна открыться главная страница");
        System.out.println("Тест входа через восстановление пароля пройден");
    }
}
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
@Feature("Навигация в личном кабинете")
@DisplayName("Тестирование навигации в личном кабинете")
public class ProfileNavigationTest extends BaseTest {

    private User testUser;
    private String accessToken;

    @BeforeEach
    @Step("Создание тестового пользователя через API и авторизация")
    public void createAndLoginUser() {
        String uniqueSuffix = String.valueOf(System.currentTimeMillis());
        testUser = new User(
                "navtest_" + uniqueSuffix + "@test.com",
                "navpass123",
                "Навигация Тест"
        );

        Response response = UserApi.createUser(testUser);
        assertEquals(200, response.getStatusCode());
        accessToken = UserApi.getAccessToken(response);

        mainPage.clickLoginButton();
        loginPage.waitForLoginPageLoad();
        loginPage.login(testUser.getEmail(), testUser.getPassword());
        mainPage.waitForMainPageLoad();
    }

    @AfterEach
    @Step("Удаление тестового пользователя")
    public void deleteTestUser() {
        if (accessToken != null) {
            UserApi.deleteUser(accessToken);
        }
    }

    @Test
    @Story("Переход в личный кабинет")
    @DisplayName("Переход в личный кабинет")
    @Description("Проверка перехода в личный кабинет из авторизованного состояния")
    @Step("Тест: Переход в личный кабинет")
    public void testNavigateToProfile() {
        mainPage.clickPersonalAccount();
        profilePage.waitForProfilePageLoad();
        assertTrue(driver.getCurrentUrl().contains("/account"));
    }

    @Test
    @Story("Возврат в конструктор")
    @DisplayName("Переход из личного кабинета в конструктор через кнопку")
    @Description("Проверка перехода из профиля в конструктор по кнопке 'Конструктор'")
    @Step("Тест: Переход в конструктор через кнопку")
    public void testReturnToConstructorViaButton() {
        mainPage.clickPersonalAccount();
        profilePage.waitForProfilePageLoad();
        mainPage.clickConstructor();
        mainPage.waitForMainPageLoad();
        assertTrue(mainPage.isMainPageLoaded());
    }

    @Test
    @Story("Возврат в конструктор через логотип")
    @DisplayName("Переход из личного кабинета в конструктор через логотип")
    @Description("Проверка перехода из профиля в конструктор по клику на логотип")
    @Step("Тест: Переход в конструктор через логотип")
    public void testReturnToConstructorViaLogo() {
        mainPage.clickPersonalAccount();
        profilePage.waitForProfilePageLoad();
        mainPage.clickLogo();
        mainPage.waitForMainPageLoad();
        assertTrue(mainPage.isMainPageLoaded());
    }
}
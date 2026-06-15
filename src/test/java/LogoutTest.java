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
@Feature("Выход из аккаунта")
@DisplayName("Тестирование выхода из аккаунта")
public class LogoutTest extends BaseTest {

    private User testUser;
    private String accessToken;

    @BeforeEach
    @Step("Создание тестового пользователя через API и авторизация")
    public void createAndLoginUser() {
        String uniqueSuffix = String.valueOf(System.currentTimeMillis());
        testUser = new User(
                "logouttest_" + uniqueSuffix + "@test.com",
                "logoutpass123",
                "Выход Тест"
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
    @Story("Выход из аккаунта")
    @DisplayName("Выход из аккаунта через кнопку 'Выйти' в личном кабинете")
    @Description("Проверка, что пользователь может выйти из аккаунта")
    @Step("Тест: Выход из аккаунта")
    public void testLogoutFromProfile() {
        // Переход в личный кабинет и ожидание загрузки страницы профиля
        mainPage.clickPersonalAccount();
        profilePage.waitForProfilePageLoad();

        // Нажатие на кнопку "Выйти"
        profilePage.clickLogoutButton();

        // Ожидание перехода на страницу входа и проверка
        loginPage.waitForLoginPageLoad();
        assertTrue(loginPage.isLoginPageLoaded());
        assertTrue(driver.getCurrentUrl().contains("/login"));
    }
}
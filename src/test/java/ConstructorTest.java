import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Epic("UI Автотесты Stellar Burgers")
@Feature("Конструктор бургеров")
@DisplayName("Тестирование работы конструктора")
public class ConstructorTest extends BaseTest {

    @BeforeEach
    @Step("Ожидание загрузки главной страницы перед каждым тестом")
    public void waitForMainPage() {
        mainPage.waitForMainPageLoad();
    }

    @Test
    @Story("Переключение разделов")
    @DisplayName("Переход к разделу 'Булки' в конструкторе")
    @Description("Проверка, что при клике на 'Булки' открывается соответствующий раздел")
    @Step("Тест: Переключение на раздел 'Булки'")
    public void testNavigateToBunsSection() {
        mainPage.goToSaucesTab();
        mainPage.goToBunsTab();

        String activeTab = mainPage.getActiveTabText();
        assertEquals("Булки", activeTab,
                "Активным должен быть раздел 'Булки'");
    }

    @Test
    @Story("Переключение разделов")
    @DisplayName("Переход к разделу 'Соусы' в конструкторе")
    @Description("Проверка, что при клике на 'Соусы' открывается соответствующий раздел")
    @Step("Тест: Переключение на раздел 'Соусы'")
    public void testNavigateToSaucesSection() {
        mainPage.goToSaucesTab();

        String activeTab = mainPage.getActiveTabText();
        assertEquals("Соусы", activeTab,
                "Активным должен быть раздел 'Соусы'");
    }

    @Test
    @Story("Переключение разделов")
    @DisplayName("Переход к разделу 'Начинки' в конструкторе")
    @Description("Проверка, что при клике на 'Начинки' открывается соответствующий раздел")
    @Step("Тест: Переключение на раздел 'Начинки'")
    public void testNavigateToFillingsSection() {
        mainPage.goToFillingsTab();

        String activeTab = mainPage.getActiveTabText();
        assertEquals("Начинки", activeTab,
                "Активным должен быть раздел 'Начинки'");
    }

    @Test
    @Story("Переключение разделов")
    @DisplayName("Последовательное переключение между всеми разделами")
    @Description("Проверка возможности последовательно переключаться между разделами")
    @Step("Тест: Последовательное переключение между разделами: Соусы -> Начинки -> Булки -> Соусы")
    public void testNavigateThroughAllSections() {
        mainPage.goToSaucesTab();
        assertEquals("Соусы", mainPage.getActiveTabText());

        mainPage.goToFillingsTab();
        assertEquals("Начинки", mainPage.getActiveTabText());

        mainPage.goToBunsTab();
        assertEquals("Булки", mainPage.getActiveTabText());

        mainPage.goToSaucesTab();
        assertEquals("Соусы", mainPage.getActiveTabText());
    }
}
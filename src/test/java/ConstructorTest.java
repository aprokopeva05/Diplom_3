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
    public void waitForMainPage() {
        mainPage.waitForMainPageLoad();
    }

    @Test
    @Story("Переключение разделов")
    @DisplayName("Переход к разделу 'Соусы' в конструкторе")
    @Description("Проверка, что при клике на 'Соусы' открывается раздел с соусами")
    @Step("Тест: Переход к разделу 'Соусы'")
    public void testNavigateToSaucesSection() {
        mainPage.goToSaucesTab();
        assertTrue(mainPage.isSaucesSectionDisplayed(),
                "Раздел 'Соусы' должен отображаться после клика на соответствующий таб");
    }

    @Test
    @Story("Переключение разделов")
    @DisplayName("Переход к разделу 'Начинки' в конструкторе")
    @Description("Проверка, что при клике на 'Начинки' открывается раздел с начинками")
    @Step("Тест: Переход к разделу 'Начинки'")
    public void testNavigateToFillingsSection() {
        mainPage.goToFillingsTab();
        assertTrue(mainPage.isFillingsSectionDisplayed(),
                "Раздел 'Начинки' должен отображаться после клика на соответствующий таб");
    }

    @Test
    @Story("Переключение разделов")
    @DisplayName("Переход к разделу 'Булки' в конструкторе")
    @Description("Проверка, что при клике на 'Булки' открывается раздел с булками")
    @Step("Тест: Переход к разделу 'Булки'")
    public void testNavigateToBunsSection() {
        // Сначала переключаемся на Соусы, чтобы убедиться, что переключение работает
        mainPage.goToSaucesTab();
        // Затем переключаемся на Булки
        mainPage.goToBunsTab();
        assertTrue(mainPage.isBunsSectionDisplayed(),
                "Раздел 'Булки' должен отображаться после клика на соответствующий таб");
    }

    @Test
    @Story("Переключение разделов")
    @DisplayName("Последовательное переключение между всеми разделами")
    @Description("Проверка, что при последовательном переключении табов открываются соответствующие разделы")
    @Step("Тест: Последовательное переключение между разделами")
    public void testNavigateThroughAllSections() {
        // Переключаемся на Соусы
        mainPage.goToSaucesTab();
        assertTrue(mainPage.isSaucesSectionDisplayed(),
                "При переключении на 'Соусы' должен открыться раздел с соусами");

        // Переключаемся на Начинки
        mainPage.goToFillingsTab();
        assertTrue(mainPage.isFillingsSectionDisplayed(),
                "При переключении на 'Начинки' должен открыться раздел с начинками");

        // Переключаемся на Булки
        mainPage.goToBunsTab();
        assertTrue(mainPage.isBunsSectionDisplayed(),
                "При переключении на 'Булки' должен открыться раздел с булками");

        // Снова переключаемся на Соусы для проверки повторного переключения
        mainPage.goToSaucesTab();
        assertTrue(mainPage.isSaucesSectionDisplayed(),
                "При повторном переключении на 'Соусы' снова должен открыться раздел с соусами");
    }
}
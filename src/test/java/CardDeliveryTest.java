import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardDeliveryTest {
    private String generateDate(int addDays) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldPositiveValueAllInput() {
        Configuration.holdBrowserOpen = true;
        $("span[data-test-id=city] input").setValue("Сам");
        $(".input__menu").find(withText("Самара")).click();
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        String currentDay = generateDate(5);
        $("[data-test-id='date'] input").setValue(currentDay);
        $("span[data-test-id=name] input").setValue("Гусева Анастасия");
        $("span[data-test-id=phone] input").setValue("+79083846408");
        $("label[data-test-id=agreement]").click();
        $("button.button").click();
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(Condition.exactText("Встреча успешно забронирована на " + currentDay));
    }

    @Test
    void shouldCityEmpty() {
        Configuration.holdBrowserOpen = true;
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        String currentDay = generateDate(5);
        $("[data-test-id='date'] input").setValue(currentDay);
        $("span[data-test-id=name] input").setValue("Гусева Анастасия");
        $("span[data-test-id=phone] input").setValue("+79083846408");
        $("label[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNegativeCityValue() {
        Configuration.holdBrowserOpen = true;
        $("span[data-test-id=city] input").setValue("Самfhf");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        String currentDay = generateDate(5);
        $("[data-test-id='date'] input").setValue(currentDay);
        $("span[data-test-id=name] input").setValue("Гусева Анастасия");
        $("span[data-test-id=phone] input").setValue("+79083846408");
        $("label[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id='city'] .input__sub").shouldBe(visible).shouldHave(text("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldDateEmpty() {
        Configuration.holdBrowserOpen = true;
        $("span[data-test-id=city] input").setValue("Сам");
        $(".input__menu").find(withText("Самара")).click();
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("span[data-test-id=name] input").setValue("Гусева Анастасия");
        $("span[data-test-id=phone] input").setValue("+79083846408");
        $("label[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id='date'] .input__sub").shouldBe(visible).shouldHave(text("Неверно введена дата"));
    }

    @Test
    void shouldNegativeDataValue() {
        Configuration.holdBrowserOpen = true;
        $("span[data-test-id=city] input").setValue("Сам");
        $(".input__menu").find(withText("Самара")).click();
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        String currentDay = generateDate(2);
        $("[data-test-id='date'] input").setValue(currentDay);
        $("span[data-test-id=name] input").setValue("Гусева Анастасия");
        $("span[data-test-id=phone] input").setValue("+79083846408");
        $("label[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id='date'] .input__sub").shouldBe(visible).shouldHave(text("Заказ на выбранную дату невозможен"));
    }

    @Test
    void shouldNameEmpty() {
        Configuration.holdBrowserOpen = true;
        $("span[data-test-id=city] input").setValue("Сам");
        $(".input__menu").find(withText("Самара")).click();
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        String currentDay = generateDate(4);
        $("[data-test-id='date'] input").setValue(currentDay);
        $("span[data-test-id=phone] input").setValue("+79083846408");
        $("label[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id='name'] .input__sub").shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldPhoneEmpty() {
        Configuration.holdBrowserOpen = true;
        $("span[data-test-id=city] input").setValue("Сам");
        $(".input__menu").find(withText("Самара")).click();
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        String currentDay = generateDate(4);
        $("[data-test-id='date'] input").setValue(currentDay);
        $("span[data-test-id=name] input").setValue("Гусева Анастасия");
        $("label[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id='phone'] .input__sub").shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void testNegativeAgreementEmpty() {
        $("span[data-test-id=city] input").setValue("Сам");
        $(".input__menu").find(withText("Самара")).click();
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        String currentDay = generateDate(4);
        $("[data-test-id='date'] input").setValue(currentDay);
        $("span[data-test-id=name] input").setValue("Гусева Анастасия");
        $("span[data-test-id=phone] input").setValue("+79083846408");
        $(".button__content").click();
        $("[data-test-id='agreement'].input_invalid").shouldBe(visible).shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

}

package org.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.PaymentBlockPage;
import org.example.pages.PaymentModalPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class MtsPaymentPageObjectTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private PaymentBlockPage paymentBlockPage;

    private final String phone = "297777777";
    private final double sum = 10;
    private final String expectedAmount = String.format(Locale.US, "%.2f BYN", sum);

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        org.openqa.selenium.chrome.ChromeOptions options = new org.openqa.selenium.chrome.ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options); // Передаем опции при создании

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        js = (JavascriptExecutor) driver;

        driver.get("https://www.mts.by/");

        // Закрываем куки
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("cookie-agree"))).click();
        } catch (Exception ignored) {}

        paymentBlockPage = new PaymentBlockPage(driver);
    }

    @Test
    public void shouldDisplayOnlinePaymentBlockTitle() {
        assertEquals("ОНЛАЙН ПОПОЛНЕНИЕ\nБЕЗ КОМИССИИ", paymentBlockPage.getPaymentTitle());
        System.out.println("Тест: Заголовок верный.");
    }

    @Test
    public void shouldDisplayCorrectPlaceholdersForConnection() {
        paymentBlockPage.clickServiceTypeButton();
        paymentBlockPage.selectServiceType("Услуги связи");
        assertEquals("Номер телефона", paymentBlockPage.getConnectionPhonePlaceholder());
        assertEquals("Сумма", paymentBlockPage.getConnectionAmountPlaceholder());
        assertEquals("E-mail для отправки чека", paymentBlockPage.getConnectionEmailPlaceholder());
        System.out.println("Тест: Плейсхолдеры Услуг связи верны.");
    }

    @Test
    public void shouldDisplayCorrectPlaceholdersForInternet() {
        paymentBlockPage.clickServiceTypeButton();
        paymentBlockPage.selectServiceType("Домашний интернет");
        assertEquals("Номер абонента", paymentBlockPage.getInternetPhonePlaceholder());
        assertEquals("Сумма", paymentBlockPage.getInternetAmountPlaceholder());
        assertEquals("E-mail для отправки чека", paymentBlockPage.getInternetEmailPlaceholder());
        System.out.println("Тест: Плейсхолдеры Домашнего интернета верны.");
    }

    @Test
    public void shouldDisplayCorrectPlaceholdersForInstallment() {
        paymentBlockPage.clickServiceTypeButton();
        paymentBlockPage.selectServiceType("Рассрочка");
        assertEquals("Номер счета на 44", paymentBlockPage.getInstallmentAccountPlaceholder());
        assertEquals("Сумма", paymentBlockPage.getInstallmentAmountPlaceholder());
        assertEquals("E-mail для отправки чека", paymentBlockPage.getInstallmentEmailPlaceholder());
        System.out.println("Тест: Плейсхолдеры Рассрочки верны.");
    }

    @Test
    public void shouldDisplayCorrectPlaceholdersForArrears() {
        paymentBlockPage.clickServiceTypeButton();
        paymentBlockPage.selectServiceType("Задолженность");
        assertEquals("Номер счета на 2073", paymentBlockPage.getArrearsAccountPlaceholder());
        assertEquals("Сумма", paymentBlockPage.getArrearsAmountPlaceholder());
        assertEquals("E-mail для отправки чека", paymentBlockPage.getArrearsEmailPlaceholder());
        System.out.println("Тест: Плейсхолдеры Задолженности верны.");
    }

    @Test
    public void shouldSubmitCommunicationServicePayment() {
        paymentBlockPage.fillConnectionForm(phone, String.valueOf(sum));
        paymentBlockPage.clickContinue();

        PaymentModalPage modalPage = new PaymentModalPage(driver);
        modalPage.waitOfVisibilityFrame(); // Переключаемся в iframe

        assertEquals(expectedAmount, modalPage.getPaymentAmount());
        assertTrue(modalPage.getPaymentDetails().contains("375" + phone));

        assertEquals("Номер карты", modalPage.getCardNumberFieldLabel());
        assertEquals("Срок действия", modalPage.getCardExpirationFieldLabel());
        assertEquals("CVC", modalPage.getCardCvcFieldLabel());
        assertEquals("Имя и фамилия на карте", modalPage.getCardHolderFieldLabel());

        assertEquals(5, modalPage.getPaymentSystemIcons().size(), "Иконок должно быть 5");
        assertTrue(modalPage.getPaymentButtonText().contains(expectedAmount));
        System.out.println("Тест: Оплата и модальное окно проверены успешно!");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
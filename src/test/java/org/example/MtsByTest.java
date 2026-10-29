package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MtsByTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
        js = (JavascriptExecutor) driver;

        driver.get("https://www.mts.by/");

        try {
            Thread.sleep(3000);
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            screenshot.renameTo(new File("mts_page.png"));
        } catch (Exception e) {
            System.out.println("Не удалось сделать скриншот: " + e.getMessage());
        }

        try {
            WebElement cookieBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//button[contains(text(), 'Принять') or contains(text(), 'Согласен') or contains(text(), 'Принять все')]")
            ));
            js.executeScript("arguments[0].click();", cookieBtn);
            System.out.println("Баннер с куки закрыт через JavaScript.");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Баннер с куки не найден или уже закрыт: " + e.getMessage());
        }
    }

    @Test
    public void testMtsPaymentBlock() {
        System.out.println("Проверка 1: Название блока");
        WebElement blockTitle = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(text(), 'Онлайн пополнение')]")
        ));
        js.executeScript("arguments[0].scrollIntoView(true);", blockTitle);
        assertTrue(blockTitle.isDisplayed(), "Блок 'Онлайн пополнение' не найден!");
        System.out.println("Блок найден: " + blockTitle.getText());

        System.out.println("Проверка 2: Логотипы платежных систем");
        List<WebElement> logos = driver.findElements(
                By.xpath("//img[contains(@src, 'visa') or contains(@src, 'mastercard') or contains(@src, 'belkart') or contains(@src, 'mir')]")
        );
        assertFalse(logos.isEmpty(), "Логотипы платежных систем не найдены!");

        System.out.println("Проверка 3: Ссылка 'Подробнее о сервисе'");
        WebElement moreLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(text(), 'Подробнее о сервисе')]")
        ));
        js.executeScript("arguments[0].scrollIntoView(true);", moreLink);
        assertTrue(moreLink.isDisplayed(), "Ссылка 'Подробнее о сервисе' не найдена!");

        System.out.println("Проверка 4: Заполнение формы и кнопка 'Продолжить'");

        WebElement communicationTab = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(text(), 'Услуги связи')]")
        ));
        js.executeScript("arguments[0].click();", communicationTab);

        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[contains(@placeholder, 'Номер телефона') or contains(@placeholder, 'номер')]")
        ));
        phoneInput.sendKeys("297777777");

        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Продолжить')]")
        ));
        continueBtn.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean isPaymentWindowOpened = driver.findElements(
                By.xpath("//*[contains(text(), 'Оплата') or contains(text(), 'Сумма') or contains(text(), 'Введите сумму')]")
        ).size() > 0;

        assertTrue(isPaymentWindowOpened, "Кнопка 'Продолжить' не сработала или не открылось окно оплаты!");
        System.out.println("Все проверки пройдены успешно!");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
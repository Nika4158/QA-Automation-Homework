package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class PaymentModalPage extends BasePage {

    @FindBy(xpath = "//iframe[@class='payment-widget-iframe']")
    private WebElement paymentFrame;

    @FindBy(xpath = "//div[@class='pay-description__cost']/span")
    private WebElement paymentAmount;

    @FindBy(xpath = "//div[@class='pay-description__text']/span")
    private WebElement paymentDetails;

    @FindBy(xpath = "//input[@id='cc-number']/following-sibling::label")
    private WebElement cardNumberFieldLabel;

    @FindBy(xpath = "//input[@formcontrolname='expirationDate']/following-sibling::label")
    private WebElement cardExpirationFieldLabel;

    @FindBy(xpath = "//input[@formcontrolname='cvc']/following-sibling::label")
    private WebElement cardCvcFieldLabel;

    @FindBy(xpath = "//input[@formcontrolname='holder']/following-sibling::label")
    private WebElement cardHolderFieldLabel;

    @FindBy(xpath = "//app-card-page//button[@type='submit']")
    private WebElement payButton;

    @FindBy(xpath = "//div[contains(@class, 'cards-brands__container')]//img")
    private List<WebElement> paymentSystemIcons;

    public PaymentModalPage(WebDriver driver) {
        super(driver);
    }

    public void waitOfVisibilityFrame() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ignored) {}

        try {
            driver.switchTo().defaultContent();

            WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(30));

            longWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@class='payment-widget-iframe']")));

            longWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(paymentFrame));

            longWait.until(ExpectedConditions.visibilityOf(paymentAmount));

            Thread.sleep(1000);

        } catch (Exception e) {
            System.out.println("Модальное окно не открылось. Делаю скриншот...");
            try {
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                screenshot.renameTo(new File("modal_error.png"));
                System.out.println("Скриншот сохранен в корень проекта: modal_error.png");
            } catch (Exception ex) {
                System.out.println("Не удалось сделать скриншот: " + ex.getMessage());
            }
            throw new RuntimeException("Не удалось дождаться загрузки iframe", e);
        }
    }

    public String getPaymentAmount() {
        return paymentAmount.getText();
    }

    public String getPaymentDetails() {
        return paymentDetails.getText();
    }

    public String getCardNumberFieldLabel() { return cardNumberFieldLabel.getText(); }
    public String getCardExpirationFieldLabel() { return cardExpirationFieldLabel.getText(); }
    public String getCardCvcFieldLabel() { return cardCvcFieldLabel.getText(); }
    public String getCardHolderFieldLabel() { return cardHolderFieldLabel.getText(); }

    public List<WebElement> getPaymentSystemIcons() {
        return paymentSystemIcons;
    }

    public String getPaymentButtonText() {
        return payButton.getText().trim();
    }
}
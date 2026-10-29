package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PaymentModalPage extends BasePage {

    // Локатор iframe из кода одногруппницы
    @FindBy(xpath = "//iframe[@class='payment-widget-iframe']")
    private WebElement paymentFrame;

    @FindBy(css = "div.app-wrapper__content")
    private WebElement paymentFrameContainer;

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

    // Переключение в iframe (очень важно!)
    public void waitOfVisibilityFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(paymentFrame));
        wait.until(ExpectedConditions.visibilityOf(paymentFrameContainer));
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
package org.example.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PaymentBlockPage extends BasePage {

    @FindBy(xpath = "//section[@class='pay']//h2")
    private WebElement blockTitle;

    @FindBy(xpath = "//section[@class='pay']//form[1]//button")
    private WebElement continueButton;

    @FindBy(xpath = "//button[@class='select__header']")
    private WebElement serviceTypeButton;

    @FindBy(xpath = "//p[@class='select__option']")
    private List<WebElement> serviceTypeOptions;

    @FindBy(id = "connection-phone")
    private WebElement connectionPhoneField;
    @FindBy(id = "connection-sum")
    private WebElement connectionAmountField;
    @FindBy(id = "connection-email")
    private WebElement connectionEmailField;

    @FindBy(id = "internet-phone")
    private WebElement internetPhoneField;
    @FindBy(id = "internet-sum")
    private WebElement internetAmountField;
    @FindBy(id = "internet-email")
    private WebElement internetEmailField;

    @FindBy(id = "score-instalment")
    private WebElement installmentAccountField;
    @FindBy(id = "instalment-sum")
    private WebElement installmentAmountField;
    @FindBy(id = "instalment-email")
    private WebElement installmentEmailField;

    @FindBy(id = "score-arrears")
    private WebElement arrearsAccountField;
    @FindBy(id = "arrears-sum")
    private WebElement arrearsAmountField;
    @FindBy(id = "arrears-email")
    private WebElement arrearsEmailField;

    // Вот та самая переменная, которой не хватало!
    private final JavascriptExecutor js;

    public PaymentBlockPage(WebDriver driver) {
        super(driver);
        this.js = (JavascriptExecutor) driver; // Инициализация
    }

    public String getPaymentTitle() {
        return blockTitle.getText().replace("\r\n", "\n");
    }

    public void clickServiceTypeButton() {
        wait.until(ExpectedConditions.elementToBeClickable(serviceTypeButton)).click();
    }

    public void selectServiceType(String typeName) {
        wait.until(ExpectedConditions.visibilityOfAllElements(serviceTypeOptions));
        for (WebElement option : serviceTypeOptions) {
            if (option.getText().equals(typeName)) {
                // Тот самый JS-клик для обхода перекрытий
                js.executeScript("arguments[0].click();", option);
                break;
            }
        }
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
    }

    public void fillConnectionForm(String phone, String amount) {
        connectionPhoneField.sendKeys(phone);
        connectionAmountField.sendKeys(amount);
    }

    public void clickContinue() {
        continueButton.click();
    }

    public String getConnectionPhonePlaceholder() { return connectionPhoneField.getAttribute("placeholder"); }
    public String getConnectionAmountPlaceholder() { return connectionAmountField.getAttribute("placeholder"); }
    public String getConnectionEmailPlaceholder() { return connectionEmailField.getAttribute("placeholder"); }

    public String getInternetPhonePlaceholder() { return internetPhoneField.getAttribute("placeholder"); }
    public String getInternetAmountPlaceholder() { return internetAmountField.getAttribute("placeholder"); }
    public String getInternetEmailPlaceholder() { return internetEmailField.getAttribute("placeholder"); }

    public String getInstallmentAccountPlaceholder() { return installmentAccountField.getAttribute("placeholder"); }
    public String getInstallmentAmountPlaceholder() { return installmentAmountField.getAttribute("placeholder"); }
    public String getInstallmentEmailPlaceholder() { return installmentEmailField.getAttribute("placeholder"); }

    public String getArrearsAccountPlaceholder() { return arrearsAccountField.getAttribute("placeholder"); }
    public String getArrearsAmountPlaceholder() { return arrearsAmountField.getAttribute("placeholder"); }
    public String getArrearsEmailPlaceholder() { return arrearsEmailField.getAttribute("placeholder"); }
}
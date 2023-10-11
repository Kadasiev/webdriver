package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MailSignInPage extends BasePage {
    @FindBy(xpath = "//button[@data-show-counter='84226411']")
    private WebElement openSignInFormButton;

    @FindBy(xpath = "//iframe[@class='ag-popup__frame__layout__iframe']")
    private WebElement signInIframe;

    @FindBy(xpath = "//input[@class='input-0-2-71']")
    private WebElement usernameField;

    @FindBy(xpath = "//div/button[@class='base-0-2-79 primary-0-2-93']")
    private WebElement enterPasswordButton;

    @FindBy(xpath = "//div/input[@class='input-0-2-71 withIcon-0-2-72']")
    private WebElement passwordField;

    @FindBy(xpath = "//button/span[@class='inner-0-2-81 innerTextWrapper-0-2-82']")
    private WebElement signInButton;

    @FindBy(xpath = "//small[@class='base-0-2-25 small-0-2-34 error-0-2-40']")
    private WebElement errorMessageFromWrongUsername;

    @FindBy(xpath = "//div[@data-test-id='password-input-error']")
    private WebElement errorMessageFromWrongPassword;

    public MailSignInPage goTo(String url) {
        driver.get(url);
        return this;
    }

    public MailSignInPage openSignInWindow() {
        wait.until(ExpectedConditions.visibilityOf(openSignInFormButton));
        openSignInFormButton.click();
        return this;
    }

    public MailSignInPage enterUsername(String name) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(signInIframe));
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.sendKeys(name);
        enterPasswordButton.click();
        driver.switchTo().defaultContent();
        return this;
    }

    public MailMailboxPage enterPassword(String password) {
        driver.switchTo().frame(signInIframe);
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);
        signInButton.click();
        driver.switchTo().defaultContent();
        return new MailMailboxPage();
    }

    public String getErrorMessageFromWrongUsername() {
        driver.switchTo().frame(signInIframe);
        wait.until(ExpectedConditions.visibilityOf(errorMessageFromWrongUsername));
        String errorMessage = errorMessageFromWrongUsername.getText();
        driver.switchTo().defaultContent();
        return errorMessage;
    }

    public String getErrorMessageFromWrongPassword() {
        wait.until(ExpectedConditions.visibilityOf(errorMessageFromWrongPassword));
        return errorMessageFromWrongPassword.getText();
    }

    public String getErrorMessageFromEmptyPassword() {
        driver.switchTo().frame(signInIframe);
        wait.until(ExpectedConditions.visibilityOf(errorMessageFromWrongPassword));
        String errorMessage = errorMessageFromWrongPassword.getText();
        driver.switchTo().defaultContent();
        return errorMessage;
    }
}

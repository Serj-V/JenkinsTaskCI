package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class RegLoginPage extends Form {

    private final ITextBox email = AqualityServices.getElementFactory().getTextBox(By.xpath("//input[@name='email']"), "email field");
    private final ITextBox password = AqualityServices.getElementFactory().getTextBox(By.xpath("//input[@name='password']"), "password field");
    private final IButton login = AqualityServices.getElementFactory().getButton(By.xpath("//button[@data-at-selector='welcomeSignInBtn']"), "login button");
    private final IButton cookie = AqualityServices.getElementFactory().getButton(By.id("CybotCookiebotDialogBodyLevelButtonAccept"), "cookie");

    public RegLoginPage() {
        super(By.cssSelector("[data-at-selector='welcomeSignInTab']"),"RegLogin Page");
    }

    public void enterPasswordsLogin(String email, String password){
        cookie.click();
        this.email.clearAndType(email);
        this.password.clearAndType(password);
        login.click();
    }
}

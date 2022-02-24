package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class SubscriptionsPage extends Form {

    IButton downloads = AqualityServices.getElementFactory().getButton(By.xpath("//*[@data-at-menu='Downloads']"), "downloads button");

    public SubscriptionsPage() {
        super(By.xpath("//*[contains(@data-at-selector,'activationCodeBlock')]"), "subscriptions page");
    }

    public void clickDownloads(){
        downloads.click();
    }
}

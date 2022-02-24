package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class DownloadsPage extends Form {

    private final String osPattern = "//*[contains(@class,'u-osTile__title') and normalize-space(text()) = '%s' ]";
    private final String productNamePattern
            = "//*[@data-at-selector='downloadApplicationCard' and contains(.,' %s ')]//button[@data-at-selector='appInfoDownload']";

    private IButton os;
    private IButton productName;
    private final IButton otherDownloads = AqualityServices.getElementFactory().getButton(
            By.xpath("//*[@data-at-selector='otherDownloads']"), "Other Downloads");
    private final IButton sendByMail = AqualityServices.getElementFactory().getButton(
            By.xpath("//*[@data-at-selector='sendToMe']"), "send By Mail");
    private final IButton send = AqualityServices.getElementFactory().getButton(
            By.xpath("//*[@data-at-selector='installerSendSelfBtn']"), "send");
    private final ILabel downloadDialogBox = AqualityServices.getElementFactory().getLabel(
            By.xpath("//*[@data-at-selector='productNameBrand']"),"download Dialog Box");
    private final ILabel otherDownloadsWindow = AqualityServices.getElementFactory().getLabel(
            By.xpath("//*[contains(@class,'kl-other-downloads')]"),"other Downloads Window");

    public DownloadsPage() {
        super(By.xpath("//*[contains(@data-at-selector,'downloadBlockTrialAppsTitle')]"), "downloads page");
    }

    private void getOsButton(String os){
        this.os = AqualityServices.getElementFactory().getButton(By.xpath(String.format(osPattern, os)), "os name is:"+os);
    }

    private void getProductNameLink(String productName){
        this.productName = AqualityServices.getElementFactory().getButton(
                By.xpath(String.format(productNamePattern, productName)), "product name is:"+productName);
    }

    public void chooseOS(String osName){
        getOsButton(osName);
        os.click();
    }

    public void clickDownloadProduct(String product){
        getProductNameLink(product);
        productName.click();
    }

    public void clickOtherDownloads(){
        otherDownloads.click();
    }

    public void clickSendByMail(){
        sendByMail.click();
        send.click();
    }

    public boolean downloadDialogBoxIsOpen(){
        return downloadDialogBox.getElement().isDisplayed();
    }

    public boolean otherDownloadsWindowIsOpen(){
        return otherDownloadsWindow.getElement().isDisplayed();
    }
}

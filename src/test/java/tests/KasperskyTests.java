package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.DownloadsPage;
import pages.RegLoginPage;
import pages.SubscriptionsPage;
import utils.ConfigUtils;
import utils.FetchingEmailUtils;

import java.time.Duration;

public class KasperskyTests extends BaseTest {

    @Test
    @Parameters({"os", "productName"})
    public void checkingCorrectnessEmailsToDownload(String os, String productName){
        AqualityServices.getLogger().info("Log in");
        RegLoginPage regLoginPage = new RegLoginPage();
        regLoginPage.state().waitForDisplayed();
        System.out.println("This works!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        //regLoginPage.enterPasswordsLogin(ConfigUtils.getConfigString("mail"), "825963228qQ");
/*        AqualityServices.getLogger().info("Go to the \"Downloads\" tab");
        SubscriptionsPage subscriptionsPage = new SubscriptionsPage();
        subscriptionsPage.state().waitForDisplayed();
        subscriptionsPage.clickDownloads();
        AqualityServices.getLogger().info("Select the system and product");
        DownloadsPage downloadsPage = new DownloadsPage();
        downloadsPage.state().waitForDisplayed();
        downloadsPage.chooseOS(os);
        AqualityServices.getLogger().info("click \"download\"");
        downloadsPage.clickDownloadProduct(productName);
        Assert.assertTrue(downloadsPage.downloadDialogBoxIsOpen(), "The download dialog box is not open");
        AqualityServices.getLogger().info("In the dialog box, click \"Send by mail\"");
        downloadsPage.clickOtherDownloads();
        Assert.assertTrue(downloadsPage.otherDownloadsWindowIsOpen(), "The window with other downloads is not open");
        AqualityServices.getLogger().info("Click the \"Send\" button");
        downloadsPage.clickSendByMail();
        Assert.assertTrue(AqualityServices.getConditionalWait().waitFor(() -> FetchingEmailUtils.readLetter(productName),
                        Duration.ofSeconds(15), Duration.ofSeconds(1)),
                "A mail error or an email with the subject "+
                        productName+" does not contain the word you are looking for:"+ConfigUtils.getConfigString("searchWord"));*/
    }
}

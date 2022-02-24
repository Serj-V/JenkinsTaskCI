package utils;

import javax.mail.internet.MimeMultipart;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import org.openqa.selenium.NotFoundException;

public class FetchingEmailUtils {

    private static Store store;

    private static void initEmail()  {
        Properties properties = new Properties();
        properties.put("mail.imap.host", ConfigUtils.getConfigString("mailHost"));
        properties.put("mail.imap.port", ConfigUtils.getConfigString("mailPort"));
        properties.put("mail.imap.auth", "true");
        properties.put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.imap.socketFactory.fallback","false");
        properties.setProperty("mail.imap.socketFactory.port", ConfigUtils.getConfigString("mailPort"));
        Session session = Session.getInstance(properties);
        try {
            Store store = session.getStore("imap");
            store.connect(ConfigUtils.getConfigString("loginFromMail"), ConfigUtils.getConfigString("password"));
            FetchingEmailUtils.store = store;

        } catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException();
        }
    }

    public static boolean readLetter(String productName) {
        if(store == null) {
            initEmail();
        }
        try {
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);
            Message[] messages = emailFolder.getMessages();
            Message message = messages[messages.length - 1];
            String letterSubject = message.getSubject();
            if (letterSubject == null || !letterSubject.contains(productName)) {
                return  false;
            }

            MimeMultipart letterContent = (MimeMultipart) message.getContent();
            String messageText = letterContent.getBodyPart(0).getContent().toString();
            int beginIndex = messageText.indexOf("(https://");
            int endIndex = messageText.indexOf(")", beginIndex);
            String linkFromMessage = messageText.substring(beginIndex, endIndex);
            return linkFromMessage.contains(ConfigUtils.getConfigString("searchWord"));
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

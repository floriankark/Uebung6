package de.hhu.propra1.marketing.defaultservices;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class GMailSender implements IMailSender{

    /*
     * This is just a hack implementation!
     * Do not use this to actually send Mails!
     * */
    private static final String GMAIL_PASSWORD = "yourgmailpasswd";
    private static final String GMAIL_USERNAME = "yourmail@gmail.com";

    private final Properties props = configureMailService();

    private Properties configureMailService() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        return props;
    }

    @Override
    public void sendMail(String kunde) {
        Session session = createSession();
        try {
            Message message = createMessage(kunde, session);
            Transport.send(message);
            System.out.println("Mail sent succesfully!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private Message createMessage(String kunde, Session session) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("youremail@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(kunde));
        message.setSubject("Endlich Eiszeit");
        message.setText("Hallo,\nkomm doch auf ein frisches Eis vorbei!");
        return message;
    }

    private Session createSession() {
        return Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(GMAIL_USERNAME, GMAIL_PASSWORD);
            }
        });
    }

}

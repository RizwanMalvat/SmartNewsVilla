package com.smartnewsvillaadmin.utilities;

import com.smartnewsvillaadmin.exceptions.RequiredMessageException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    private String from;
    private String to;
    private String[] arrto;
    private String subject;
    private String text;
    private String displayName;

    public EmailSender(String from, String to, String subject, String text, String displayName) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
        this.displayName = displayName;
    }

    public EmailSender(String from, String[] arrto, String subject, String text, String displayName) {
        this.from = from;
        this.arrto = arrto;
        this.subject = subject;
        this.text = text;
        this.displayName = displayName;
    }

    public EmailSender() {
        super();
    }

    @Async
    public void send() {

        Properties props = new Properties();
        props.put("mail.smtp.host", "auth.smtp.1and1.co.uk");

//        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");

        Authenticator auth = new SMTPAuthenticator();
        Session mailSession = Session.getInstance(props, auth);
        // Session mailSession = Session.getDefaultInstance(props);
        Message simpleMessage = new MimeMessage(mailSession);

        InternetAddress fromAddress = null;
        InternetAddress toAddress = null;
        try {
            fromAddress = new InternetAddress(from, displayName);
            toAddress = new InternetAddress(to);

            //try {
            simpleMessage.setFrom(fromAddress);
            simpleMessage.setRecipient(RecipientType.TO, toAddress);
            simpleMessage.setSubject(subject);
            simpleMessage.setContent(text, "text/html");

            Transport.send(simpleMessage);
            //}
//                catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
            System.err.println("Email send.....");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Async
    public void sendMany() throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.host", "auth.smtp.1and1.co.uk");

//        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");

        Authenticator auth = new SMTPAuthenticator();
        Session mailSession = Session.getInstance(props, auth);
        // Session mailSession = Session.getDefaultInstance(props);
        Message simpleMessage = new MimeMessage(mailSession);

        InternetAddress fromAddress = null;
        InternetAddress arrtoAddress[] = null;
        try {
            fromAddress = new InternetAddress(from, displayName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //try {
        simpleMessage.setFrom(fromAddress);

        if (arrto != null) {
            arrtoAddress = new InternetAddress[arrto.length];
            for (int i = 0; i < arrto.length; i++) {
                arrtoAddress[i] = new InternetAddress(arrto[i]);
            }
            simpleMessage.setRecipients(RecipientType.TO, arrtoAddress);

            simpleMessage.setSubject(subject);
            simpleMessage.setContent(text, "text/html");

            Transport.send(simpleMessage);
        }
        //}
//                catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        System.err.println("Email send.....");
    }

    @Async
    public void sendToSubscribers() throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.host", "auth.smtp.1and1.co.uk");

//        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");

        Authenticator auth = new SMTPAuthenticator();
        Session mailSession = Session.getInstance(props, auth);
        // Session mailSession = Session.getDefaultInstance(props);
        Message simpleMessage = new MimeMessage(mailSession);

        InternetAddress fromAddress = null;
        InternetAddress toAddress = null;
        try {
            fromAddress = new InternetAddress(from, displayName);
            toAddress = new InternetAddress(to);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //try {
        simpleMessage.setFrom(fromAddress);
        simpleMessage.setRecipient(RecipientType.TO, toAddress);
        simpleMessage.setSubject(subject);
        //text=text+"<a href="+"http://duckecommerce.com/pg/Newsletters/unsubscribe/"+to+">Unsubscribe</a>";
//        text=text+"<a href="+"http://localhost:8080/MAEcommerce/pg/Newsletters/unsubscribe/"+to+">Unsubscribe</a>";
        simpleMessage.setContent(text, "text/html");

        Transport.send(simpleMessage);
        //}
//                catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        System.err.println("Email send.....");
    }

    @Async
    //  ==============This method is used to send challenge invitation by email.=======================
    public void sendEmail(HttpServletRequest request, String userName) throws AddressException, MessagingException, UnsupportedEncodingException, RequiredMessageException {

        if (arrto == null || arrto.length == 0) {
            // throw new RequiredMessageException("Email address(s) not found.");
        } else {

            String invalidAddresses = "";
            // Properties properties = System.getProperties();
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.host", "auth.smtp.1and1.co.uk");

            properties.setProperty("mail.smtp.auth", "true");

            Authenticator auth = new SMTPAuthenticator();

            Session mailSession = Session.getInstance(properties, auth);

            Message message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(from, userName));
            message.setSubject(subject);
            message.setContent(text, "text/html");

            for (String emailId : arrto) {
//                if (!MADuckUtil.checkEmail(emailId)) {
//                    invalidAddresses += emailId + ",";
//                } else {
                message.addRecipient(RecipientType.TO, new InternetAddress(emailId.trim()));
                Transport.send(message);
//                }
            }

            if (!(invalidAddresses == null || invalidAddresses.trim().equals(""))) {
                invalidAddresses = invalidAddresses.substring(0, invalidAddresses.lastIndexOf(','));
                throw new RequiredMessageException("Invalid Email Address(s) : " + invalidAddresses);

            }

//            message.addRecipient(RecipientType.TO, new InternetAddress("bhushan.bwt@gmail.com"));
//            Transport.send(message);
        }
        System.err.println("Email send.....");

    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {

        public PasswordAuthentication getPasswordAuthentication() {
            String username = "mukesh@bwt.in";
            String password = "9194286714539191";
            return new PasswordAuthentication(username, password);
        }
    }

}

package ua.com.vovacoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.vovacoffee.model.Order;
import ua.com.vovacoffee.model.User;
import ua.com.vovacoffee.service.SenderService;
import ua.com.vovacoffee.service.UserService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
public class SenderServiceImpl implements SenderService, Runnable{

    private UserService userService;

    private static final String CHARSET = "UTF-8";

    private static final String ENCODING = "Q";

    private User admin;

    private List<User> managers;

    private Order order;

    @Autowired
    public SenderServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void send(Order order) {
        this.order = order;
        new Thread(this).start();
    }

    @Override
    public void run() {
        if (order != null) {
            admin = userService.getMainAdministrator();
            managers = userService.getManagers();
            Collections.shuffle(managers);

            if (admin != null && !managers.isEmpty()) {
                choosePropertiesAndSend();
            }
        }
    }

    private void choosePropertiesAndSend() {
        Properties properties;
        String subject = "VovaCoffee || New Order " + order.getNumber();
        String text = order.toString();
        try {
            for (User manager : managers) {
                Thread.sleep(10000);
                try {
                    try {
                        properties = getTLSProperties();
                        sendMessage(properties, manager.getEmail(), subject, text);
                    } catch (MessagingException ex) {
                        ex.printStackTrace();

                        properties = getSSLProperties();
                        sendMessage(properties, manager.getEmail(), subject, text);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Properties getTLSProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        return properties;
    }

    @Override
    public Properties getSSLProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        return properties;
    }

    @Override
    public void sendMessage(Properties properties, String toEmail, String subject, String text) throws MessagingException, UnsupportedEncodingException {
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(admin.getEmail(), admin.getPassword());
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("support@vovacoffee.com.ua"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject(MimeUtility.encodeText(subject, CHARSET, ENCODING));
        message.setContent(text, "text/plain;charset=" + CHARSET);
        message.setSentDate(new Date());

        Transport.send(message);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public List<User> getManagers() {
        return managers;
    }

    public void setManagers(List<User> managers) {
        this.managers = managers;
    }
}

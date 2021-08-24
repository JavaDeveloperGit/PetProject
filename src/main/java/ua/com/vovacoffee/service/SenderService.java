package ua.com.vovacoffee.service;

import ua.com.vovacoffee.model.Order;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public interface SenderService {

    void send(Order order);

    Properties getTLSProperties();

    Properties getSSLProperties();

    void sendMessage(Properties properties, String toEmail, String subject, String text)
            throws MessagingException, UnsupportedEncodingException;
}

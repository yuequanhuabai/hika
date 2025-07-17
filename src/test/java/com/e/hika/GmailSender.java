package com.e.hika;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * GmailSender.
 */
public class GmailSender {

    public static void main(String[] args) {
        String to = "yuequanhuabai@gmail.com";

        String from = "2521761673@qq.com";
        String password = "";

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        session.setDebug(true); // 加到 Session 創建後

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("JavaMail 測試");
            message.setText("這是一封通過JavaEmail發送的測試郵件");
            Transport.send(message);
            System.out.println("郵件發送成功！");

        } catch (MessagingException e) {
            throw new RuntimeException("發送失敗" + e.getMessage());
        }


    }


    protected Object getVal(Object val, Field f, String dateFormat) {
        if (null == val) {
            return null;
        }

        String className = f.getType().getSimpleName();
        switch (className) {
            case "LocalDateTime":
                return val instanceof String ? LocalDateTimeUtil.parse(val.toString(), dateFormat) : val;
            case "LocalDate":
            case "LocalTime":
                return val;
            case "Date":
                return val instanceof String ? DateUtil.parse(val.toString(), dateFormat).toJdkDate() : val;
            case "Long":
                return Long.valueOf(val.toString());
            default:
                throw new UnsupportedOperationException("Unsupported field type: " + className);
        }
//        return val;

    }
}

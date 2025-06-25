package com.e.hika;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class GmailSender {

    public static void main(String[] args) {
        String to="yuequanhuabai@gmail.com";

        String from="2521761673@qq.com";
        String password="QwEr1357+-!#%&,.";

        String host="smtp.gmail.com";

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

        }catch (MessagingException e) {
            throw new RuntimeException("發送失敗"+e.getMessage());
        }






    }
}

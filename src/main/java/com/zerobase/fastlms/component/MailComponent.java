package com.zerobase.fastlms.component;


import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailComponent {

    private final JavaMailSender javaMailSender;

    public void sendMailTest(){

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("min318777@naver.com");
        msg.setSubject("Test");
        msg.setText("Hello World");

        javaMailSender.send(msg);
    }

    public boolean sendMail(String mail, String subject, String text){

        boolean result = false;
        MimeMessagePreparator msg = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "utf-8");
                message.setTo(mail);
                message.setSubject(subject);
                message.setText(text, true);
            }
        };
        try{
            javaMailSender.send(msg);
            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}

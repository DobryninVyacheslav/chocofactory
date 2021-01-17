package ru.mileev.chocofactory.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class MailSenderTest {

    @Autowired
    MailSender mailSender;
    @MockBean
    JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String username;

    @Test
    void whenSendEmailThenCallSendOfJavaMailSender() {
        // Arrange
        String mail = "mail@mail.ru";
        String subject = "subject";
        String msg = "msg";

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(mail);
        mailMessage.setSubject(subject);
        mailMessage.setText(msg);


        // Act
        mailSender.send(mail, subject, msg);

        // Assert
        verify(javaMailSender, times(1)).send(mailMessage);
    }

}
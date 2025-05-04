package com.example.lesson_15_email;


import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    /*
    для отправки почты используем класс JavaMailSender
     */

    private final JavaMailSender mailSender;

    /*
    надо создать метод, который умеет отправлять письма
     */

    public void sendConfirmationCodeByEmail(User user) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("lorajarik@gmail.com");
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Registration confirmation code");
        mailMessage.setText("Please confirm your registration code: " + user.getConfirmationCode());

        mailSender.send(mailMessage);

    }
}

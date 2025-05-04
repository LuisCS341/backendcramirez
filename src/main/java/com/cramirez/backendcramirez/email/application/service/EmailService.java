package com.cramirez.backendcramirez.email.application.service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String code) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

            String emailContent = "<p>Hola,</p>"
                    + "<p>Recibiste este correo porque solicitaste recuperar tu contraseña.</p>"
                    + "<p>Tu código de verificación es: <strong>" + code + "</strong></p>"
                    + "<p>Por favor, ingresa este código en la aplicación para continuar con el proceso.</p>"
                    + "<p>Si no realizaste esta solicitud, ignora este mensaje.</p>"
                    + "<br>"
                    + "<p>Atentamente,<br>El equipo de soporte.</p>";

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(emailContent, true); // `true` para indicar que el contenido es HTML
            helper.setFrom("tu_email@gmail.com");

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo: " + e.getMessage());
        }
    }
}

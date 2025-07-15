package com.cramirez.backendcramirez.emailnotificacion.application.service;

import com.cramirez.backendcramirez.cliente.domain.entity.Cliente;
import com.cramirez.backendcramirez.cliente.infrastructure.repository.ClienteRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailNotificacionService {

    private final JavaMailSender mailSender;
    private final ClienteRepository clienteRepository;

    public EmailNotificacionService(JavaMailSender mailSender, ClienteRepository clienteRepository) {
        this.mailSender = mailSender;
        this.clienteRepository = clienteRepository;
    }

    public void sendEmail(Integer idCliente) {

        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + idCliente));

        String to = cliente.getCorreoElectronico();
        String nombre = cliente.getNombresApellidos();

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

            String subject = "REMITIMOS PROYECTO DE CONTRATO FINAL DE ENTREGA DE ACCIONES Y DERECHOS";

            String emailContent = String.format("""
                    <p>Estimado(a) %s,</p>

                    <p>Reciba un cordial saludo de parte de <strong>Vivienda Para Todos</strong>. Por medio de la presente, procedemos a remitirle el proyecto de su contrato final de entrega de acciones y derechos.</p>

                    <p>Adjunto encontrará el documento legal mediante el cual se formaliza su contrato preparatorio y se realiza la entrega definitiva del lote adquirido con nuestra empresa.</p>

                    <p>Por ello, es de suma importancia que lo revise detenidamente y proceda a su firma el día de su cita con el Área de Vivienda Para Todos, en nuestra sede principal:</p>

                    <p>📍 <strong>Av. Circunvalación del Golf Los Incas N° 134, Torre B, Piso 19, distrito de Santiago de Surco.</strong></p>

                    <p>Sin otro particular, y esperando que tenga un excelente día, nos despedimos cordialmente.</p>

                    <br>
                    <p>Atentamente,<br>
                    Vivienda Para Todos<br>
                    Aybar Corp</p>
                    """, nombre);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(emailContent, true); // Contenido HTML
            helper.setFrom("tu_email@gmail.com"); // Reemplaza con tu correo real

            System.out.println("Enviando correo a: " + to);
            System.out.println("Contenido:" + emailContent);


            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo: " + e.getMessage());
        }
    }
}

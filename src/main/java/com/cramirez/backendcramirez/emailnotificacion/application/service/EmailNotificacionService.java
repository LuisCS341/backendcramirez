package com.cramirez.backendcramirez.emailnotificacion.application.service;

import com.cramirez.backendcramirez.cliente.domain.entity.Cliente;
import com.cramirez.backendcramirez.cliente.infrastructure.repository.ClienteRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

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

            String subject = "VALIDACION DE DATOS_CONTRATO DEFINITIVO - GRUPO AYBAR";

            String emailContent = String.format("""
    <p>Estimado(a) %s,</p>

    <p>Reciba un cordial saludo de parte de <strong>Vivienda Para Todos</strong>.</p>

    <p>Por medio del presente se le adjunta a este correo, el proyecto de contrato final de transferencia de posesión, documento que formaliza su contrato preparatorio y confirma la entrega definitiva del lote adquirido con nosotros.</p>

    <p><strong>Se requiere:</strong> Revise el documento detenidamente para garantizar que toda la información sea correcta.</p>

    <ul>
        <li>Proyecto:</li>
        <li>Mz – Lt:</li>
        <li>Nombre, DNI, estado civil y dirección del Cliente:</li>
        <li>Nombre, DNI, estado civil y dirección del Copropietario (de ser el caso):</li>
        <li>Nombre, DNI y ¿matrimonio por bienes separados? y dirección del Cónyuge (de ser el caso):</li>
        <li>Su predio lo tiene pagado al 100%% o tiene algún pendiente de pago:</li>
        <li>Cuenta con constancia de No adeudo:</li>
        <li>Cuenta con Certificado de Lote:</li>
        <li>Cuenta con planos de su lote:</li>
        <li>Cuenta con sus medios de pago:</li>
        <li>Cuenta con algún acto adicional como Adenda, acta o acuerdo adicional con la empresa:</li>
        <li>Requiere copia legalizada de su contrato:</li>
        <li>Requiere el servicio de Habilitación Urbana (solo en los casos de Huaral):</li>
        <li>Nos podría adjuntar la información solicitada (en PDF, imagen o escaneado):</li>
        <li>Requerimientos adicionales en los que le podemos ayudar:</li>
    </ul>

    <p>Sin otro particular, y esperando que tenga un excelente día, nos despedimos cordialmente.</p>

    <br>
    <p>Atentamente,<br>
    Vivienda Para Todos<br>
    Aybar Corp</p>
""", nombre);

            // Destinatario principal
            helper.setTo(to);

            // Correos en copia
            helper.setCc(new String[]{
                    "cramirezsac2024@gmail.com",
                    "VIVIENDAPARATODOS@aybarsac.com"
            });

            helper.setSubject(subject);
            helper.setText(emailContent, true); // Contenido HTML
            helper.setFrom("tu_email@gmail.com", "Vivienda Para Todos"); // Con nombre visible

            System.out.println("Enviando correo a: " + to);
            System.out.println("Con copia a: cramirezsac2024@gmail.com, VIVIENDAPARATODOS@aybarsac.com");
            System.out.println("Contenido:" + emailContent);

            mailSender.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException("Error al enviar el correo: " + e.getMessage());
        }
    }
}

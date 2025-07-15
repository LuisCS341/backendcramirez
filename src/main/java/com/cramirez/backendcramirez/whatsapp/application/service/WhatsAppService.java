package com.cramirez.backendcramirez.whatsapp.application.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WhatsAppService {

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.whatsappFrom}")
    private String whatsappFrom;

    public void enviarMensajeWhatsApp(String celularDestino, String mensaje) {
        Twilio.init(accountSid, authToken);


        String destino = "whatsapp:" + celularDestino;

        Message message = Message.creator(
                new PhoneNumber(destino),
                new PhoneNumber(whatsappFrom),
                mensaje
        ).create();

        System.out.println("Mensaje enviado con SID: " + message.getSid());
    }
}

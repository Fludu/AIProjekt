package com.example.aiprojekt.service;

import com.example.aiprojekt.Exception.FileNoExistException;
import com.example.aiprojekt.models.CarAssistance;
import com.example.aiprojekt.models.Client;
import com.example.aiprojekt.models.Reservation;
import com.example.aiprojekt.properties.EmailAddAppointmentProperties;
import com.example.aiprojekt.utils.filestorage.FileStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

@Service
@EnableConfigurationProperties(EmailAddAppointmentProperties.class)
@RequiredArgsConstructor
@Slf4j
public class EmailAddAppointmentToUserService {

    private final EmailAddAppointmentProperties emailAddAppointmentProperties;
    private final JavaMailSender javaMailSender;
    private final FileStorage fileStorage;

    @Async
    public void sendConfirmationEmail(EmailAddAppointmentRequest emailAddAppointmentRequest) {
        javaMailSender.send((mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setTo(emailAddAppointmentRequest.client.getEmail());
            message.setSubject(emailAddAppointmentProperties.getSubject());
            log.info("Sending confirmation email add appointment to {} with subject {}", emailAddAppointmentRequest.client.getEmail(), emailAddAppointmentProperties.getSubject());
            message.setText(loadTextMessage(emailAddAppointmentRequest), true);
            message.addInline("image", loadImage());
        }
        ));
    }

    private ByteArrayDataSource loadImage() throws IOException {
        return new ByteArrayDataSource(fileStorage.getFileBytes("AIProjekt/src/main/resources/files/email/email-add-appointment.png"), fileStorage.getFileType("AIProjekt/src/main/resources/files/email/email-add-appointment.png"));
    }

    private String loadTextMessage(EmailAddAppointmentRequest emailAddAppointmentRequest) {
        return getMessageFromFile(emailAddAppointmentRequest.client().getName(),
                emailAddAppointmentRequest.carAssistance().getName(), emailAddAppointmentRequest.appointment().getLocalDateTime().toLocalDate(),
                emailAddAppointmentRequest.appointment().getLocalDateTime().toLocalTime(), emailAddAppointmentRequest.carAssistance().getPrice());
    }

    private String getMessageFromFile(
            String userName,
            String mechanicalService,
            LocalDate appointmentDate,
            LocalTime appointmentTime,
            Double price) {
        StringBuilder stringBuilder = new StringBuilder();
        try (Scanner scanner = new Scanner(getFile())) {
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine()).append("\n");
            }
        }
        String message = stringBuilder.toString();

        return String.format(message, userName, mechanicalService, appointmentDate, appointmentTime, price);
    }

    private InputStream getFile() {
        InputStream resource = getClass().getResourceAsStream(emailAddAppointmentProperties.getPathToMessage());
        log.info("Getting email template file from  properties: {}", emailAddAppointmentProperties.getPathToMessage());
        if (resource == null)
            throw new FileNoExistException();
        return resource;
    }

    record EmailAddAppointmentRequest(Reservation appointment,
                                      Client client,
                                      CarAssistance carAssistance) {

        static EmailAddAppointmentRequest of(Reservation reservation, Client client, CarAssistance carAssistance) {
            return new EmailAddAppointmentRequest(
                    reservation,
                    client,
                    carAssistance
            );
        }
    }
}

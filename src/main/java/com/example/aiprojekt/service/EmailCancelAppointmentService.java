package com.example.aiprojekt.service;

import com.example.aiprojekt.Exception.FileNoExistException;
import com.example.aiprojekt.models.CarAssistance;
import com.example.aiprojekt.models.Client;
import com.example.aiprojekt.models.Reservation;
import com.example.aiprojekt.properties.EmailCancelAppointmentProperties;
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
@EnableConfigurationProperties({EmailCancelAppointmentProperties.class})
@RequiredArgsConstructor
@Slf4j
public class EmailCancelAppointmentService {
    private final EmailCancelAppointmentProperties emailCancelAppointmentProperties;
    private final JavaMailSender javaMailSender;
    private final FileStorage fileStorage;

    @Async
    public void sendConfirmationEmail(EmailCancelAppointmentRequest emailCancelAppointmentRequest) {
        javaMailSender.send((mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setTo(emailCancelAppointmentRequest.user.getEmail());
            message.setSubject(emailCancelAppointmentProperties.getSubject());
            log.info("Sending confirmation email cancel appointment to {} with subject {}", emailCancelAppointmentRequest.user().getEmail(), emailCancelAppointmentProperties.getSubject());
            message.setText(loadTextMessage(emailCancelAppointmentRequest), true);
            message.addInline("image", loadImage());
        }));
    }

    private ByteArrayDataSource loadImage() throws IOException {
        return new ByteArrayDataSource(fileStorage.getFileBytes("AIProjekt/src/main/resources/files/email/email-cancelled.png"), fileStorage.getFileType("AIProjekt/src/main/resources/files/email/email-cancelled.png"));
    }

    private String loadTextMessage(EmailCancelAppointmentRequest emailCancelAppointmentRequest) {
        return getMessageFromFile(emailCancelAppointmentRequest.user().getName(), emailCancelAppointmentRequest.mechanicalService().getName(), emailCancelAppointmentRequest.appointment().getLocalDateTime().toLocalDate(), emailCancelAppointmentRequest.appointment().getLocalDateTime().toLocalTime());
    }


    private String getMessageFromFile(
            String userName,
            String mechanicalService,
            LocalDate appointmentDate,
            LocalTime appointmentTime) {
        StringBuilder stringBuilder = new StringBuilder();
        try (Scanner scanner = new Scanner(getFile())) {
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine()).append("\n");
            }
        }
        String message = stringBuilder.toString();

        return String.format(message, userName, mechanicalService, appointmentDate, appointmentTime);
    }


    private InputStream getFile() {
        InputStream resource = getClass().getResourceAsStream(emailCancelAppointmentProperties.getPathToMessage());
        log.info("Getting email template file from  properties: {}", emailCancelAppointmentProperties.getPathToMessage());
        if (resource == null)
            throw new FileNoExistException();
        return resource;
    }

    record EmailCancelAppointmentRequest(Reservation appointment,
                                         Client user,
                                         CarAssistance mechanicalService) {

        static EmailCancelAppointmentRequest of(Reservation appointment, Client user, CarAssistance mechanicalService) {
            return new EmailCancelAppointmentRequest(
                    appointment,
                    user,
                    mechanicalService
            );
        }
    }
}

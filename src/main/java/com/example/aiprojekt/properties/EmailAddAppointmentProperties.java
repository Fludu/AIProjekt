package com.example.aiprojekt.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mail.add-appointment")
@Data
public class EmailAddAppointmentProperties {
    private String subject;
    private String pathToMessage;
}

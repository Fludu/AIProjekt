package com.example.aiprojekt.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mail.cancel-appointment")
@Data
public class EmailCancelAppointmentProperties {

    private String subject;
    private String pathToMessage;
}

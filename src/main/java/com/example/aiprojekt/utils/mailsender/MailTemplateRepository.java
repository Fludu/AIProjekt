package com.example.aiprojekt.utils.mailsender;

import com.example.aiprojekt.exception.FileNoExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.exceptions.TemplateInputException;

@Service
@RequiredArgsConstructor
class MailTemplateRepository {

    private final TemplateEngine templateEngine;

    public String findTemplateMessageAndFillContext(String pathToMessage, Context context) {
        try {
            return templateEngine.process(pathToMessage, context);
        } catch (TemplateInputException e) {
            throw new FileNoExistException();
        }
    }

    public String findMessage(String pathToMessage) {
        return findTemplateMessageAndFillContext(pathToMessage, new Context());
    }
}


package com.tbd.user_service.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Translator {

    private final MessageSource messageSource;

    public Translator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String translate(String messageKey) {
        return messageSource.getMessage(
                messageKey,
                null,
                LocaleContextHolder.getLocale()
        );
    }

    public String translate(String messageKey, Object... args) {
        return messageSource.getMessage(
                messageKey,
                args,
                LocaleContextHolder.getLocale()
        );
    }
}

package com.nihanim.rest.webservices.restfulwebservices.custom;

import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Component
public class CustomMessageSource {

    private final MessageSource messageSource;
    private MessageSourceAccessor accessor;

    public CustomMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource, new Locale("TR"));
    }
    public String getMessage(String code) { return accessor.getMessage(code); }

    public String getMessage(String code, Object[] args) {return accessor.getMessage(code, args);}
}

package com.e.hika.config;

import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;

@Component("messageSource")
public class DatabaseMessageSource extends AbstractMessageSource {


    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String lang = locale.toLanguageTag();
        String msg = null;


        msg = getMessageFromCache(lang, code);
        if (msg == null && lang.contains("-")) {
            msg = getMessageFromCache(lang.split("-")[0], code);
        }

        if(msg == null) {
            msg="???"+code+"???";
        }

        return new MessageFormat(msg, locale);
    }


    private String getMessageFromCache(String lang, String code) {
        Map<String, String> map = I18nMessageLoader.i18nCache.get(lang);
        if (map != null) {
            return map.get(code);
        }
        return null;
    }
}

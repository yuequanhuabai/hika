package com.e.hika.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("i18n_message")

public class I18nMessage {

    private String id;
    private String locale;
    private String code;

    private String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "I18nMessage{" +
                "id='" + id + '\'' +
                ", locale='" + locale + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}



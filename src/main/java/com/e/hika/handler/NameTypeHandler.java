package com.e.hika.handler;


import com.e.hika.i18npojo.Name;

public class NameTypeHandler extends JacksonTypeHandler<Name> {


    public NameTypeHandler() {
        super(Name.class);
    }
}

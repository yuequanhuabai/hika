package com.e.hika.ex.en;

import org.apache.ibatis.annotations.*;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandles;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestA {
    private static final int ALLOWED_MODES = MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED
            | MethodHandles.Lookup.PACKAGE | MethodHandles.Lookup.PUBLIC;

    private static final Set<Class<? extends Annotation>> statementAnnotationTypes = Stream
            .of(Select.class, Update.class, Insert.class, Delete.class, SelectProvider.class, UpdateProvider.class,
                    InsertProvider.class, DeleteProvider.class)
            .collect(Collectors.toSet());

    @Test
    public void test1() {
        System.out.println("===================");
//        System.out.println(ALLOWED_MODES);


        System.out.println("statementAnnotationTypes:" + statementAnnotationTypes);

    }
}

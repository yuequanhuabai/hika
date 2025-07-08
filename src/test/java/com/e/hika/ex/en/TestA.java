package com.e.hika.ex.en;

import org.junit.jupiter.api.Test;

import java.lang.invoke.MethodHandles;

public class TestA {
    private static final int ALLOWED_MODES = MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED
            | MethodHandles.Lookup.PACKAGE | MethodHandles.Lookup.PUBLIC;

    @Test
    public void test1() {
        System.out.println("===================");
        System.out.println(ALLOWED_MODES);

    }
}

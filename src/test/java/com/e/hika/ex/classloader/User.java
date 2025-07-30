package com.e.hika.ex.classloader;

public class User {
    public String hello(){
        return "hello "+this.getClass().getClassLoader();
    }
}

package com.e.hika.ex;

public class SmsSender implements Sender{

    @Override
    public void send(String msg) {
        System.out.println("SmsSender send msg: " + msg);
    }


}

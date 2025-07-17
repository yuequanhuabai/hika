package com.e.hika.ex;

public class Notifier {
    private Sender sender;

    public Notifier(Sender sender) {
        this.sender = sender;
    }

    public static void main(String[] args) {
        SmsSender smsSender = new SmsSender();
        System.out.println("=========================================");
        new Notifier(smsSender).notify("hello world!");
    }

    public void notify(String msg) {
        sender.send(msg);
    }
}

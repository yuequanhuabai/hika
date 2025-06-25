package com.e.hika.ex;

public interface Sender {

    void send (String msg);

   default boolean healthCheck(){
        return true;
    }
}



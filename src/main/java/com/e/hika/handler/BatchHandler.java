package com.e.hika.handler;


import java.util.List;

@FunctionalInterface
public interface BatchHandler<T> {
    void handle(List<T> batch);
}

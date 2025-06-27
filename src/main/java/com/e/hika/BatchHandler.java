package com.e.hika;


import java.util.List;

@FunctionalInterface
public interface BatchHandler<T> {
    void handle(List<T> batch);
}

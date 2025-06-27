package com.e.hika.config;

import com.e.hika.handler.BatchHandler;
import com.e.hika.listener.GenericBatchListener;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

@Component
public class ExcelListenerFactory {

    private final ApplicationContext ctx;

    public ExcelListenerFactory(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    public <T> GenericBatchListener<T> createBatchListener(Class<T> clazz, int batchSize) {

        // 1. 先嘗試從 Spring 找對應的 BatchHandler<T>
//        BatchHandler<T> handler = (BatchHandler<T>) ctx.getBeanProvider(ResolvableType.forClassWithGenerics(
//                BatchHandler.class, clazz)).getObject();
//        BatchHandler<T> handler = (BatchHandler<T>)ctx.getBeanProvider(ResolvableType.forClassWithGenerics(
//                BatchHandler.class, clazz)).getObject();

        BatchHandler<T> handler = (BatchHandler<T>) ctx.getBeanProvider(ResolvableType.forClassWithGenerics(
                BatchHandler.class, clazz)).getIfAvailable();

        if (null == handler) {
            throw new IllegalStateException("No BatchHandler found for " + clazz.getSimpleName());
        }
        return new GenericBatchListener<>(batchSize, handler);
    }

}

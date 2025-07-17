package com.e.hika.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.e.hika.handler.BatchHandler;

import java.util.ArrayList;
import java.util.List;


public class GenericBatchListener<T> extends AnalysisEventListener<T> {

    private static final int defaultBatchSize = 5000;
    private final int batchSize;
    private final List<T> cache;
    private final BatchHandler<T> batchHandler;

    public GenericBatchListener(int batchSize, BatchHandler batchHandler) {
        this.batchSize = batchSize;
        this.cache = new ArrayList<>(batchSize);
        this.batchHandler = batchHandler;
    }

    public GenericBatchListener(BatchHandler batchHandler) {
        this(defaultBatchSize, batchHandler);
    }


    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        cache.add(t);
        if (cache.size() >= batchSize) {
            flush();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        flush();
    }


    private void flush() {
        if (!cache.isEmpty()) {
            batchHandler.handle(cache);
            cache.clear();
        }
    }
}

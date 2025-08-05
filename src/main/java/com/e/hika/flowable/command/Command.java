package com.e.hika.flowable.command;

@FunctionalInterface
public interface Command<T> {
    T execute(CommandContext commandContext);
}

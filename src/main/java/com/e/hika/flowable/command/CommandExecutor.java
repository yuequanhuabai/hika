package com.e.hika.flowable.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CommandExecutor {

    @Autowired
    private ApplicationContext applicationContext;

    @Transactional
    public <T> T execute(Command<T> command) {
        CommandContext commandContext = new CommandContext(applicationContext);
        return command.execute(commandContext);
    }
}

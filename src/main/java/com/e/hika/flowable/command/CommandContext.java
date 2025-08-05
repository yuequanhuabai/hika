package com.e.hika.flowable.command;

import com.e.hika.flowable.manager.SchoolEntityManager;
import org.springframework.context.ApplicationContext;

public class CommandContext {

    private final SchoolEntityManager schoolEntityManager;

    public CommandContext(ApplicationContext applicationContext) {
        this.schoolEntityManager = applicationContext.getBean(SchoolEntityManager.class);
    }

    public SchoolEntityManager getSchoolEntityManager() {
        return schoolEntityManager;
    }
}

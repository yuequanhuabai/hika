package com.e.hika.flowable.command;

import com.e.hika.flowable.manager.SchoolEntityManager;
import com.e.hika.pojo.School;

import java.util.List;

public class SchoolCommands {

    public static class CreateSchoolCmd implements Command<School> {
        private final School school;

        public CreateSchoolCmd(School school) {
            this.school = school;
        }

        @Override
        public School execute(CommandContext commandContext) {
            SchoolEntityManager schoolEntityManager = commandContext.getSchoolEntityManager();
            schoolEntityManager.insert(school);
            return school;
        }
    }

    public static class GetSchoolCmd implements Command<School> {
        private final String id;

        public GetSchoolCmd(String id) {
            this.id = id;
        }

        @Override
        public School execute(CommandContext commandContext) {
            return commandContext.getSchoolEntityManager().findById(id);
        }
    }
    
    public static class GetAllSchoolsCmd implements Command<List<School>> {
        @Override
        public List<School> execute(CommandContext commandContext) {
            return commandContext.getSchoolEntityManager().findAll();
        }
    }

    public static class UpdateSchoolCmd implements Command<Void> {
        private final School school;

        public UpdateSchoolCmd(School school) {
            this.school = school;
        }

        @Override
        public Void execute(CommandContext commandContext) {
            commandContext.getSchoolEntityManager().update(school);
            return null;
        }
    }

    public static class DeleteSchoolCmd implements Command<Void> {
        private final String id;

        public DeleteSchoolCmd(String id) {
            this.id = id;
        }

        @Override
        public Void execute(CommandContext commandContext) {
            commandContext.getSchoolEntityManager().delete(id);
            return null;
        }
    }
}

package com.e.hika.service;

import com.e.hika.flowable.command.CommandExecutor;
import com.e.hika.flowable.command.SchoolCommands;
import com.e.hika.pojo.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    @Autowired
    private CommandExecutor commandExecutor;

    public School createSchool(School school) {
        return commandExecutor.execute(new SchoolCommands.CreateSchoolCmd(school));
    }

    public School getSchoolById(String id) {
        return commandExecutor.execute(new SchoolCommands.GetSchoolCmd(id));
    }
    
    public List<School> getAllSchools() {
        return commandExecutor.execute(new SchoolCommands.GetAllSchoolsCmd());
    }

    public void updateSchool(School school) {
        commandExecutor.execute(new SchoolCommands.UpdateSchoolCmd(school));
    }

    public void deleteSchool(String id) {
        commandExecutor.execute(new SchoolCommands.DeleteSchoolCmd(id));
    }
}

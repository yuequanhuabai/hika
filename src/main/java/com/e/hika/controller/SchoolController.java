package com.e.hika.controller;

import com.e.hika.pojo.School;
import com.e.hika.service.SchoolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "School Management", description = "APIs for managing schools")
@RestController
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @Operation(summary = "Create a new school", description = "Creates a new school record in the database.")
    @PostMapping
    public School createSchool(@RequestBody School school) {
        return schoolService.createSchool(school);
    }

    @Operation(summary = "Get a school by its ID", description = "Retrieves a single school record based on its UUID.")
    @GetMapping("/{id}")
    public School getSchoolById(@Parameter(description = "The UUID of the school", required = true) @PathVariable String id) {
        return schoolService.getSchoolById(id);
    }
    
    @Operation(summary = "Get all schools", description = "Retrieves a list of all school records.")
    @GetMapping
    public List<School> getAllSchools() {
        return schoolService.getAllSchools();
    }

    @Operation(summary = "Update an existing school", description = "Updates the details of an existing school by its UUID.")
    @PutMapping("/{id}")
    public void updateSchool(
            @Parameter(description = "The UUID of the school to update", required = true) @PathVariable String id, 
            @RequestBody School school) {
        school.setId(id);
        schoolService.updateSchool(school);
    }

    @Operation(summary = "Delete a school by its ID", description = "Deletes a school record from the database by its UUID.")
    @DeleteMapping("/{id}")
    public void deleteSchool(@Parameter(description = "The UUID of the school to delete", required = true) @PathVariable String id) {
        schoolService.deleteSchool(id);
    }
}

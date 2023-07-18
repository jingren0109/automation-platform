package com.project.automationplatform.controllers;

import com.project.automationplatform.models.Project;
import com.project.automationplatform.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project createProject(@RequestBody Project project) throws Exception {
        return projectService.createProject(project);
    }

    @PutMapping("/{projectId}")
    public Project updateProject(@PathVariable Long projectId, @RequestBody Project project) {
        project.setId(projectId);
        return projectService.updateProject(project);
    }

    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
    }

    @GetMapping("/{projectId}")
    public Optional<Project> getProjectById(@PathVariable Long projectId) {
        return projectService.getProjectById(projectId);
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }
}

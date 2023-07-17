package com.project.automationplatform.services;

import com.project.automationplatform.models.Project;
import java.util.List;
import java.util.Optional;

public interface ProjectService {

    Project createProject(Project project) throws Exception;

    Project updateProject(Project project);

    void deleteProject(Long projectId);

    Optional<Project> getProjectById(Long projectId);

    List<Project> getAllProjects();

}


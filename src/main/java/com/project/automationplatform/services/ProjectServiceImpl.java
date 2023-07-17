package com.project.automationplatform.services;

import com.project.automationplatform.exception.exceptions.MissingFieldValueException;
import com.project.automationplatform.exception.exceptions.ResourceAlreadyExistsException;
import com.project.automationplatform.exception.exceptions.ResourceNotFoundException;
import com.project.automationplatform.models.Project;
import com.project.automationplatform.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project createProject(Project project) {
        if (project.getName() == null || project.getName().isEmpty()) {
            throw new MissingFieldValueException("Project name cannot be blank.");
        }
        if (projectRepository.existsByName(project.getName())) {
            throw new ResourceAlreadyExistsException("Project already exists with the given name.");
        }
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Project project) {
        if (project.getName() == null || project.getName().isEmpty()) {
            throw new MissingFieldValueException("Project name cannot be blank.");
        }
        if (projectRepository.existsByName(project.getName())) {
            throw new ResourceAlreadyExistsException("Project already exists with the given name.");
        }
        return projectRepository.save(project);
    }

    @Override
    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    @Override
    public Optional<Project> getProjectById(Long projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isEmpty()) {
            throw new ResourceNotFoundException("Project not found with ID: " + projectId);
        }
        return project;
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}

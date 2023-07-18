package com.project.automationplatform.service;

import com.project.automationplatform.exception.exceptions.MissingFieldValueException;
import com.project.automationplatform.exception.exceptions.ResourceAlreadyExistsException;
import com.project.automationplatform.exception.exceptions.ResourceNotFoundException;
import com.project.automationplatform.models.Project;
import com.project.automationplatform.repositories.ProjectRepository;
import com.project.automationplatform.services.impl.ProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProject() {
        Project project = new Project();
        project.setId(1L);
        project.setName("Sample Project");

        when(projectRepository.existsByName(project.getName())).thenReturn(false);
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        Project createdProject = projectService.createProject(project);

        assertNotNull(createdProject);
        assertEquals(1L, createdProject.getId());
        assertEquals("Sample Project", createdProject.getName());

        verify(projectRepository, times(1)).existsByName(project.getName());
        verify(projectRepository, times(1)).save(any(Project.class));
        verifyNoMoreInteractions(projectRepository);
    }

    @Test
    void testCreateProject_ThrowsMissingFieldValueException() {
        Project project = new Project();
        project.setId(1L);
        project.setName("");

        assertThrows(MissingFieldValueException.class, () -> projectService.createProject(project));

        verifyNoInteractions(projectRepository);
    }

    @Test
    void testCreateProject_ThrowsResourceAlreadyExistsException() {
        Project project = new Project();
        project.setId(1L);
        project.setName("Sample Project");

        when(projectRepository.existsByName(project.getName())).thenReturn(true);

        assertThrows(ResourceAlreadyExistsException.class, () -> projectService.createProject(project));

        verify(projectRepository, times(1)).existsByName(project.getName());
        verifyNoMoreInteractions(projectRepository);
    }

    @Test
    void testUpdateProject() {
        Project project = new Project();
        project.setId(1L);
        project.setName("Updated Project");

        when(projectRepository.existsByName(project.getName())).thenReturn(false);
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        Project updatedProject = projectService.updateProject(project);

        assertNotNull(updatedProject);
        assertEquals(1L, updatedProject.getId());
        assertEquals("Updated Project", updatedProject.getName());

        verify(projectRepository, times(1)).existsByName(project.getName());
        verify(projectRepository, times(1)).save(any(Project.class));
        verifyNoMoreInteractions(projectRepository);
    }

    @Test
    void testUpdateProject_ThrowsMissingFieldValueException() {
        Project project = new Project();
        project.setId(1L);
        project.setName("");

        assertThrows(MissingFieldValueException.class, () -> projectService.updateProject(project));

        verifyNoInteractions(projectRepository);
    }

    @Test
    void testUpdateProject_ThrowsResourceAlreadyExistsException() {
        Project project = new Project();
        project.setId(1L);
        project.setName("Updated Project");

        when(projectRepository.existsByName(project.getName())).thenReturn(true);

        assertThrows(ResourceAlreadyExistsException.class, () -> projectService.updateProject(project));

        verify(projectRepository, times(1)).existsByName(project.getName());
        verifyNoMoreInteractions(projectRepository);
    }

    @Test
    void testDeleteProject() {
        Long projectId = 1L;

        projectService.deleteProject(projectId);

        verify(projectRepository, times(1)).deleteById(projectId);
        verifyNoMoreInteractions(projectRepository);
    }

    @Test
    void testGetProjectById() {
        Long projectId = 1L;
        Project project = new Project();
        project.setId(projectId);
        project.setName("Sample Project");

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

        Optional<Project> foundProject = projectService.getProjectById(projectId);

        assertTrue(foundProject.isPresent());
        assertEquals(projectId, foundProject.get().getId());
        assertEquals("Sample Project", foundProject.get().getName());

        verify(projectRepository, times(1)).findById(projectId);
        verifyNoMoreInteractions(projectRepository);
    }

    @Test
    void testGetProjectById_ThrowsResourceNotFoundException() {
        Long projectId = 1L;

        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> projectService.getProjectById(projectId));

        verify(projectRepository, times(1)).findById(projectId);
        verifyNoMoreInteractions(projectRepository);
    }

    @Test
    void testGetAllProjects() {
        Project project1 = new Project();
        project1.setId(1L);
        project1.setName("Project 1");

        Project project2 = new Project();
        project2.setId(2L);
        project2.setName("Project 2");

        List<Project> projects = new ArrayList<>();
        projects.add(project1);
        projects.add(project2);

        when(projectRepository.findAll()).thenReturn(projects);

        List<Project> allProjects = projectService.getAllProjects();

        assertEquals(2, allProjects.size());
        assertEquals(projects, allProjects);

        verify(projectRepository, times(1)).findAll();
        verifyNoMoreInteractions(projectRepository);
    }
}

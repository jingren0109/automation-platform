package com.project.automationplatform.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.automationplatform.controllers.ProjectController;
import com.project.automationplatform.models.Project;
import com.project.automationplatform.services.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    private static final String BASE_URL = "/projects";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateProject() throws Exception {
        Project project = new Project();
        project.setId(1L);
        project.setName("Sample Project");

        when(projectService.createProject(any(Project.class))).thenReturn(project);

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Sample Project")));

        verify(projectService, times(1)).createProject(any(Project.class));
        verifyNoMoreInteractions(projectService);
    }

    @Test
    void testUpdateProject() throws Exception {
        Project project = new Project();
        project.setId(1L);
        project.setName("Updated Project");

        when(projectService.updateProject(any(Project.class))).thenReturn(project);

        mockMvc.perform(put(BASE_URL + "/{projectId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Updated Project")));

        verify(projectService, times(1)).updateProject(any(Project.class));
        verifyNoMoreInteractions(projectService);
    }

    @Test
    void testDeleteProject() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/{projectId}", 1L))
                .andExpect(status().isOk());

        verify(projectService, times(1)).deleteProject(1L);
        verifyNoMoreInteractions(projectService);
    }

    @Test
    void testGetProjectById() throws Exception {
        Project project = new Project();
        project.setId(1L);
        project.setName("Sample Project");

        when(projectService.getProjectById(1L)).thenReturn(Optional.of(project));

        mockMvc.perform(get(BASE_URL + "/{projectId}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Sample Project")));

        verify(projectService, times(1)).getProjectById(1L);
        verifyNoMoreInteractions(projectService);
    }

    @Test
    void testGetAllProjects() throws Exception {
        Project project1 = new Project();
        project1.setId(1L);
        project1.setName("Project 1");

        Project project2 = new Project();
        project2.setId(2L);
        project2.setName("Project 2");

        List<Project> projects = Arrays.asList(project1, project2);

        when(projectService.getAllProjects()).thenReturn(projects);

        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Project 1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Project 2")));

        verify(projectService, times(1)).getAllProjects();
        verifyNoMoreInteractions(projectService);
    }
}

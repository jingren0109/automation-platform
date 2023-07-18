package com.project.automationplatform.services.impl;

import com.project.automationplatform.exception.exceptions.MissingFieldValueException;
import com.project.automationplatform.exception.exceptions.ResourceNotFoundException;
import com.project.automationplatform.models.Project;
import com.project.automationplatform.models.TestSuite;
import com.project.automationplatform.repositories.ProjectRepository;
import com.project.automationplatform.repositories.TestSuiteRepository;
import com.project.automationplatform.services.TestSuiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestSuiteServiceImpl implements TestSuiteService {

    private final TestSuiteRepository testSuiteRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public TestSuiteServiceImpl(TestSuiteRepository testSuiteRepository, ProjectRepository projectRepository) {
        this.testSuiteRepository = testSuiteRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public TestSuite createTestSuite(Long projectId, TestSuite testSuite) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + projectId));
        testSuite.setProject(project);
        if (testSuite.getName() == null || testSuite.getName().isEmpty()) {
            throw new MissingFieldValueException("Test Suite name cannot be blank.");
        }
        return testSuiteRepository.save(testSuite);
    }

    @Override
    public TestSuite updateTestSuite(Long projectId, Long testSuiteId, TestSuite testSuite) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + projectId));
        testSuiteRepository.findById(testSuiteId)
                .orElseThrow(() -> new ResourceNotFoundException("Test Suite not found with ID: " + testSuiteId));;
        testSuite.setProject(project);
        testSuite.setId(testSuiteId);
        if (testSuite.getName() == null || testSuite.getName().isEmpty()) {
            throw new MissingFieldValueException("Test Suite name cannot be blank.");
        }
        return testSuiteRepository.save(testSuite);
    }

    @Override
    public void deleteTestSuite(Long projectId, Long testSuiteId) {
        testSuiteRepository.deleteById(testSuiteId);
    }

    @Override
    public Optional<TestSuite> getTestSuiteById(Long testSuiteId) {
        Optional<TestSuite> testSuite = testSuiteRepository.findById(testSuiteId);
        if (testSuite.isEmpty()) {
            throw new ResourceNotFoundException("Test Suite not found with ID: " + testSuiteId);
        }
        return testSuite;
    }

    @Override
    public List<TestSuite> getAllTestSuites() {
        return testSuiteRepository.findAll();
    }
}

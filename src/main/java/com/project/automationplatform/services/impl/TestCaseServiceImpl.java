package com.project.automationplatform.services.impl;

import com.project.automationplatform.exception.exceptions.MissingFieldValueException;
import com.project.automationplatform.exception.exceptions.ResourceNotFoundException;
import com.project.automationplatform.models.Project;
import com.project.automationplatform.models.TestCase;
import com.project.automationplatform.models.TestSuite;
import com.project.automationplatform.repositories.TestCaseRepository;
import com.project.automationplatform.repositories.TestSuiteRepository;
import com.project.automationplatform.services.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestCaseServiceImpl implements TestCaseService {

    private final TestCaseRepository testCaseRepository;
    private final TestSuiteRepository testSuiteRepository;

    @Autowired
    public TestCaseServiceImpl(TestCaseRepository testCaseRepository, TestSuiteRepository testSuiteRepository) {
        this.testCaseRepository = testCaseRepository;
        this.testSuiteRepository = testSuiteRepository;
    }

    @Override
    public TestCase createTestCase(Long testSuiteId, TestCase testCase) {
        TestSuite testSuite = testSuiteRepository.findById(testSuiteId)
                .orElseThrow(() -> new ResourceNotFoundException("Test Suite not found with ID: " + testSuiteId));
        testCase.setTestSuite(testSuite);
        if (testCase.getName() == null || testCase.getName().isEmpty()) {
            throw new MissingFieldValueException("Test Case name cannot be blank.");
        }
        return testCaseRepository.save(testCase);
    }

    @Override
    public TestCase updateTestCase(Long testSuiteId, Long testCaseId, TestCase testCase) {
        TestSuite testSuite = testSuiteRepository.findById(testSuiteId)
                .orElseThrow(() -> new ResourceNotFoundException("Test Suite not found with ID: " + testSuiteId));
        testCaseRepository.findById(testCaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Test Case not found with ID: " + testCaseId));;
        testCase.setTestSuite(testSuite);
        testCase.setId(testCaseId);
        if (testCase.getName() == null || testCase.getName().isEmpty()) {
            throw new MissingFieldValueException("Test Case name cannot be blank.");
        }
        return testCaseRepository.save(testCase);
    }

    @Override
    public void deleteTestCase(Long testCaseId) {
        testCaseRepository.deleteById(testCaseId);
    }

    @Override
    public Optional<TestCase> getTestCaseById(Long testSuiteId, Long testCaseId) {
        Optional<TestCase> testCase = testCaseRepository.findById(testCaseId);
        if (testCase.isEmpty()) {
            throw new ResourceNotFoundException("Test Case not found with ID: " + testCaseId);
        }
        return testCase;
    }

    @Override
    public List<TestCase> getAllTestCases(Long testSuiteId) {
        return testCaseRepository.findByTestSuiteId(testSuiteId);
    }
}

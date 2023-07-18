package com.project.automationplatform.services.impl;

import com.project.automationplatform.exception.exceptions.MissingFieldValueException;
import com.project.automationplatform.exception.exceptions.ResourceNotFoundException;
import com.project.automationplatform.models.TestStep;
import com.project.automationplatform.models.TestCase;
import com.project.automationplatform.repositories.TestCaseRepository;
import com.project.automationplatform.repositories.TestStepRepository;
import com.project.automationplatform.services.TestStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestStepServiceImpl implements TestStepService {

    private final TestStepRepository testStepRepository;
    private final TestCaseRepository testCaseRepository;

    @Autowired
    public TestStepServiceImpl(TestStepRepository testStepRepository, TestCaseRepository testCaseRepository1) {
        this.testCaseRepository = testCaseRepository1;
        this.testStepRepository = testStepRepository;
    }

    @Override
    public TestStep createTestStep(Long testCaseId, TestStep testStep) {
        TestCase testCase = testCaseRepository.findById(testCaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Test Case not found with ID: " + testCaseId));
        testStep.setTestCase(testCase);
        if (testStep.getDescription() == null || testStep.getDescription().isEmpty()) {
            throw new MissingFieldValueException("Test Step description cannot be blank.");
        }
        updateStepNumbersOnInsert(testCase, testStep.getStepNumber());
        return testStepRepository.save(testStep);
    }

    @Override
    public TestStep updateTestStep(Long testCaseId, Long testStepId, TestStep testStep) {
        TestCase testCase = testCaseRepository.findById(testCaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Test Suite not found with ID: " + testCaseId));
        testStepRepository.findById(testStepId)
                .orElseThrow(() -> new ResourceNotFoundException("Test Case not found with ID: " + testStepId));;
        testStep.setTestCase(testCase);
        testStep.setId(testStepId);
        if (testStep.getDescription() == null || testStep.getDescription().isEmpty()) {
            throw new MissingFieldValueException("Test Step description cannot be blank.");
        }
        updateStepNumbersOnUpdate(testCase, testStep, testStep.getStepNumber());
        return testStepRepository.save(testStep);
    }

    @Override
    public void deleteTestStep(Long testCaseId, Long testStepId) {
        Optional<TestCase> testCase = testCaseRepository.findById(testCaseId);
        if(testCase.isEmpty()){
            throw new ResourceNotFoundException("Test Suite not found with ID: " + testCaseId);
        }
        testStepRepository.deleteById(testCaseId, testStepId);
        updateStepNumbersOnDelete(testCase.get());
    }

    @Override
    public Optional<TestStep> getTestStepById(Long testCaseId, Long testStepId) {
        Optional<TestStep> testStep = testStepRepository.findById(testStepId);
        if (testStep.isEmpty()) {
            throw new ResourceNotFoundException("Test Step not found with ID: " + testStepId);
        }
        return testStep;
    }

    @Override
    public List<TestStep> getAllTestSteps(Long testCaseId) {
        return testStepRepository.findByTestCaseId(testCaseId);
    }

    // Helper method to update step numbers when a step is inserted
    private void updateStepNumbersOnInsert(TestCase testCase, int insertedStepNumber) {
        List<TestStep> testSteps = testCase.getTestSteps();
        for (TestStep step : testSteps) {
            if (step.getStepNumber() >= insertedStepNumber) {
                step.setStepNumber(step.getStepNumber() + 1);
            }
        }
    }

    // Helper method to update step numbers when a step is updated
    private void updateStepNumbersOnUpdate(TestCase testCase, TestStep updatedStep, int originalStepNumber) {
        List<TestStep> testSteps = testCase.getTestSteps();
        for (TestStep step : testSteps) {
            if (step.getId().equals(updatedStep.getId())) {
                // Skip the updated step
                continue;
            }
            if (step.getStepNumber() > originalStepNumber && step.getStepNumber() <= updatedStep.getStepNumber()) {
                step.setStepNumber(step.getStepNumber() - 1);
            } else if (step.getStepNumber() < originalStepNumber && step.getStepNumber() >= updatedStep.getStepNumber()) {
                step.setStepNumber(step.getStepNumber() + 1);
            }
        }
    }

    // Helper method to update step numbers when a step is deleted
    private void updateStepNumbersOnDelete(TestCase testCase) {
        List<TestStep> testSteps = testCase.getTestSteps();
        for (TestStep step : testSteps) {
            if (step.getStepNumber() > testSteps.size()) {
                step.setStepNumber(step.getStepNumber() - 1);
            }
        }
    }
}

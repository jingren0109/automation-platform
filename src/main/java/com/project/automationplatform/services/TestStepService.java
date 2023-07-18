package com.project.automationplatform.services;

import com.project.automationplatform.models.TestStep;

import java.util.List;
import java.util.Optional;

public interface TestStepService {

    TestStep createTestStep(Long testCaseId, TestStep testStep) throws Exception;

    TestStep updateTestStep(Long testCaseId, Long testStepId, TestStep testStep);

    void deleteTestStep(Long testCaseId, Long testStepId);

    Optional<TestStep> getTestStepById(Long testCaseId, Long testStepId);

    List<TestStep> getAllTestSteps(Long testCaseId);
}

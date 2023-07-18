package com.project.automationplatform.services;

import com.project.automationplatform.models.TestCase;

import java.util.List;
import java.util.Optional;

public interface TestCaseService {

    TestCase createTestCase(Long testSuiteId, TestCase testCase) throws Exception;

    TestCase updateTestCase(Long testSuiteId, Long testCaseId, TestCase testCase);

    void deleteTestCase(Long testCaseId);

    Optional<TestCase> getTestCaseById(Long testSuiteId, Long testCaseId);

    List<TestCase> getAllTestCases(Long testSuiteId);

}


package com.project.automationplatform.services;

import com.project.automationplatform.models.TestSuite;

import java.util.List;
import java.util.Optional;

public interface TestSuiteService {

    TestSuite createTestSuite(Long projectId, TestSuite testSuite) throws Exception;

    TestSuite updateTestSuite(Long projectId, Long testSuiteId, TestSuite testSuite);

    void deleteTestSuite(Long projectId, Long testSuiteId);

    Optional<TestSuite> getTestSuiteById(Long testSuiteId);

    List<TestSuite> getAllTestSuites();

}


package com.project.automationplatform.controllers;

import com.project.automationplatform.models.TestCase;
import com.project.automationplatform.services.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/testSuites/{testSuiteId}/testCases")
public class TestCaseController {

    private final TestCaseService testCaseService;

    @Autowired
    public TestCaseController(TestCaseService testCaseService) {
        this.testCaseService = testCaseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TestCase createTestCase(@PathVariable Long testSuiteId, @RequestBody TestCase testCase) throws Exception {
        return testCaseService.createTestCase(testSuiteId, testCase);
    }

    @PutMapping("/{testCaseId}")
    public TestCase updateTestCase(@PathVariable Long testSuiteId, @PathVariable Long testCaseId, @RequestBody TestCase testCase) {
        testCase.setId(testCaseId);
        return testCaseService.updateTestCase(testSuiteId, testSuiteId, testCase);
    }

    @DeleteMapping("/{testCaseId}")
    public void deleteTestCase(@PathVariable Long testSuiteId, @PathVariable Long testCaseId) {
        testCaseService.deleteTestCase(testCaseId);
    }

    @GetMapping("/{testCaseId}")
    public Optional<TestCase> getTestCaseById(@PathVariable Long testSuiteId, @PathVariable Long testCaseId) {
        return testCaseService.getTestCaseById(testSuiteId, testCaseId);
    }

    @GetMapping
    public List<TestCase> getAllTestCases(@PathVariable Long testSuiteId) {
        return testCaseService.getAllTestCases(testSuiteId);
    }
}

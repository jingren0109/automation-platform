package com.project.automationplatform.controllers;

import com.project.automationplatform.models.TestSuite;
import com.project.automationplatform.services.TestSuiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects/{projectId}/testSuites")
public class TestSuiteController {

    private final TestSuiteService testSuiteService;

    @Autowired
    public TestSuiteController(TestSuiteService testSuiteService) {
        this.testSuiteService = testSuiteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TestSuite createTestSuite(@PathVariable Long projectId, @RequestBody TestSuite testSuite) throws Exception {
        return testSuiteService.createTestSuite(projectId, testSuite);
    }

    @PutMapping("/{testSuiteId}")
    public TestSuite updateTestSuite(@PathVariable Long projectId, @PathVariable Long testSuiteId, @RequestBody TestSuite testSuite) {
        testSuite.setId(testSuiteId);
        return testSuiteService.updateTestSuite(projectId, testSuiteId, testSuite);
    }

    @DeleteMapping("/{testSuiteId}")
    public void deleteTestSuite(@PathVariable Long testSuiteId) {
        testSuiteService.deleteTestSuite(testSuiteId, testSuiteId);
    }

    @GetMapping("/{testSuiteId}")
    public Optional<TestSuite> getTestSuiteById(@PathVariable Long testSuiteId) {
        return testSuiteService.getTestSuiteById(testSuiteId);
    }

    @GetMapping
    public List<TestSuite> getAllTestSuites() {
        return testSuiteService.getAllTestSuites();
    }
}

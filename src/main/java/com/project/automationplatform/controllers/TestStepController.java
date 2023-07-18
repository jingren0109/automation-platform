package com.project.automationplatform.controllers;

import com.project.automationplatform.models.TestCase;
import com.project.automationplatform.models.TestStep;
import com.project.automationplatform.services.TestCaseService;
import com.project.automationplatform.services.TestStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/testCases/{testCaseId}/testSteps")
public class TestStepController {

    private final TestStepService testStepService;

    @Autowired
    public TestStepController(TestStepService testStepService) {
        this.testStepService = testStepService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TestStep createTestStep(@PathVariable Long testCaseId, @RequestBody TestStep testStep) throws Exception {
        return testStepService.createTestStep(testCaseId, testStep);
    }

    @PutMapping("/{testStepId}")
    public TestStep updateTestStep(@PathVariable Long testCaseId, @PathVariable Long testStepId, @RequestBody TestStep testStep) {
        testStep.setId(testStepId);
        return testStepService.updateTestStep(testCaseId, testStepId, testStep);
    }

    @DeleteMapping("/{testStepId}")
    public void deleteTestStep(@PathVariable Long testCaseId, @PathVariable Long testStepId) {
        testStepService.deleteTestStep(testCaseId, testStepId);
    }

    @GetMapping("/{testStepId}")
    public Optional<TestStep> getTestStepById(@PathVariable Long testCaseId, @PathVariable Long testStepId) {
        return testStepService.getTestStepById(testCaseId, testStepId);
    }

    @GetMapping
    public List<TestStep> getAllTestSteps(@PathVariable Long testCaseId) {
        return testStepService.getAllTestSteps(testCaseId);
    }
}

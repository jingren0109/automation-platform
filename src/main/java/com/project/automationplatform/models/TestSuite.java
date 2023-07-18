package com.project.automationplatform.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TestSuite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @JsonBackReference
    @OneToMany(mappedBy = "testSuite", cascade = CascadeType.ALL)
    private List<TestCase> testCases;

    public TestSuite() {
    }

    public TestSuite(String name, Project project, List<TestCase> testCases) {
        this.name = name;
        this.project = project;
        this.testCases = testCases;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    public void add(TestCase tempTestCase) {
        if (testCases == null) {
            testCases = new ArrayList<>();
        }
        testCases.add(tempTestCase);
        tempTestCase.setTestSuite(this);
    }
}

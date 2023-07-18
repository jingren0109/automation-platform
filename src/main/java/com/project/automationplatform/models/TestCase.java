package com.project.automationplatform.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class TestCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "test_suite_id", nullable = false)
    private TestSuite testSuite;

    @OneToMany(mappedBy = "testCase", cascade = CascadeType.ALL)
    private List<TestStep> testSteps;

    public TestCase() {
    }

    public TestCase(String name, TestSuite testSuite, List<TestStep> testSteps) {
        this.name = name;
        this.testSuite = testSuite;
        this.testSteps = testSteps;
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

    public TestSuite getTestSuite() {
        return testSuite;
    }

    public void setTestSuite(TestSuite testSuite) {
        this.testSuite = testSuite;
    }

    public List<TestStep> getTestSteps() {
        return testSteps;
    }

    public void setTestSteps(List<TestStep> testSteps) {
        this.testSteps = testSteps;
    }
}
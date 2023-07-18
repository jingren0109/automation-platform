package com.project.automationplatform.repositories;

import com.project.automationplatform.models.TestSuite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestSuiteRepository extends JpaRepository<TestSuite, Long> {

}


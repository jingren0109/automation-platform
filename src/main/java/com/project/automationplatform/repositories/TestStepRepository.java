package com.project.automationplatform.repositories;

import com.project.automationplatform.models.TestStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestStepRepository extends JpaRepository<TestStep, Long> {

    @Modifying
    @Query("DELETE FROM TestStep t WHERE t.testCase.id = :testCaseId AND t.id = :testStepId")
    void deleteById(@Param("testCaseId") Long testCaseId, @Param("testStepId") Long testStepId);

    List<TestStep> findByTestCaseId(Long testCaseId);
}


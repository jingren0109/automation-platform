-- Create test_step table
CREATE TABLE test_step (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    step_number INT NOT NULL,
    description VARCHAR(255) NOT NULL,
    expected_result VARCHAR(255) NOT NULL,
    execution_result VARCHAR(255),
    test_case_id BIGINT NOT NULL,
    FOREIGN KEY (test_case_id) REFERENCES test_case(id)
);

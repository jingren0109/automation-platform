-- Create test_case table
CREATE TABLE test_case (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    test_suite_id BIGINT NOT NULL,
    FOREIGN KEY (test_suite_id) REFERENCES test_suite(id)
);
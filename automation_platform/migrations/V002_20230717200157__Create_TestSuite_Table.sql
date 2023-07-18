-- V1__Create_TestSuite_Table.sql

CREATE TABLE test_suite (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    project_id BIGINT NOT NULL,
    FOREIGN KEY (project_id) REFERENCES project(id)
);

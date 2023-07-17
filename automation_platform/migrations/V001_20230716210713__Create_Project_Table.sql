-- db/migration/V1__Create_Project_Table.sql

CREATE TABLE IF NOT EXISTS project (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255),
  start_date DATE,
  end_date DATE
);

DROP USER 'pd2_user'@'localhost';
DROP DATABASE IF EXISTS pd2_db;

CREATE DATABASE IF NOT EXISTS pd2_db;
CREATE USER 'pd2_user'@'localhost' IDENTIFIED BY 'pd2_user';
GRANT ALL ON pd2_db.* to 'pd2_user'@'localhost';
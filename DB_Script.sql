CREATE DATABASE gritodb;
CREATE TABLE billboardMain ( message char[100] );
CREATE USER 'user'@'localhost' IDENTIFIED BY 'user';
GRANT ALL PRIVILEGES ON *.* TO user@localhost



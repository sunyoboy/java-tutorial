DROP DATABASE IF EXISTS lieve;

CREATE DATABASE lieve
	CHARACTER   SET   'utf8'
	COLLATE   'utf8_general_ci';

GRANT ALL ON lieve.* TO online@'%'
	IDENTIFIED BY 'Online@123'
	WITH GRANT OPTION;
                                         
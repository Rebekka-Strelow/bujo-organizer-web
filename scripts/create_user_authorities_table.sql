 CREATE DATABASE IF NOT EXISTS `bujo_organizer`;
 
 USE `bujo_organizer`;
 
 DROP TABLE IF EXISTS `authorities`; 
 DROP TABLE IF EXISTS `users`;
 
 CREATE TABLE `users`(
	`username` varchar(50) NOT NULL,
	`password` varchar(50) NOT NULL,
	`enabled` tinyint(1) NOT NULL,
    PRIMARY KEY(`username`)
 ) ENGINE=InnoDB DEFAULT CHARSET=latin1; 
 
 CREATE TABLE `authorities`(
	`username` varchar(50) NOT NULL,
	`authority` varchar(50) NOT NULL,
    UNIQUE KEY `authorities_index_1`(`username`, `authority`),
    CONSTRAINT `authorities_fk_1` FOREIGN KEY(`username`) REFERENCES `users`(`username`)
 ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
 
 INSERT INTO `users` VALUES 
 ('bekka', '{noop}test123', 1),
 ('nils', '{noop}test456', 1);
 
 INSERT INTO `authorities` VALUES 
 ('bekka', 'ADMIN'), 
 ('bekka', 'USER'), 
 ('nils', 'USER')
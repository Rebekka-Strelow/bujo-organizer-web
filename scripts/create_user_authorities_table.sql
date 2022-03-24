 CREATE DATABASE IF NOT EXISTS `bujo_organizer_security`;
 
 USE `bujo_organizer_security`;
 
 SET FOREIGN_KEY_CHECKS = 0;
 DROP TABLE IF EXISTS `authorities`; 
 DROP TABLE IF EXISTS `users`;
 SET FOREIGN_KEY_CHECKS = 1;
 
 CREATE TABLE `users`(
	`username` varchar(100) NOT NULL,
	`password` varchar(100) NOT NULL,
	`enabled` tinyint(1) NOT NULL,
    PRIMARY KEY(`username`)
 ) ENGINE=InnoDB DEFAULT CHARSET=latin1; 
 
 CREATE TABLE `authorities`(
	`username` varchar(100) NOT NULL,
	`authority` varchar(50) NOT NULL,
    UNIQUE KEY `authorities_index_1`(`username`, `authority`),
    CONSTRAINT `authorities_fk_1` FOREIGN KEY(`username`) REFERENCES `users`(`username`)
 ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
 
 INSERT INTO `users` VALUES 
 ('bekka', '{bcrypt}$2a$06$EB5erLSYvTxGclClE7P/JuaMT1LeTud1LCAuvY9NPnqarpxFDQh2m', 1),
 ('nils', '{bcrypt}$2a$06$iSSoNLeWPJLhdBgg32fz6.SabzQ3og5nAOoszeXrNMqZ6j/AMFLay', 1);
 
 INSERT INTO `authorities` VALUES 
 ('bekka', 'ADMIN'), 
 ('bekka', 'USER'), 
 ('nils', 'USER');
 
CREATE DATABASE IF NOT EXISTS `bujo_organizer`; 
USE `bujo_organizer`;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `user_details`; 
SET FOREIGN_KEY_CHECKS = 1;
 
 
CREATE TABLE `user_details`(
	`username` varchar(100) NOT NULL,
	`email` varchar(100) NOT NULL,
	PRIMARY KEY(`username`)
 ) ENGINE=InnoDB DEFAULT CHARSET=latin1; 
 
 INSERT INTO `user_details` 
 VALUES ('bekka', 'bekka@bujo.io'), ('nils', 'nils@bujo.io');
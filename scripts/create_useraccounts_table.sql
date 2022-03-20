CREATE DATABASE IF NOT EXISTS `bujo_organizer`;

USE `bujo_organizer`;

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  unique (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

Insert into `account` values
	(1, "bekka", "test123", "bekka.test@bujo.de"),	
	(2, "nils", "test456", "nils_test@bujo.de");


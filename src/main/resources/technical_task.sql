CREATE DATABASE IF NOT EXISTS `technical_task`;
USE `technical_task`;

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `user_group` int(11) DEFAULT NULL,
  `course` varchar(50) DEFAULT NULL,
  `user_type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `name`, `age`, `user_group`, `course`, `user_type`) VALUES(1, 'Name', 20, 20, 'MAIN', 'TEACHER');
INSERT INTO `users` (`id`, `name`, `age`, `user_group`, `course`, `user_type`) VALUES(2, 'Name Name', 20, 20, 'SECONDARY', 'TEACHER');
INSERT INTO `users` (`id`, `name`, `age`, `user_group`, `course`, `user_type`) VALUES(3, 'Test', 20, 20, 'MAIN', 'TEACHER');
INSERT INTO `users` (`id`, `name`, `age`, `user_group`, `course`, `user_type`) VALUES(4, 'Test 2', 20, 20, 'SECONDARY', 'TEACHER');
INSERT INTO `users` (`id`, `name`, `age`, `user_group`, `course`, `user_type`) VALUES(5, 'Test Test', 20, 20, 'MAIN', 'TEACHER');
INSERT INTO `users` (`id`, `name`, `age`, `user_group`, `course`, `user_type`) VALUES(6, 'String', 20, 20, 'SECONDARY', 'TEACHER');
INSERT INTO `users` (`id`, `name`, `age`, `user_group`, `course`, `user_type`) VALUES(7, 'String String', 20, 20, 'MAIN', 'STUDENT');
INSERT INTO `users` (`id`, `name`, `age`, `user_group`, `course`, `user_type`) VALUES(8, 'Test', 20, 20, 'SECONDARY', 'STUDENT');
INSERT INTO `users` (`id`, `name`, `age`, `user_group`, `course`, `user_type`) VALUES(9, 'Name123', 20, 20, 'MAIN', 'STUDENT');
INSERT INTO `users` (`id`, `name`, `age`, `user_group`, `course`, `user_type`) VALUES(10, 'String123', 20, 20, 'SECONDARY', 'STUDENT');
INSERT INTO `users` (`id`, `name`, `age`, `user_group`, `course`, `user_type`) VALUES(11, 'Test123', 20, 20, 'MAIN', 'STUDENT');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
create database if not exists katikamudb;

use katikamudb;

drop table if exists `Students`;

CREATE TABLE `Students` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`class_id` INT NOT NULL,
	`first_name` varchar(255) NOT NULL,
	`last_name` varchar(255) NOT NULL,
	`gender` varchar(255) NOT NULL,
	`registration_number` varchar(255) NOT NULL UNIQUE,
	`age` INT(255) NOT NULL,
	`password` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

drop table if exists `Teachers`;

CREATE TABLE `Teachers` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255),
	`email` varchar(255) NOT NULL,
	`password` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

drop table if exists `Classes`;

CREATE TABLE `Classes` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255),
	`year` INT NOT NULL,
	PRIMARY KEY (`id`)
);
drop table if exists `Subjects`;

CREATE TABLE `Subjects` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
	
);
drop table if exists `Timetable`;

CREATE TABLE `Timetable` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`teacher_id` INT NOT NULL,
	`class_id` INT NOT NULL,
	`subject_id` INT NOT NULL,
	`day`  ENUM('Monday', 'Tuesday', 'Wednesday', 'Thursday','Friday')  NOT NULL,
	`time_start` TIME NOT NULL,
	`time_end` TIME NOT NULL,
	PRIMARY KEY (`id`)
);

drop table if exists `Results`;

CREATE TABLE `Results` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`subject_id` INT NOT NULL ,
	`student_id` INT NOT NULL,
	`mark` FLOAT NULL,
	`grade` varchar(255) NULL,
	PRIMARY KEY (`id`)
);


ALTER TABLE `Timetable` ADD CONSTRAINT `Timetable_fk0` FOREIGN KEY (`teacher_id`) REFERENCES `Teachers`(`id`);

ALTER TABLE `Timetable` ADD CONSTRAINT `Timetable_fk1` FOREIGN KEY (`class_id`) REFERENCES `Classes`(`id`);

ALTER TABLE `Timetable` ADD CONSTRAINT `Timetable_fk2` FOREIGN KEY (`subject_id`) REFERENCES `Subjects`(`id`);

ALTER TABLE `Results` ADD CONSTRAINT `Results_fk0` FOREIGN KEY (`subject_id`) REFERENCES `Subjects`(`id`);

ALTER TABLE `Students` ADD CONSTRAINT `students_fk1` FOREIGN KEY (`class_id`) REFERENCES `Classes`(`id`);

ALTER TABLE `Results` ADD CONSTRAINT `Results_fk2` FOREIGN KEY (`student_id`) REFERENCES `Students`(`id`);





INSERT INTO `Teachers` (`id`, `name`, `email`, `password`) VALUES (NULL, 'aksam', 'aksam@gmail.com', SHA1('1234'));
INSERT INTO `Teachers` (`id`, `name`, `email`, `password`) VALUES (NULL, 'kom', 'kom@gmail.com', SHA1('12345'));
INSERT INTO `Teachers` (`id`, `name`, `email`, `password`) VALUES (NULL, 'paul', 'paul@gmail.com', SHA1('123456'));
INSERT INTO `Teachers` (`id`, `name`, `email`, `password`) VALUES (NULL, 'gerald', 'gerald@gmail.com', SHA1('123456'));

INSERT INTO `Classes` (`id`, `name`, `year`) VALUES (NULL, 'P.1', '2022');
INSERT INTO `Classes` (`id`, `name`, `year`) VALUES (NULL, 'P.2', '2022');
INSERT INTO `Classes` (`id`, `name`, `year`) VALUES (NULL, 'P.3', '2022');
INSERT INTO `Classes` (`id`, `name`, `year`) VALUES (NULL, 'P.4', '2022');
INSERT INTO `Classes` (`id`, `name`, `year`) VALUES (NULL, 'P.5', '2022');
INSERT INTO `Classes` (`id`, `name`, `year`) VALUES (NULL, 'P.6', '2022');
INSERT INTO `Classes` (`id`, `name`, `year`) VALUES (NULL, 'P.7', '2022');

INSERT INTO `Subjects` (`id`, `name`) VALUES (NULL, 'ENGLISH'), (NULL, 'MATH');
INSERT INTO `Subjects` (`id`, `name`) VALUES (NULL, 'SST'), (NULL, 'SCIENCE'),(NULL,'No English'),(NULL,'No SST'),(NULL,'No Maths'),(NULL,'No Science');

INSERT INTO `Timetable` (`id`, `teacher_id`, `class_id`, `subject_id`, `day`, `time_start`, `time_end`) VALUES (NULL, '1', '2', '1', 'Monday', '21:50:38', '22:50:38');
INSERT INTO `Timetable` (`id`, `teacher_id`, `class_id`, `subject_id`, `day`, `time_start`, `time_end`) VALUES (NULL, '1', '3', '1', 'Monday', '21:50:38', '22:50:38');
INSERT INTO `Timetable` (`id`, `teacher_id`, `class_id`, `subject_id`, `day`, `time_start`, `time_end`) VALUES (NULL, '1', '4', '1', 'Monday', '21:50:38', '22:50:38');
INSERT INTO `Timetable` (`id`, `teacher_id`, `class_id`, `subject_id`, `day`, `time_start`, `time_end`) VALUES (NULL, '1', '5', '1', 'Monday', '21:50:38', '22:50:38');
create database if not exists katikamudb;

use katikamudb;

drop table if exists students;

CREATE TABLE `students` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `registration_number` varchar(64) NOT NULL,
  `first_name` varchar(64) NOT NULL,
  `last_name` varchar(64) NOT NULL,
  `gender` varchar(64) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `subject` varchar(64) DEFAULT NULL,
  `student_class` varchar(64) DEFAULT NULL,
  `mark` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `students` (`id`,`registration_number`,`first_name`,`last_name`,`gender`,`age`,`subject`,`student_class`,`mark`) VALUES (1,'A01','Paul','Aksam','Male', 28, 'Mathematics', 'P.7', 83);
INSERT INTO `students` (`id`,`registration_number`,`first_name`,`last_name`,`gender`,`age`,`subject`,`student_class`,`mark`) VALUES (2,'A02','Lwanga','Lumu','Male', 30, 'Science', 'P.6', 87);



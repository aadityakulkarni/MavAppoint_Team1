-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 07, 2016 at 02:27 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12


DROP DATABASE IF EXISTS mavappointdb2s;
CREATE DATABASE mavappointdb2s;
USE mavappointdb2s;


SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `mavappointdb2s`
--

-- --------------------------------------------------------

--
-- Table structure for table `advising_schedule`
--

CREATE TABLE IF NOT EXISTS `advising_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `date` date NOT NULL,
  `start` time NOT NULL,
  `end` time NOT NULL,
  `studentId` int(11) DEFAULT NULL,
  `schedule_id` int(11) DEFAULT NULL,
  `appid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userId` (`userId`,`date`,`start`),
  KEY `scheduleid` (`schedule_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=607 ;

--
-- Dumping data for table `advising_schedule`
--

INSERT INTO `advising_schedule` (`id`, `userId`, `date`, `start`, `end`, `studentId`, `schedule_id`, `appid`) VALUES
(1, 115, '2016-04-27', '13:00:00', '13:05:00', 1001238152, 3, NULL),
(2, 115, '2016-04-27', '13:05:00', '13:10:00', 1001238152, 3, NULL),
(3, 115, '2016-04-27', '13:10:00', '13:15:00', 1001238152, 3, NULL),
(4, 115, '2016-04-27', '13:15:00', '13:20:00', -1, 3, NULL),
(5, 115, '2016-04-27', '13:20:00', '13:25:00', -1, 3, NULL),
(6, 115, '2016-04-27', '13:25:00', '13:30:00', -1, 3, NULL),
(7, 115, '2016-05-04', '13:00:00', '13:05:00', -1, 3, 7),
(8, 115, '2016-05-04', '13:05:00', '13:10:00', -1, 3, 7),
(9, 115, '2016-05-04', '13:10:00', '13:15:00', -1, 3, 7),
(10, 115, '2016-05-04', '13:15:00', '13:20:00', NULL, 3, NULL),
(11, 115, '2016-05-04', '13:20:00', '13:25:00', NULL, 3, NULL),
(12, 115, '2016-05-04', '13:25:00', '13:30:00', NULL, 3, NULL),
(37, 115, '2016-04-28', '01:55:00', '02:00:00', NULL, 5, NULL),
(38, 115, '2016-04-28', '02:00:00', '02:05:00', NULL, 5, NULL),
(39, 115, '2016-04-28', '02:05:00', '02:10:00', NULL, 5, NULL),
(40, 115, '2016-04-28', '02:10:00', '02:15:00', NULL, 5, NULL),
(41, 115, '2016-04-28', '02:15:00', '02:20:00', NULL, 5, NULL),
(42, 115, '2016-04-28', '02:20:00', '02:25:00', NULL, 5, NULL),
(43, 115, '2016-04-28', '02:25:00', '02:30:00', NULL, 5, NULL),
(44, 115, '2016-04-28', '02:30:00', '02:35:00', NULL, 5, NULL),
(45, 115, '2016-04-28', '02:35:00', '02:40:00', NULL, 5, NULL),
(46, 115, '2016-04-28', '02:40:00', '02:45:00', NULL, 5, NULL),
(47, 115, '2016-04-28', '02:45:00', '02:50:00', NULL, 5, NULL),
(48, 115, '2016-04-28', '02:50:00', '02:55:00', NULL, 5, NULL),
(49, 115, '2016-05-05', '01:55:00', '02:00:00', NULL, 5, NULL),
(50, 115, '2016-05-05', '02:00:00', '02:05:00', NULL, 5, NULL),
(51, 115, '2016-05-05', '02:05:00', '02:10:00', NULL, 5, NULL),
(52, 115, '2016-05-05', '02:10:00', '02:15:00', NULL, 5, NULL),
(53, 115, '2016-05-05', '02:15:00', '02:20:00', NULL, 5, NULL),
(54, 115, '2016-05-05', '02:20:00', '02:25:00', NULL, 5, NULL),
(55, 115, '2016-05-05', '02:25:00', '02:30:00', NULL, 5, NULL),
(56, 115, '2016-05-05', '02:30:00', '02:35:00', NULL, 5, NULL),
(57, 115, '2016-05-05', '02:35:00', '02:40:00', NULL, 5, NULL),
(58, 115, '2016-05-05', '02:40:00', '02:45:00', NULL, 5, NULL),
(59, 115, '2016-05-05', '02:45:00', '02:50:00', NULL, 5, NULL),
(60, 115, '2016-05-05', '02:50:00', '02:55:00', NULL, 5, NULL),
(61, 115, '2016-05-12', '01:55:00', '02:00:00', NULL, 5, NULL),
(62, 115, '2016-05-12', '02:00:00', '02:05:00', NULL, 5, NULL),
(63, 115, '2016-05-12', '02:05:00', '02:10:00', NULL, 5, NULL),
(64, 115, '2016-05-12', '02:10:00', '02:15:00', NULL, 5, NULL),
(65, 115, '2016-05-12', '02:15:00', '02:20:00', NULL, 5, NULL),
(66, 115, '2016-05-12', '02:20:00', '02:25:00', NULL, 5, NULL),
(67, 115, '2016-05-12', '02:25:00', '02:30:00', NULL, 5, NULL),
(68, 115, '2016-05-12', '02:30:00', '02:35:00', NULL, 5, NULL),
(69, 115, '2016-05-12', '02:35:00', '02:40:00', NULL, 5, NULL),
(70, 115, '2016-05-12', '02:40:00', '02:45:00', NULL, 5, NULL),
(71, 115, '2016-05-12', '02:45:00', '02:50:00', NULL, 5, NULL),
(72, 115, '2016-05-12', '02:50:00', '02:55:00', NULL, 5, NULL),
(73, 115, '2016-05-19', '01:55:00', '02:00:00', NULL, 5, NULL),
(74, 115, '2016-05-19', '02:00:00', '02:05:00', NULL, 5, NULL),
(75, 115, '2016-05-19', '02:05:00', '02:10:00', NULL, 5, NULL),
(76, 115, '2016-05-19', '02:10:00', '02:15:00', NULL, 5, NULL),
(77, 115, '2016-05-19', '02:15:00', '02:20:00', NULL, 5, NULL),
(78, 115, '2016-05-19', '02:20:00', '02:25:00', NULL, 5, NULL),
(79, 115, '2016-05-19', '02:25:00', '02:30:00', NULL, 5, NULL),
(80, 115, '2016-05-19', '02:30:00', '02:35:00', NULL, 5, NULL),
(81, 115, '2016-05-19', '02:35:00', '02:40:00', NULL, 5, NULL),
(82, 115, '2016-05-19', '02:40:00', '02:45:00', NULL, 5, NULL),
(83, 115, '2016-05-19', '02:45:00', '02:50:00', NULL, 5, NULL),
(84, 115, '2016-05-19', '02:50:00', '02:55:00', NULL, 5, NULL),
(109, 115, '2016-05-11', '13:00:00', '13:05:00', NULL, 11, NULL),
(110, 115, '2016-05-11', '13:05:00', '13:10:00', NULL, 11, NULL),
(111, 115, '2016-05-11', '13:10:00', '13:15:00', NULL, 11, NULL),
(112, 115, '2016-05-11', '13:15:00', '13:20:00', NULL, 11, NULL),
(113, 115, '2016-05-11', '13:20:00', '13:25:00', NULL, 11, NULL),
(114, 115, '2016-05-11', '13:25:00', '13:30:00', NULL, 11, NULL),
(115, 115, '2016-05-11', '13:30:00', '13:35:00', NULL, 11, NULL),
(116, 115, '2016-05-11', '13:35:00', '13:40:00', NULL, 11, NULL),
(117, 115, '2016-05-11', '13:40:00', '13:45:00', NULL, 11, NULL),
(118, 115, '2016-05-11', '13:45:00', '13:50:00', NULL, 11, NULL),
(119, 115, '2016-05-11', '13:50:00', '13:55:00', NULL, 11, NULL),
(120, 115, '2016-05-11', '13:55:00', '14:00:00', NULL, 11, NULL),
(121, 115, '2016-05-11', '14:00:00', '14:05:00', NULL, 11, NULL),
(122, 115, '2016-05-11', '14:05:00', '14:10:00', NULL, 11, NULL),
(123, 115, '2016-05-11', '14:10:00', '14:15:00', NULL, 11, NULL),
(124, 115, '2016-05-11', '14:15:00', '14:20:00', NULL, 11, NULL),
(125, 115, '2016-05-11', '14:20:00', '14:25:00', NULL, 11, NULL),
(126, 115, '2016-05-11', '14:25:00', '14:30:00', NULL, 11, NULL),
(445, 115, '2016-05-09', '13:00:00', '13:05:00', NULL, 18, NULL),
(446, 115, '2016-05-09', '13:05:00', '13:10:00', NULL, 18, NULL),
(447, 115, '2016-05-09', '13:10:00', '13:15:00', NULL, 18, NULL),
(448, 115, '2016-05-09', '13:15:00', '13:20:00', NULL, 18, NULL),
(449, 115, '2016-05-09', '13:20:00', '13:25:00', NULL, 18, NULL),
(450, 115, '2016-05-09', '13:25:00', '13:30:00', NULL, 18, NULL),
(451, 115, '2016-05-09', '13:30:00', '13:35:00', NULL, 18, NULL),
(452, 115, '2016-05-09', '13:35:00', '13:40:00', NULL, 18, NULL),
(453, 115, '2016-05-09', '13:40:00', '13:45:00', NULL, 18, NULL),
(454, 115, '2016-05-09', '13:45:00', '13:50:00', NULL, 18, NULL),
(455, 115, '2016-05-09', '13:50:00', '13:55:00', NULL, 18, NULL),
(456, 115, '2016-05-09', '13:55:00', '14:00:00', NULL, 18, NULL),
(457, 115, '2016-05-09', '14:00:00', '14:05:00', NULL, 18, NULL),
(458, 115, '2016-05-09', '14:05:00', '14:10:00', NULL, 18, NULL),
(459, 115, '2016-05-16', '13:00:00', '13:05:00', NULL, 18, NULL),
(460, 115, '2016-05-16', '13:05:00', '13:10:00', NULL, 18, NULL),
(461, 115, '2016-05-16', '13:10:00', '13:15:00', NULL, 18, NULL),
(462, 115, '2016-05-16', '13:15:00', '13:20:00', NULL, 18, NULL),
(463, 115, '2016-05-16', '13:20:00', '13:25:00', NULL, 18, NULL),
(464, 115, '2016-05-16', '13:25:00', '13:30:00', NULL, 18, NULL),
(465, 115, '2016-05-16', '13:30:00', '13:35:00', NULL, 18, NULL),
(466, 115, '2016-05-16', '13:35:00', '13:40:00', NULL, 18, NULL),
(467, 115, '2016-05-16', '13:40:00', '13:45:00', NULL, 18, NULL),
(468, 115, '2016-05-16', '13:45:00', '13:50:00', NULL, 18, NULL),
(469, 115, '2016-05-16', '13:50:00', '13:55:00', NULL, 18, NULL),
(470, 115, '2016-05-16', '13:55:00', '14:00:00', NULL, 18, NULL),
(471, 115, '2016-05-16', '14:00:00', '14:05:00', NULL, 18, NULL),
(472, 115, '2016-05-16', '14:05:00', '14:10:00', NULL, 18, NULL),
(589, 115, '2016-05-26', '02:15:00', '02:20:00', NULL, 20, NULL),
(590, 115, '2016-05-26', '02:20:00', '02:25:00', NULL, 20, NULL),
(591, 115, '2016-05-26', '02:25:00', '02:30:00', NULL, 20, NULL),
(592, 115, '2016-05-26', '02:30:00', '02:35:00', NULL, 20, NULL),
(593, 115, '2016-05-26', '02:35:00', '02:40:00', NULL, 20, NULL),
(594, 115, '2016-05-26', '02:40:00', '02:45:00', NULL, 20, NULL),
(595, 115, '2016-05-26', '02:45:00', '02:50:00', NULL, 20, NULL),
(596, 115, '2016-05-26', '02:50:00', '02:55:00', NULL, 20, NULL),
(597, 115, '2016-05-26', '02:55:00', '03:00:00', NULL, 20, NULL),
(598, 115, '2016-06-02', '02:15:00', '02:20:00', NULL, 20, NULL),
(599, 115, '2016-06-02', '02:20:00', '02:25:00', NULL, 20, NULL),
(600, 115, '2016-06-02', '02:25:00', '02:30:00', NULL, 20, NULL),
(601, 115, '2016-06-02', '02:30:00', '02:35:00', NULL, 20, NULL),
(602, 115, '2016-06-02', '02:35:00', '02:40:00', NULL, 20, NULL),
(603, 115, '2016-06-02', '02:40:00', '02:45:00', NULL, 20, NULL),
(604, 115, '2016-06-02', '02:45:00', '02:50:00', NULL, 20, NULL),
(605, 115, '2016-06-02', '02:50:00', '02:55:00', NULL, 20, NULL),
(606, 115, '2016-06-02', '02:55:00', '03:00:00', NULL, 20, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

CREATE TABLE IF NOT EXISTS `appointments` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `advisor_userId` int(11) NOT NULL,
  `student_userId` int(11) DEFAULT NULL,
  `date` date NOT NULL,
  `start` time NOT NULL,
  `end` time NOT NULL,
  `type` varchar(45) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `studentId` int(11) DEFAULT NULL,
  `student_email` varchar(50) DEFAULT NULL,
  `student_cell` varchar(20) DEFAULT NULL,
  `comments` text,
  PRIMARY KEY (`Id`),
  KEY `advisor_userId` (`advisor_userId`,`type`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=294 ;

--
-- Dumping data for table `appointments`
--

INSERT INTO `appointments` (`Id`, `advisor_userId`, `student_userId`, `date`, `start`, `end`, `type`, `description`, `studentId`, `student_email`, `student_cell`, `comments`) VALUES
(1, 115, 117, '2016-04-27', '13:00:00', '13:15:00', 'add course', 'I want to add wdm', 1001238152, 'aaditya.kulkarni@mavs.uta.edu', '682-248-7010', ' '),
(4, 115, 0, '2016-04-27', '13:15:00', '13:30:00', 'add course', 'I want to add DB1', NULL, 'developersuta@gmail.com', '6822487010', ' Done'),
(7, 115, 0, '2016-05-04', '13:00:00', '13:15:00', 'add course', 'Add Course WDM', NULL, 'developersuta@yahoo.com', '6822487010', ' '),
(37, 115, 117, '2016-04-28', '01:55:00', '02:10:00', 'add course', 'I want to add DB2', 1001238152, 'aaditya.kulkarni@mavs.uta.edu', '682-248-7010', ' ');

-- --------------------------------------------------------

--
-- Table structure for table `appointment_types`
--

CREATE TABLE IF NOT EXISTS `appointment_types` (
  `userId` int(11) NOT NULL,
  `type` varchar(45) NOT NULL,
  `duration` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`type`),
  UNIQUE KEY `userId` (`userId`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `appointment_types`
--

INSERT INTO `appointment_types` (`userId`, `type`, `duration`) VALUES
(115, 'add course', 15),
(115, 'CPT / OPT', 20),
(116, 'drop course', 10),
(116, 'Recommendation', 15);

-- --------------------------------------------------------

--
-- Table structure for table `degree_type`
--

CREATE TABLE IF NOT EXISTS `degree_type` (
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`name`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `degree_type_user`
--

CREATE TABLE IF NOT EXISTS `degree_type_user` (
  `name` varchar(45) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`name`,`userId`),
  UNIQUE KEY `userId` (`userId`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE IF NOT EXISTS `department` (
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`name`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`name`) VALUES
('ARCH'),
('CSE'),
('MAE'),
('MATH');

-- --------------------------------------------------------

--
-- Table structure for table `department_user`
--

CREATE TABLE IF NOT EXISTS `department_user` (
  `name` varchar(45) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`name`,`userId`),
  UNIQUE KEY `userId` (`userId`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `department_user`
--

INSERT INTO `department_user` (`name`, `userId`) VALUES
('CSE', 114),
('CSE', 115),
('CSE', 116),
('CSE', 117),
('CSE', 118),
('CSE', 119),
('CSE', 120),
('CSE', 121);

-- --------------------------------------------------------

--
-- Table structure for table `major`
--

CREATE TABLE IF NOT EXISTS `major` (
  `name` varchar(45) NOT NULL,
  `dep_name` varchar(45) NOT NULL,
  PRIMARY KEY (`name`),
  UNIQUE KEY `name` (`name`),
  KEY `dep_name` (`dep_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `major`
--

INSERT INTO `major` (`name`, `dep_name`) VALUES
('Architecture', 'ARCH'),
('Interior Design', 'ARCH'),
('Landscape Architecture', 'ARCH'),
('Computer Engineering', 'CSE'),
('Computer Science', 'CSE'),
('Software Engineering', 'CSE'),
('Aerospace Engineering', 'MAE'),
('Mechanical Engineering', 'MAE'),
('Mathematics', 'MATH');

-- --------------------------------------------------------

--
-- Table structure for table `major_user`
--

CREATE TABLE IF NOT EXISTS `major_user` (
  `name` varchar(45) NOT NULL,
  `userId` int(11) NOT NULL,
  `minor` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`name`,`userId`),
  UNIQUE KEY `userId` (`userId`,`name`),
  KEY `minor` (`minor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `major_user`
--

INSERT INTO `major_user` (`name`, `userId`, `minor`) VALUES
('Computer Science', 114, NULL),
('Computer Science', 115, NULL),
('Computer Science', 116, NULL),
('Computer Science', 117, NULL),
('Computer Engineering', 121, 'Architecture'),
('Computer Science', 120, 'Architecture'),
('Software Engineering', 118, 'Architecture');

-- --------------------------------------------------------

--
-- Table structure for table `resetpass`
--

CREATE TABLE IF NOT EXISTS `resetpass` (
  `email` varchar(255) NOT NULL,
  `uniqueid` varchar(255) NOT NULL,
  `datevalid` datetime NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE IF NOT EXISTS `schedule` (
  `schedule_id` int(11) NOT NULL AUTO_INCREMENT,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  PRIMARY KEY (`schedule_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Dumping data for table `schedule`
--

INSERT INTO `schedule` (`schedule_id`, `start_date`, `end_date`, `start_time`, `end_time`) VALUES
(3, '2016-04-27', '2016-05-04', '13:00:00', '13:30:00'),
(5, '2016-04-28', '2016-05-19', '01:55:00', '02:55:00'),
(11, '2016-05-11', '2016-05-11', '13:00:00', '14:30:00'),
(18, '2016-05-09', '2016-05-16', '13:00:00', '14:10:00'),
(20, '2016-05-26', '2016-06-02', '02:15:00', '03:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(32) NOT NULL,
  `role` varchar(32) DEFAULT NULL,
  `validated` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=122 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userId`, `email`, `password`, `role`, `validated`) VALUES
(114, 'aadikulkarni91@gmail.com', '_MÌ;Z§eÖƒ''Þ¸‚Ï™', 'admin', 1),
(115, 'akaadi3@gmail.com', '_MÌ;Z§eÖƒ''Þ¸‚Ï™', 'advisor', 1),
(116, 'dev.junk.998@gmail.com', '_MÌ;Z§eÖƒ''Þ¸‚Ï™', 'advisor', 1),
(117, 'aaditya.kulkarni@mavs.uta.edu', '_MÌ;Z§eÖƒ''Þ¸‚Ï™', 'student', 1),
(118, 'yathaarth.bhansali@mavs.uta.edu', '_MÌ;Z§eÖƒ''Þ¸‚Ï™', 'student', 1),
(119, 'dev.junk.997@gmail.com', '_MÌ;Z§eÖƒ''Þ¸‚Ï™', 'admin', 1),
(120, 'kulkarniaaditya@yahoo.com', 'á­\nH!\r9ø8¿öS/h^Ÿ', 'student', 0),
(121, 'abhinav.joshi@mavs.uta.edu', '’ålÎ{“4óáñ¦', 'student', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user_advisor`
--

CREATE TABLE IF NOT EXISTS `user_advisor` (
  `userId` int(11) NOT NULL,
  `pName` varchar(32) NOT NULL,
  `notification` varchar(45) NOT NULL,
  `name_low` varchar(2) NOT NULL,
  `name_high` varchar(2) NOT NULL,
  `degree_types` int(11) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userId` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_advisor`
--

INSERT INTO `user_advisor` (`userId`, `pName`, `notification`, `name_low`, `name_high`, `degree_types`) VALUES
(115, 'Bahram Khalili', 'yes', 'A', 'Z', 7),
(116, 'Ramez Elmasri', 'yes', 'A', 'Z', 7);

-- --------------------------------------------------------

--
-- Table structure for table `user_student`
--

CREATE TABLE IF NOT EXISTS `user_student` (
  `userId` int(11) NOT NULL,
  `student_Id` int(11) DEFAULT NULL,
  `degree_type` int(11) NOT NULL,
  `phone_num` varchar(45) NOT NULL,
  `last_name_initial` varchar(2) NOT NULL,
  `notification` varchar(45) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userId` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_student`
--

INSERT INTO `user_student` (`userId`, `student_Id`, `degree_type`, `phone_num`, `last_name_initial`, `notification`) VALUES
(117, 1001238152, 2, '682-248-7010', 'K', 'yes'),
(118, 1001238153, 2, '682-248-7011', 'B', 'yes'),
(120, NULL, 2, '682-258-7010', 'K', 'yes'),
(121, 1001112727, 2, '682-560-7378', 'J', 'yes');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `advising_schedule`
--
ALTER TABLE `advising_schedule`
  ADD CONSTRAINT `advising_schedule_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user_advisor` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `advising_schedule_ibfk_2` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`schedule_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `appointments_ibfk_1` FOREIGN KEY (`advisor_userId`) REFERENCES `user_advisor` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`advisor_userId`, `type`) REFERENCES `appointment_types` (`userId`, `type`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `appointment_types`
--
ALTER TABLE `appointment_types`
  ADD CONSTRAINT `appointment_types_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user_advisor` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `degree_type_user`
--
ALTER TABLE `degree_type_user`
  ADD CONSTRAINT `degree_type_user_ibfk_1` FOREIGN KEY (`name`) REFERENCES `degree_type` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `degree_type_user_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `department_user`
--
ALTER TABLE `department_user`
  ADD CONSTRAINT `department_user_ibfk_1` FOREIGN KEY (`name`) REFERENCES `department` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `department_user_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `major`
--
ALTER TABLE `major`
  ADD CONSTRAINT `major_ibfk_1` FOREIGN KEY (`dep_name`) REFERENCES `department` (`name`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `major_user`
--
ALTER TABLE `major_user`
  ADD CONSTRAINT `major_user_ibfk_1` FOREIGN KEY (`name`) REFERENCES `major` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `major_user_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `major_user_ibfk_3` FOREIGN KEY (`minor`) REFERENCES `major` (`name`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user_advisor`
--
ALTER TABLE `user_advisor`
  ADD CONSTRAINT `user_advisor_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user_student`
--
ALTER TABLE `user_student`
  ADD CONSTRAINT `user_student_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

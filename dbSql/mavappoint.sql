-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 03, 2016 at 01:51 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=343 ;

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
(85, 115, '2016-05-26', '01:55:00', '02:00:00', 1001238152, 5, 85),
(86, 115, '2016-05-26', '02:00:00', '02:05:00', 1001238152, 5, 85),
(87, 115, '2016-05-26', '02:05:00', '02:10:00', 1001238152, 5, 85),
(88, 115, '2016-05-26', '02:10:00', '02:15:00', 1001238152, 5, 85),
(89, 115, '2016-05-26', '02:15:00', '02:20:00', NULL, 5, NULL),
(90, 115, '2016-05-26', '02:20:00', '02:25:00', NULL, 5, NULL),
(91, 115, '2016-05-26', '02:25:00', '02:30:00', NULL, 5, NULL),
(92, 115, '2016-05-26', '02:30:00', '02:35:00', NULL, 5, NULL),
(93, 115, '2016-05-26', '02:35:00', '02:40:00', NULL, 5, NULL),
(94, 115, '2016-05-26', '02:40:00', '02:45:00', NULL, 5, NULL),
(95, 115, '2016-05-26', '02:45:00', '02:50:00', NULL, 5, NULL),
(96, 115, '2016-05-26', '02:50:00', '02:55:00', NULL, 5, NULL),
(97, 115, '2016-06-02', '01:55:00', '02:00:00', NULL, 5, NULL),
(98, 115, '2016-06-02', '02:00:00', '02:05:00', NULL, 5, NULL),
(99, 115, '2016-06-02', '02:05:00', '02:10:00', NULL, 5, NULL),
(100, 115, '2016-06-02', '02:10:00', '02:15:00', NULL, 5, NULL),
(101, 115, '2016-06-02', '02:15:00', '02:20:00', NULL, 5, NULL),
(102, 115, '2016-06-02', '02:20:00', '02:25:00', NULL, 5, NULL),
(103, 115, '2016-06-02', '02:25:00', '02:30:00', NULL, 5, NULL),
(104, 115, '2016-06-02', '02:30:00', '02:35:00', NULL, 5, NULL),
(105, 115, '2016-06-02', '02:35:00', '02:40:00', NULL, 5, NULL),
(106, 115, '2016-06-02', '02:40:00', '02:45:00', NULL, 5, NULL),
(107, 115, '2016-06-02', '02:45:00', '02:50:00', NULL, 5, NULL),
(108, 115, '2016-06-02', '02:50:00', '02:55:00', NULL, 5, NULL),
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
(199, 115, '2016-05-09', '13:00:00', '13:05:00', NULL, 14, NULL),
(200, 115, '2016-05-09', '13:05:00', '13:10:00', NULL, 14, NULL),
(201, 115, '2016-05-09', '13:10:00', '13:15:00', NULL, 14, NULL),
(202, 115, '2016-05-09', '13:15:00', '13:20:00', NULL, 14, NULL),
(203, 115, '2016-05-09', '13:20:00', '13:25:00', NULL, 14, NULL),
(204, 115, '2016-05-09', '13:25:00', '13:30:00', NULL, 14, NULL),
(205, 115, '2016-05-09', '13:30:00', '13:35:00', NULL, 14, NULL),
(206, 115, '2016-05-09', '13:35:00', '13:40:00', NULL, 14, NULL),
(207, 115, '2016-05-09', '13:40:00', '13:45:00', NULL, 14, NULL),
(208, 115, '2016-05-09', '13:45:00', '13:50:00', NULL, 14, NULL),
(209, 115, '2016-05-09', '13:50:00', '13:55:00', NULL, 14, NULL),
(210, 115, '2016-05-09', '13:55:00', '14:00:00', NULL, 14, NULL),
(211, 115, '2016-05-16', '13:00:00', '13:05:00', NULL, 14, NULL),
(212, 115, '2016-05-16', '13:05:00', '13:10:00', NULL, 14, NULL),
(213, 115, '2016-05-16', '13:10:00', '13:15:00', NULL, 14, NULL),
(214, 115, '2016-05-16', '13:15:00', '13:20:00', NULL, 14, NULL),
(215, 115, '2016-05-16', '13:20:00', '13:25:00', NULL, 14, NULL),
(216, 115, '2016-05-16', '13:25:00', '13:30:00', NULL, 14, NULL),
(217, 115, '2016-05-16', '13:30:00', '13:35:00', NULL, 14, NULL),
(218, 115, '2016-05-16', '13:35:00', '13:40:00', NULL, 14, NULL),
(219, 115, '2016-05-16', '13:40:00', '13:45:00', NULL, 14, NULL),
(220, 115, '2016-05-16', '13:45:00', '13:50:00', NULL, 14, NULL),
(221, 115, '2016-05-16', '13:50:00', '13:55:00', NULL, 14, NULL),
(222, 115, '2016-05-16', '13:55:00', '14:00:00', NULL, 14, NULL),
(271, 115, '2016-05-23', '13:00:00', '13:05:00', NULL, 15, NULL),
(272, 115, '2016-05-23', '13:05:00', '13:10:00', NULL, 15, NULL),
(273, 115, '2016-05-23', '13:10:00', '13:15:00', NULL, 15, NULL),
(274, 115, '2016-05-23', '13:15:00', '13:20:00', NULL, 15, NULL),
(275, 115, '2016-05-23', '13:20:00', '13:25:00', NULL, 15, NULL),
(276, 115, '2016-05-23', '13:25:00', '13:30:00', NULL, 15, NULL),
(277, 115, '2016-05-23', '13:30:00', '13:35:00', NULL, 15, NULL),
(278, 115, '2016-05-23', '13:35:00', '13:40:00', NULL, 15, NULL),
(279, 115, '2016-05-23', '13:40:00', '13:45:00', NULL, 15, NULL),
(280, 115, '2016-05-23', '13:45:00', '13:50:00', NULL, 15, NULL),
(281, 115, '2016-05-23', '13:50:00', '13:55:00', NULL, 15, NULL),
(282, 115, '2016-05-23', '13:55:00', '14:00:00', NULL, 15, NULL),
(283, 115, '2016-05-23', '14:00:00', '14:05:00', NULL, 15, NULL),
(284, 115, '2016-05-23', '14:05:00', '14:10:00', NULL, 15, NULL),
(285, 115, '2016-05-23', '14:10:00', '14:15:00', NULL, 15, NULL),
(286, 115, '2016-05-23', '14:15:00', '14:20:00', NULL, 15, NULL),
(287, 115, '2016-05-23', '14:20:00', '14:25:00', NULL, 15, NULL),
(288, 115, '2016-05-23', '14:25:00', '14:30:00', NULL, 15, NULL),
(289, 115, '2016-05-30', '13:00:00', '13:05:00', NULL, 15, NULL),
(290, 115, '2016-05-30', '13:05:00', '13:10:00', NULL, 15, NULL),
(291, 115, '2016-05-30', '13:10:00', '13:15:00', NULL, 15, NULL),
(292, 115, '2016-05-30', '13:15:00', '13:20:00', NULL, 15, NULL),
(293, 115, '2016-05-30', '13:20:00', '13:25:00', NULL, 15, 293),
(294, 115, '2016-05-30', '13:25:00', '13:30:00', NULL, 15, 293),
(295, 115, '2016-05-30', '13:30:00', '13:35:00', NULL, 15, 293),
(296, 115, '2016-05-30', '13:35:00', '13:40:00', NULL, 15, 293),
(297, 115, '2016-05-30', '13:40:00', '13:45:00', NULL, 15, NULL),
(298, 115, '2016-05-30', '13:45:00', '13:50:00', NULL, 15, NULL),
(299, 115, '2016-05-30', '13:50:00', '13:55:00', NULL, 15, NULL),
(300, 115, '2016-05-30', '13:55:00', '14:00:00', NULL, 15, NULL),
(301, 115, '2016-05-30', '14:00:00', '14:05:00', NULL, 15, NULL),
(302, 115, '2016-05-30', '14:05:00', '14:10:00', NULL, 15, NULL),
(303, 115, '2016-05-30', '14:10:00', '14:15:00', NULL, 15, NULL),
(304, 115, '2016-05-30', '14:15:00', '14:20:00', NULL, 15, NULL),
(305, 115, '2016-05-30', '14:20:00', '14:25:00', NULL, 15, NULL),
(306, 115, '2016-05-30', '14:25:00', '14:30:00', NULL, 15, NULL),
(307, 115, '2016-06-06', '13:00:00', '13:05:00', NULL, 15, NULL),
(308, 115, '2016-06-06', '13:05:00', '13:10:00', NULL, 15, NULL),
(309, 115, '2016-06-06', '13:10:00', '13:15:00', NULL, 15, NULL),
(310, 115, '2016-06-06', '13:15:00', '13:20:00', NULL, 15, NULL),
(311, 115, '2016-06-06', '13:20:00', '13:25:00', NULL, 15, NULL),
(312, 115, '2016-06-06', '13:25:00', '13:30:00', NULL, 15, NULL),
(313, 115, '2016-06-06', '13:30:00', '13:35:00', NULL, 15, NULL),
(314, 115, '2016-06-06', '13:35:00', '13:40:00', NULL, 15, NULL),
(315, 115, '2016-06-06', '13:40:00', '13:45:00', NULL, 15, NULL),
(316, 115, '2016-06-06', '13:45:00', '13:50:00', NULL, 15, NULL),
(317, 115, '2016-06-06', '13:50:00', '13:55:00', NULL, 15, NULL),
(318, 115, '2016-06-06', '13:55:00', '14:00:00', NULL, 15, NULL),
(319, 115, '2016-06-06', '14:00:00', '14:05:00', NULL, 15, NULL),
(320, 115, '2016-06-06', '14:05:00', '14:10:00', NULL, 15, NULL),
(321, 115, '2016-06-06', '14:10:00', '14:15:00', NULL, 15, NULL),
(322, 115, '2016-06-06', '14:15:00', '14:20:00', NULL, 15, NULL),
(323, 115, '2016-06-06', '14:20:00', '14:25:00', NULL, 15, NULL),
(324, 115, '2016-06-06', '14:25:00', '14:30:00', NULL, 15, NULL),
(325, 115, '2016-06-13', '13:00:00', '13:05:00', NULL, 15, NULL),
(326, 115, '2016-06-13', '13:05:00', '13:10:00', NULL, 15, NULL),
(327, 115, '2016-06-13', '13:10:00', '13:15:00', NULL, 15, NULL),
(328, 115, '2016-06-13', '13:15:00', '13:20:00', NULL, 15, NULL),
(329, 115, '2016-06-13', '13:20:00', '13:25:00', NULL, 15, NULL),
(330, 115, '2016-06-13', '13:25:00', '13:30:00', NULL, 15, NULL),
(331, 115, '2016-06-13', '13:30:00', '13:35:00', NULL, 15, NULL),
(332, 115, '2016-06-13', '13:35:00', '13:40:00', NULL, 15, NULL),
(333, 115, '2016-06-13', '13:40:00', '13:45:00', NULL, 15, NULL),
(334, 115, '2016-06-13', '13:45:00', '13:50:00', NULL, 15, NULL),
(335, 115, '2016-06-13', '13:50:00', '13:55:00', NULL, 15, NULL),
(336, 115, '2016-06-13', '13:55:00', '14:00:00', NULL, 15, NULL),
(337, 115, '2016-06-13', '14:00:00', '14:05:00', NULL, 15, NULL),
(338, 115, '2016-06-13', '14:05:00', '14:10:00', NULL, 15, NULL),
(339, 115, '2016-06-13', '14:10:00', '14:15:00', NULL, 15, NULL),
(340, 115, '2016-06-13', '14:15:00', '14:20:00', NULL, 15, NULL),
(341, 115, '2016-06-13', '14:20:00', '14:25:00', NULL, 15, NULL),
(342, 115, '2016-06-13', '14:25:00', '14:30:00', NULL, 15, NULL);

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
(37, 115, 117, '2016-04-28', '01:55:00', '02:10:00', 'add course', 'I want to add DB2', 1001238152, 'aaditya.kulkarni@mavs.uta.edu', '682-248-7010', ' '),
(85, 115, 117, '2016-05-26', '01:55:00', '02:15:00', 'CPT / OPT', 'Got Internship at Amazon', 1001238152, 'aaditya.kulkarni@mavs.uta.edu', '682-248-7010', ' ');

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
('ARCH', 63),
('ARCH', 65),
('ARCH', 70),
('ARCH', 78),
('ARCH', 79),
('ARCH', 80),
('ARCH', 82),
('ARCH', 85),
('ARCH', 86),
('ARCH', 87),
('ARCH', 90),
('CSE', 88),
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
('Architecture', 63, NULL),
('Architecture', 65, NULL),
('Architecture', 70, NULL),
('Architecture', 78, NULL),
('Architecture', 79, NULL),
('Architecture', 80, NULL),
('Architecture', 82, NULL),
('Architecture', 85, NULL),
('Architecture', 86, NULL),
('Architecture', 87, NULL),
('Architecture', 90, NULL),
('Computer Engineering', 88, NULL),
('Computer Science', 114, NULL),
('Computer Science', 115, NULL),
('Computer Science', 116, NULL),
('Computer Science', 117, NULL),
('Interior Design', 80, NULL),
('Interior Design', 82, NULL),
('Interior Design', 85, NULL),
('Landscape Architecture', 80, NULL),
('Landscape Architecture', 82, NULL),
('Landscape Architecture', 85, NULL),
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

--
-- Dumping data for table `resetpass`
--

INSERT INTO `resetpass` (`email`, `uniqueid`, `datevalid`) VALUES
('aaditya.kulkarni@mavs.uta.edu', 'ayhe!r!61k', '2016-05-03 03:44:29'),
('akaadi3@gmail.com', '@huju_cdqv', '2016-05-03 01:10:48');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `schedule`
--

INSERT INTO `schedule` (`schedule_id`, `start_date`, `end_date`, `start_time`, `end_time`) VALUES
(3, '2016-04-27', '2016-05-04', '13:00:00', '13:30:00'),
(5, '2016-04-28', '2016-06-02', '01:55:00', '02:55:00'),
(11, '2016-05-11', '2016-05-11', '13:00:00', '14:30:00'),
(14, '2016-05-09', '2016-05-16', '13:00:00', '14:00:00'),
(15, '2016-05-23', '2016-06-13', '13:00:00', '14:30:00');

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
(63, 'somnath.shekhar@mavs.uta.edu', 'פ�D-�:�<�re��', 'student', 1),
(65, 'ruchi.tengse@mavs.uta.edu', 'פ�D-�:�<�re��', 'admin', 1),
(70, 'prawal.sharma@mavs.uta.edu', 'פ�D-�:�<�re��', 'student', 1),
(78, 'ruchitengse@gmail.com', 'פ�D-�:�<�re��', 'student', 1),
(79, 'padmashri.nonaburkrishnamurthy@mavs.uta.edu', 'פ�D-�:�<�re��', 'student', 1),
(80, 'test123@mavs.uta.edu', 'r��\n`Z2�5:`�ٔ_', 'advisor', 0),
(82, 'yu@gmail.com', '�1>4��9��\Z�N%��', 'advisor', 0),
(85, 'ashwin.rameshbabu@mavs.uta.edu', '�e�:KҨCT*�G�Z', 'advisor', 0),
(86, 'vidyadhar.angadiyavar@mavs.uta.edu', '�m������=&����', 'student', 1),
(87, 'mandarupadhye@gmail.com', 'פ�D-�:�<�re��', 'student', 1),
(88, 'ruchi.smiley@gmail.com', 'פ�D-�:�<�re��', 'student', 1),
(90, 'teststudent@mavs.uta.edu', ' +��jJ���5�ɋ�', 'student', 0),
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
(85, 'Ashwin Ramesh', 'yes', 'A', 'Z', 7),
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
(63, 1001084560, 1, '817-823-3972', 'A', 'yes'),
(70, 1001084561, 1, '987-097-4653', 'A', 'yes'),
(78, 1001100197, 1, '817-948-4302', 'A', 'yes'),
(79, 1001084560, 1, '987-097-4653', 'A', 'yes'),
(86, 1001084560, 1, '817-948-4304', 'A', 'no'),
(87, 1009874093, 1, '817-823-3972', 'A', 'yes'),
(88, 1001100199, 1, '817-823-3972', 'A', 'yes'),
(90, 1001084569, 1, '817-948-4309', 'A', 'yes'),
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

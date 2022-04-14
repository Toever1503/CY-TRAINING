-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 08, 2022 at 12:03 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pratice_2022_04_08`
--

-- --------------------------------------------------------

--
-- Table structure for table `administrators`
--

CREATE TABLE `administrators` (
  `id` bigint(20) NOT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `administrators`
--

INSERT INTO `administrators` (`id`, `fullname`, `password`, `username`) VALUES
(1, 'admin', '1234', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `avail_subjects`
--

CREATE TABLE `avail_subjects` (
  `id` bigint(20) NOT NULL,
  `start_date` date DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `adminstrator_id` bigint(20) DEFAULT NULL,
  `subject_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `avail_subjects`
--

INSERT INTO `avail_subjects` (`id`, `start_date`, `start_time`, `adminstrator_id`, `subject_id`) VALUES
(1, '2022-04-07', '11:47', 1, 1),
(2, '2022-03-29', '10:55', 1, 5),
(3, '2022-03-29', '10:55', 1, 2),
(4, '2022-04-05', '15:57', 1, 15),
(5, '2022-04-12', '19:03', 1, 10);

-- --------------------------------------------------------

--
-- Table structure for table `points`
--

CREATE TABLE `points` (
  `id` bigint(20) NOT NULL,
  `point` float DEFAULT NULL,
  `point_type` varchar(255) DEFAULT NULL,
  `adminstrator_id` bigint(20) DEFAULT NULL,
  `point_date` datetime DEFAULT NULL,
  `registered_subject_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `points`
--

INSERT INTO `points` (`id`, `point`, `point_type`, `adminstrator_id`, `point_date`, `registered_subject_id`) VALUES
(1, 8, 'GiuaKy', 1, '2022-04-08 16:03:53', 2),
(2, NULL, NULL, 1, NULL, 3),
(3, NULL, NULL, NULL, NULL, 4),
(4, 10, 'CuoiKy', 1, '2022-04-08 16:30:51', 5),
(5, 5, 'CuoiKy', 1, '2022-04-08 16:35:33', 6),
(6, NULL, NULL, NULL, NULL, 7);

-- --------------------------------------------------------

--
-- Table structure for table `registered_subjects`
--

CREATE TABLE `registered_subjects` (
  `id` bigint(20) NOT NULL,
  `avail_subject_id` bigint(20) DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  `registration_time` datetime DEFAULT NULL,
  `status` bit(1) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `registered_subjects`
--

INSERT INTO `registered_subjects` (`id`, `avail_subject_id`, `student_id`, `registration_time`, `status`) VALUES
(2, 1, 1, '2022-04-08 11:24:43', b'0'),
(3, 1, 2, '2022-04-08 14:13:40', b'1'),
(4, 2, 3, '2022-04-08 15:45:08', b'1'),
(5, 1, 4, '2022-04-08 15:47:14', b'1'),
(6, 3, 4, '2022-04-08 16:25:48', b'1'),
(7, 5, 4, '2022-04-08 16:25:49', b'1');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `id` bigint(20) NOT NULL,
  `student_phone` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `student_email` varchar(255) DEFAULT NULL,
  `student_name` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`id`, `student_phone`, `password`, `student_email`, `student_name`, `username`) VALUES
(1, '125151', '1234', 'sj@a.com', 'student 1', 'ph128'),
(2, '984912', '1234', 'fab.gha', 'student 2', 'ah128'),
(3, '51261', '1234', 'af@ga.f', 'student 3', 'sdn815'),
(4, '8613', '1234', 'as@ga.co', 'student 4', 'user');

-- --------------------------------------------------------

--
-- Table structure for table `subjects`
--

CREATE TABLE `subjects` (
  `id` bigint(20) NOT NULL,
  `subject_name` varchar(255) DEFAULT NULL,
  `adminstrator_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `subjects`
--

INSERT INTO `subjects` (`id`, `subject_name`, `adminstrator_id`) VALUES
(1, 'SPRING BOOT', 1),
(2, 'SPRING JPA', 1),
(3, 'SPRING HIBERNATE', 1),
(4, 'SPRING JDBC', 1),
(5, 'SPRING SECURITY', 1),
(6, 'JAVA WEB JSP', 1),
(7, 'JAVA WEB SERVLET', 1),
(8, 'JAVA WEB JSTL', 1),
(9, 'PHP BASIC', 1),
(10, 'PHP ADVANCED', 1),
(11, 'JAVA BASIC', 1),
(12, 'JAVA ADVANCED', 1),
(13, 'MAC LENIN', 1),
(14, 'SQL BASIC', 1),
(15, 'SQL ADVANCED', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `administrators`
--
ALTER TABLE `administrators`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `avail_subjects`
--
ALTER TABLE `avail_subjects`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK80ifiwiy5ub39fk8v146m37xt` (`adminstrator_id`),
  ADD KEY `FKeebnj158kt59y48ljufyd09wp` (`subject_id`);

--
-- Indexes for table `points`
--
ALTER TABLE `points`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKchk8cxiwxd0sgwdf5cjdxslew` (`adminstrator_id`),
  ADD KEY `FK6kflqd493wp845nupl417643b` (`registered_subject_id`);

--
-- Indexes for table `registered_subjects`
--
ALTER TABLE `registered_subjects`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKoayvejnqj85a5x8j3n9yvsdl2` (`avail_subject_id`),
  ADD KEY `FKpd8wohyfi6yljymiau5klqpsb` (`student_id`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subjects`
--
ALTER TABLE `subjects`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKfk3ofemiip1lf3fp3083upwbl` (`adminstrator_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `administrators`
--
ALTER TABLE `administrators`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `avail_subjects`
--
ALTER TABLE `avail_subjects`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `points`
--
ALTER TABLE `points`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `registered_subjects`
--
ALTER TABLE `registered_subjects`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `subjects`
--
ALTER TABLE `subjects`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

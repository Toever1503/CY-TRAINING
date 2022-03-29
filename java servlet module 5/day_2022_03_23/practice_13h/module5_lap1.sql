-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 25, 2022 at 11:10 AM
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
-- Database: `module5_lap1`
--

-- --------------------------------------------------------

--
-- Table structure for table `log`
--

CREATE TABLE `log` (
  `id` bigint(20) NOT NULL,
  `message` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `log`
--

INSERT INTO `log` (`id`, `message`) VALUES
(1, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(2, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(3, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(4, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(5, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(6, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(7, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(8, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(9, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(10, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(11, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(12, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(13, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(14, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(15, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(16, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(17, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(18, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(19, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(20, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(21, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(22, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(23, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(24, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(25, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(26, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(27, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(28, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(29, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(30, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(31, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(32, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(33, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(34, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(35, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(36, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(37, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(38, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(39, '0:0:0:0:0:0:0:1 is visiting product management page.'),
(40, 'User with ip 0:0:0:0:0:0:0:1 is visiting product management page.'),
(41, 'User with ip 0:0:0:0:0:0:0:1 is visiting product management page.'),
(42, 'User with ip 0:0:0:0:0:0:0:1 is visiting product management page.'),
(43, 'User with ip 0:0:0:0:0:0:0:1 is searching product.'),
(44, 'User with ip 0:0:0:0:0:0:0:1 is updating product.'),
(45, 'User with ip 0:0:0:0:0:0:0:1 is updating product.'),
(46, 'User with ip 0:0:0:0:0:0:0:1 is visiting product management page.'),
(47, 'User with ip 0:0:0:0:0:0:0:1 is updating product.'),
(48, 'User with ip 0:0:0:0:0:0:0:1 is updating product.'),
(49, 'User with ip 0:0:0:0:0:0:0:1 is visiting product management page.'),
(50, 'User with ip 0:0:0:0:0:0:0:1 is adding product.'),
(51, 'User with ip 0:0:0:0:0:0:0:1 is adding product.'),
(52, 'User with ip 0:0:0:0:0:0:0:1 is visiting product management page.'),
(53, 'User with ip 0:0:0:0:0:0:0:1 is searching product.'),
(54, 'Method GET, User with ip 0:0:0:0:0:0:0:1 is searching product.'),
(55, 'Method GET, User with ip 0:0:0:0:0:0:0:1 is visiting product management page.'),
(56, 'Method GET, User with ip 0:0:0:0:0:0:0:1 is visiting product management page.'),
(57, 'Method GET, User with ip 0:0:0:0:0:0:0:1 is visiting product management page.'),
(58, 'Method GET, User with ip 0:0:0:0:0:0:0:1 is searching product.'),
(59, 'Method GET, User with ip 0:0:0:0:0:0:0:1 is visiting product management page.'),
(60, 'Method GET, User with ip 0:0:0:0:0:0:0:1 is visiting product management page.');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `old_price` double DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `Material` varchar(255) DEFAULT NULL,
  `Style` varchar(255) DEFAULT NULL,
  `Season` varchar(255) DEFAULT NULL,
  `year` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `price`, `description`, `image`, `old_price`, `color`, `Material`, `Style`, `Season`, `year`) VALUES
(32, 'Solid Color Loose Irregular Shirt ...', 599, NULL, 'https://imgaz1.chiccdn.com/thumb/view/oaupload/newchic/images/E2/AA/8271a425-7a69-4386-99b8-68d8e5a4cb6f.jpg.webp?s=360x480', 777, 'Red', NULL, NULL, NULL, NULL),
(33, 'https://imgaz1.chiccdn.com/thumb/large/oaupload/newchic/images/78/01/c2055242-ebd2-4fd6-aed5-7f756f9beaa6.jpg?s=702x936', 599, NULL, 'https://imgaz1.chiccdn.com/thumb/view/oaupload/newchic/images/80/D0/aed3566f-cffc-4fff-aaea-ece9a5e1313f.jpg.webp?s=360x480', 777, 'Red', NULL, NULL, NULL, NULL),
(34, 'Solid Lace Button V-neck Blouse ...', 599, NULL, 'https://imgaz1.chiccdn.com/thumb/wap/oaupload/newchic/images/E4/9A/8d21fb67-35a2-4d38-a69f-8a463ee81474.jpg.webp?s=240x320', 777, 'Red', NULL, NULL, NULL, NULL),
(35, 'Solid Pocket Maxi Shirt ...', 599, NULL, 'https://imgaz1.chiccdn.com/thumb/view/oaupload/newchic/images/19/8B/53ae43c9-fa60-4672-88fd-dbc2f3b38675.jpg.webp?s=360x480', 777, 'Red', NULL, NULL, NULL, NULL),
(36, 'Paisley Ethnic Pattern Black Kimono ...', 599, NULL, 'https://imgaz1.chiccdn.com/thumb/wap/oaupload/newchic/images/EF/9C/2f37a919-8a74-452a-9683-2c43949f5fdd.jpg.webp?s=240x320', 777, 'Red', NULL, NULL, NULL, NULL),
(37, 'Solid Pocket Maxi Shirt ...', 599, NULL, 'https://imgaz1.chiccdn.com/thumb/view/oaupload/newchic/images/BD/54/c2ffde86-59d6-4a54-bae0-71a2c7381e8c.jpg.webp?s=360x480', 777, 'Red', NULL, NULL, NULL, NULL),
(38, 'Denim Contrast Stitching Kimono ...', 599, NULL, 'https://imgaz1.chiccdn.com/thumb/wap/oaupload/newchic/images/B4/D5/8e98a18f-b845-46ec-a1fa-f1013623eb5f.jpeg.webp?s=240x320', 777, 'Red', NULL, NULL, NULL, NULL),
(39, 'Solid Pocket Maxi Shirt ...', 599, NULL, 'https://imgaz1.chiccdn.com/thumb/wap/oaupload/newchic/images/B1/AF/03eb62e1-df83-4324-b064-379b84464fb2.jpg.webp?s=240x320', 777, 'Red', NULL, NULL, NULL, NULL),
(40, 'Mens Waffle Loungewear Set ...', 599, NULL, 'https://imgaz1.chiccdn.com/thumb/wap/oaupload/newchic/images/36/29/4dd53689-86d3-4400-90b6-f1283b703412.jpg.webp?s=240x320', 777, 'Red', NULL, NULL, NULL, NULL),
(41, 'Holiday Stripe Co-ords For ...', 599, NULL, 'https://imgaz1.chiccdn.com/thumb/wap/oaupload/newchic/images/B0/B8/7a73b63b-d167-49c8-96fb-bc70f3a56b53.jpg.webp?s=240x320', 777, 'Red', NULL, NULL, NULL, NULL),
(42, 'Contrast Patchwork Hooded Sports C ...', 599, NULL, 'https://imgaz1.chiccdn.com/thumb/wap/oaupload/newchic/images/DD/DF/7d140c1a-b890-42e7-addb-dd1cf5624c33.jpg.webp?s=240x320', 777, 'Red', NULL, NULL, NULL, NULL),
(43, 'https://imgaz1.chiccdn.com/thumb/wap/oaupload/newchic/images/AA/50/d0b25bf7-3263-4f4d-898a-9a952fe17864.jpg.webp?s=240x320', 599, NULL, 'Anime Elements Rectangular Neckl ...', 777, 'Red', NULL, NULL, NULL, NULL),
(44, 'Plus Size Graffiti Striped Shirt', 599, NULL, 'https://imgaz1.chiccdn.com/os/202203/20220315012737_420.jpg.webp', 777, 'Red', NULL, NULL, NULL, NULL),
(45, 'Japanese Cat Print Hoodies', 599, NULL, 'https://imgaz1.chiccdn.com/thumb/view/oaupload/newchic/images/AA/39/26a725bb-806e-490f-8f32-dd6e0d22ee0f.jpg?s=360x480', 777, 'Red', NULL, NULL, NULL, NULL),
(46, 'shiki', 412412, '24112', 'http://localhost:8080/practice_13h/upload/1.png', NULL, 'kmmfka', 'faskm', 'afmas', 'asfm', 1421),
(47, 'san', 12, 'fasfa', 'http://localhost:8080/practice_13h/upload/ichigo kurosaki.jpg', NULL, 'fasf', 'faskmf', 'safsaf', 'safa', 1421);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `id` bigint(20) NOT NULL,
  `Roles` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `id`, `Roles`) VALUES
('user', '1234', 1, NULL),
('user1', '1234', 2, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `log`
--
ALTER TABLE `log`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `log`
--
ALTER TABLE `log`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

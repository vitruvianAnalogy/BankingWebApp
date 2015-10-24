-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 06, 2014 at 06:51 PM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `safemoneycorp`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE IF NOT EXISTS `account` (
  `account_no` bigint(10) NOT NULL,
  `member_id` int(11) NOT NULL,
  `amount` double DEFAULT NULL,
  `is_active` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`account_no`),
  KEY `accountAndUser` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE IF NOT EXISTS `login` (
  `member_id` int(11) NOT NULL,
  `user_name` varchar(15) NOT NULL,
  `password` varchar(80) NOT NULL,
  `site_key` varchar(20) NOT NULL,
  `isAccountNonLocked` tinyint(1) NOT NULL,
  `isEnabled` tinyint(1) NOT NULL,
  `failedAttemptCount` int(11) NOT NULL,
  `lastLoginDate` date DEFAULT NULL,
  `otp` bigint(12) DEFAULT NULL,
  `otp_date` datetime DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `user_name` (`user_name`),
  KEY `memberid` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`member_id`, `user_name`, `password`, `site_key`, `isAccountNonLocked`, `isEnabled`, `failedAttemptCount`, `lastLoginDate`, `otp`, `otp_date`) VALUES
(3357383, 'obuli', '$2a$10$/f5x2uhTlKxQgT0OiSSk9OSGfpDHTWtdG.LR/pGC3iOJvqAMG7.B6', 'halo1', 1, 0, 0, NULL, 0, NULL),
(3358190, 'nishant', '$2a$10$Wlrc3I6xQy1Srbk3Hgb15OsjtVFC/l.Q/EANkfWeq.5GnI6ASYj/q', 'really', 1, 0, 0, NULL, 0, NULL),
(3758157, 'moksha', '$2a$10$6YnYxg0x3.Y/E8hQjOvqqe0pxK2PBZsv.H9TxXYA6pTJLwv4aPe6S', 'hello', 1, 1, 0, NULL, 0, NULL),
(3946164, 'prachi', '$2a$10$KhFJ9LV/pyOJLCr55uNhfOUVZ7q4Rt0QDKDSLRjlbU7L3SZ/FYCMW', 'halo3', 1, 0, 0, NULL, 0, NULL),
(4040894, 'jimit', 'siddhi23', 'cooldude', 1, 1, 0, NULL, 0, NULL),
(4187938, 'sagar', '$2a$10$E0JbeSav5vOA8ocol54U0O/AV7jFGnfaV9EGmk9ovKSJg0kHsxCE2', 'forever', 1, 0, 0, NULL, 0, NULL),
(4230864, 'priyank', '$2a$10$K/koQTG/cxWO0FapeXG4.O1evlzsClOJHC2DUAhZKBNweXsMDZy56', 'helios', 1, 0, 0, NULL, 0, NULL),
(5122111, 'hiten', '$2a$10$eIbjO8sQI6SaHGRbCryewud32Wvo1uaXBDnkkQJqQu2i2FglbMm6u', 'hellboy', 1, 0, 0, NULL, 0, NULL),
(6074860, 'chintan', 'lovesjigna', 'jigudi', 1, 1, 0, NULL, 0, NULL),
(6257441, 'jigna', '$2a$10$QyNquv0ktjoPah4ytSljt.OPO2T.Qrm8dqEDhgb.emmBLburG3mY6', 'chintan', 1, 0, 0, NULL, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `payment_request`
--

CREATE TABLE IF NOT EXISTS `payment_request` (
  `payment_id` bigint(12) NOT NULL,
  `merchant_account_id` bigint(10) NOT NULL,
  `merchant_member_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `amount` double NOT NULL,
  `merchant_first_name` varchar(25) NOT NULL,
  `merchant_last_name` varchar(25) NOT NULL,
  `authorizer_member_id` int(11) NOT NULL,
  `authorizer_account_id` bigint(10) NOT NULL,
  `status` varchar(15) NOT NULL,
  `description` varchar(300) NOT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `memberId` (`merchant_member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `request`
--

CREATE TABLE IF NOT EXISTS `request` (
  `request_id` bigint(10) NOT NULL,
  `member_id` int(11) NOT NULL,
  `request_type` varchar(45) DEFAULT NULL,
  `authorizing_member_id` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `authorizing_authority` varchar(20) NOT NULL,
  `authority_user_type_id` int(11) NOT NULL,
  `request_date` date NOT NULL,
  `processed_date` date DEFAULT NULL,
  PRIMARY KEY (`request_id`),
  KEY `request_user` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `request`
--

INSERT INTO `request` (`request_id`, `member_id`, `request_type`, `authorizing_member_id`, `status`, `authorizing_authority`, `authority_user_type_id`, `request_date`, `processed_date`) VALUES
(3797799, 4187938, 'CREATE_ACCOUNT', NULL, 'NEW', 'ADMIN', 123, '2014-11-06', NULL),
(3913556, 5122111, 'CREATE_ACCOUNT', NULL, 'NEW', 'ADMIN', 123, '2014-11-06', NULL),
(3941669, 3357383, 'CREATE_ACCOUNT', NULL, 'NEW', 'ADMIN', 123, '2014-11-06', NULL),
(4053008, 6074860, 'CREATE_ACCOUNT', NULL, 'NEW', 'ADMIN', 123, '2014-11-06', NULL),
(4114501, 3946164, 'CREATE_ACCOUNT', NULL, 'NEW', 'ADMIN', 123, '2014-11-06', NULL),
(5246390, 4040894, 'CREATE_ACCOUNT', NULL, 'NEW', 'ADMIN', 123, '2014-11-06', NULL),
(5274982, 3358190, 'CREATE_ACCOUNT', NULL, 'NEW', 'ADMIN', 123, '2014-11-06', NULL),
(5295876, 6257441, 'CREATE_ACCOUNT', NULL, 'NEW', 'ADMIN', 123, '2014-11-06', NULL),
(5770944, 4230864, 'CREATE_ACCOUNT', NULL, 'NEW', 'ADMIN', 123, '2014-11-06', NULL),
(5815313, 3758157, 'CREATE_ACCOUNT', NULL, 'NEW', 'ADMIN', 123, '2014-11-06', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE IF NOT EXISTS `transaction` (
  `transaction_id` bigint(10) NOT NULL,
  `member_id` int(11) NOT NULL,
  `from_account` bigint(10) DEFAULT NULL,
  `to_account` bigint(10) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `transaction_type` varchar(45) DEFAULT NULL,
  `is_critical` tinyint(1) DEFAULT NULL,
  `is_authorized` tinyint(1) DEFAULT NULL,
  `processed_date` date DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `transaction_member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `transaction_review`
--

CREATE TABLE IF NOT EXISTS `transaction_review` (
  `transaction_review_id` bigint(12) NOT NULL,
  `cust_member_id` int(11) NOT NULL,
  `transaction_id` bigint(12) NOT NULL,
  `from_account` bigint(12) NOT NULL,
  `to_account` bigint(12) NOT NULL,
  `amount` double NOT NULL,
  `transaction_type` varchar(45) NOT NULL,
  `status` varchar(15) NOT NULL,
  `authorizing_authority_id` int(11) NOT NULL,
  `authorizing_member_id` int(11) DEFAULT NULL,
  `authorizing_authority_type` varchar(15) NOT NULL,
  `request_date` date NOT NULL,
  `processed_date` date DEFAULT NULL,
  `review_type` varchar(15) NOT NULL,
  PRIMARY KEY (`transaction_review_id`),
  KEY `member_id_review` (`cust_member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `member_id` int(11) NOT NULL,
  `first_name` varchar(25) DEFAULT NULL,
  `last_name` varchar(25) DEFAULT NULL,
  `email_id` varchar(30) NOT NULL,
  `contact_no` bigint(10) NOT NULL,
  `address_1` varchar(50) NOT NULL,
  `address_2` varchar(50) DEFAULT NULL,
  `city` varchar(15) NOT NULL,
  `state` varchar(2) NOT NULL,
  `zip` bigint(5) NOT NULL,
  `ssn` bigint(9) DEFAULT NULL,
  `sec_question_1` varchar(200) NOT NULL,
  `sec_question_2` varchar(200) NOT NULL,
  `sec_question_3` varchar(200) NOT NULL,
  `sec_answer_1` varchar(25) NOT NULL,
  `sec_answer_2` varchar(25) NOT NULL,
  `sec_answer_3` varchar(25) NOT NULL,
  `date_of_birth` date NOT NULL,
  `age` int(3) DEFAULT NULL,
  `isCustomer` varchar(5) NOT NULL,
  `user_type_id` int(11) NOT NULL,
  `created_by` varchar(10) NOT NULL,
  `created_date` date NOT NULL,
  `expiry_date` date NOT NULL,
  `is_active` varchar(5) NOT NULL,
  `isEmployee` tinyint(1) DEFAULT NULL,
  `designation` varchar(25) DEFAULT NULL,
  `isPIIAuthorized` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `email_id` (`email_id`),
  KEY `member_id` (`member_id`),
  KEY `user_type_idx` (`user_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`member_id`, `first_name`, `last_name`, `email_id`, `contact_no`, `address_1`, `address_2`, `city`, `state`, `zip`, `ssn`, `sec_question_1`, `sec_question_2`, `sec_question_3`, `sec_answer_1`, `sec_answer_2`, `sec_answer_3`, `date_of_birth`, `age`, `isCustomer`, `user_type_id`, `created_by`, `created_date`, `expiry_date`, `is_active`, `isEmployee`, `designation`, `isPIIAuthorized`) VALUES
(3357383, 'Obuli', 'Karthik', 'obuli_karthik@gmail.com', 6875496354, '636843 nefkd', 'kden', 'SanJose', 'Ca', 78943, 952462399, 'name?', 'height?', 'weight?', 'george', 'five', '65', '2014-11-04', 21, 'true', 322, 'SYSTEM', '2014-11-06', '2015-11-06', 'true', 0, NULL, NULL),
(3358190, 'Nishant', 'Rawat', 'nishant_rawat45@gmail.com', 3772252198, '2368nkdkn', 'lneln', 'baroda', 'gu', 62349, 849525638, 'name?', 'height?', 'weight?', 'rawat', '8', '91', '2014-11-01', 24, 'false', 366, 'SYSTEM', '2014-11-06', '2015-11-06', 'true', 0, NULL, NULL),
(3758157, 'Moksha', 'Doshi', 'doshimoksha@rediffmail.com', 6144403425, '1265 E. University Dr.', '1029', 'Tempe', 'Ar', 85281, 123456789, 'name?', 'height?', 'weight?', 'Moksha', '5', '50', '1992-01-23', 22, 'true', 123, 'SYSTEM', '2014-11-06', '2015-11-06', 'true', 0, NULL, NULL),
(3946164, 'Prachi', 'Shah', 'prachi_shah22@gmail.com', 4799261719, '4375 hjwb', 'kjjdk', 'Dallas', 'TX', 43668, 794742569, 'name?', 'height?', 'weight?', 'niket', 'four', '49', '1992-01-24', 22, 'true', 322, 'SYSTEM', '2014-11-06', '2015-11-06', 'true', 0, NULL, NULL),
(4040894, 'Jimit', 'Doshi', 'jimit_facebook@gmail.com', 9925758983, '479ndkjn', 'lnwefk', 'abd', 'gu', 68345, 943267297, 'name?', 'height?', 'weight?', 'sids', '5.3', 'three', '1994-05-25', 19, 'false', 123, 'ADMIN', '2014-11-06', '2015-11-06', 'true', 1, 'Cashier', NULL),
(4187938, 'Sagar', 'Reddy', 'sagar_reddy34@gmail.com', 8459739830, 'hfn hkehkn', 'kfke', 'florida', 'fx', 44643, 789353869, 'name?', 'height?', 'weight?', 'hritik', '10', '69', '2014-11-03', 24, 'true', 322, 'SYSTEM', '2014-11-06', '2015-11-06', 'true', 0, NULL, NULL),
(4230864, 'Priyank', 'Sharma', 'omega_5330@yahoo.com', 6328627357, '124537 wdkj', '', 'Tempe', 'Ar', 85281, 874820215, 'name?', 'height?', 'weight?', 'Priyank', '6', '79', '2014-11-04', 24, 'true', 322, 'SYSTEM', '2014-11-06', '2015-11-06', 'true', 0, NULL, NULL),
(5122111, 'Hiten', 'Doshi', 'hiten_doshi55@gmail.com', 9825039061, 'amrapali', 'towers', 'abad', 'Gu', 38007, 354281793, 'name?', 'height?', 'weight?', 'jimit', '90', '6', '2014-11-04', 56, 'false', 366, 'SYSTEM', '2014-11-06', '2015-11-06', 'true', 0, NULL, NULL),
(6074860, 'Chintan', 'Prajapati', 'cvprajap@gmail.com', 9032737266, '63hkfjd', 'elk', 'tempe', 'se', 87897, 687943398, 'name?', 'height?', 'weight?', 'chiku', '6.7', '23', '2007-11-08', 52, 'false', 123, 'ADMIN', '2014-11-06', '2015-11-06', 'true', 1, 'Manager', NULL),
(6257441, 'Jigna', 'Modi', 'jigna_modi420@gmail.com', 6435709802, '3279hfwkj', 'kfdkn', 'Tempe', 'Ar', 85281, 803883280, 'name?', 'height?', 'weight?', 'jiggs', '5.6', '90', '2014-10-14', 21, 'true', 322, 'SYSTEM', '2014-11-06', '2015-11-06', 'true', 0, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_type`
--

CREATE TABLE IF NOT EXISTS `user_type` (
  `user_type_id` int(11) NOT NULL,
  `user_type` varchar(15) NOT NULL,
  `description` varchar(50) NOT NULL,
  `is_active` varchar(5) NOT NULL,
  PRIMARY KEY (`user_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_type`
--

INSERT INTO `user_type` (`user_type_id`, `user_type`, `description`, `is_active`) VALUES
(123, 'INT_BANK_ADMIN', 'Internal User - BanK Admin', 'true'),
(125, 'INT_BANK_EMP', 'Internal User - Bank Employee', 'true'),
(322, 'EXT_IND_CUST', 'External User - Individual Customer', 'true'),
(366, 'EXT_MERCHANT', 'External User - Merchant / Organization', 'true');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `memberId` FOREIGN KEY (`member_id`) REFERENCES `user` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `login`
--
ALTER TABLE `login`
  ADD CONSTRAINT `login_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `user` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `payment_request`
--
ALTER TABLE `payment_request`
  ADD CONSTRAINT `payment_request_ibfk_1` FOREIGN KEY (`merchant_member_id`) REFERENCES `user` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `request`
--
ALTER TABLE `request`
  ADD CONSTRAINT `requestMemberId` FOREIGN KEY (`member_id`) REFERENCES `user` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `transactionMemberId` FOREIGN KEY (`member_id`) REFERENCES `user` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `transaction_review`
--
ALTER TABLE `transaction_review`
  ADD CONSTRAINT `transaction_review_ibfk_1` FOREIGN KEY (`cust_member_id`) REFERENCES `user` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`user_type_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

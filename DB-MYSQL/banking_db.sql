-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 16, 2021 at 11:40 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `banking_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `audit_events`
--

CREATE TABLE `audit_events` (
  `audit_Id` int(11) NOT NULL,
  `api_Name` varchar(100) NOT NULL,
  `Param_Value` varchar(100) NOT NULL,
  `date_Time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `audit_events`
--

INSERT INTO `audit_events` (`audit_Id`, `api_Name`, `Param_Value`, `date_Time`) VALUES
(8, '/getAccountBalanceByAccountNo/{accountNo}', 'accountNo = 147852369', '2021-05-14 19:34:48'),
(9, '/getAccountBalanceByAccountNo/{accountNo}', 'accountNo = 456789456', '2021-05-14 23:53:37'),
(10, '/getAccountBalanceByAccountNo/{accountNo}', 'accountNo= 985687542', '2021-05-15 00:09:56'),
(12, '/getAccountBalanceByAccountNo/{accountNo}', '{accountNo = =985687542}', '2021-05-15 01:21:48'),
(13, '/makeFundTransferToOwnAccount', '{depositedAmount = =4846.5, accountNo = =985687542}', '2021-05-15 01:24:42'),
(14, '/getAccountBalanceByAccountNo/{accountNo}', '{accountNo =985687542}', '2021-05-15 01:26:12'),
(15, '/makeFundTransferToOwnAccount', '{depositedAmount = =100.0, accountNo = =123456789}', '2021-05-15 14:30:44'),
(16, '/makeFundTransferToOwnAccount', '{depositedAmount = =100.0, accountNo = =123456789}', '2021-05-15 14:31:44'),
(17, '/makeFundTransferToOwnAccount', '{depositedAmount = =100.0, accountNo = =123456789}', '2021-05-15 14:36:21'),
(18, '/getAccountBalanceByAccountNo/{accountNo}', '123456789', '2021-05-15 14:52:08'),
(19, '/getAccountBalanceByAccountNo/{accountNo}', '123456789', '2021-05-15 18:28:24'),
(20, '/getAccountBalanceByAccountNo/{accountNo}', '123456789', '2021-05-15 18:31:01'),
(21, '/getAccountBalanceByAccountNo/{accountNo}', '123456789', '2021-05-15 18:33:53'),
(22, '/getAccountBalanceByAccountNo/{accountNo}', '147852369', '2021-05-15 18:34:49'),
(23, '/getAccountBalanceByAccountNo/{accountNo}', '147852369', '2021-05-15 18:35:49');

-- --------------------------------------------------------

--
-- Table structure for table `transaction_details`
--

CREATE TABLE `transaction_details` (
  `td_Id` int(11) NOT NULL,
  `sender_Acct_No` int(13) NOT NULL,
  `receiver_Acct_No` int(13) NOT NULL,
  `transferred_Amt` decimal(10,0) NOT NULL,
  `date_Time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaction_details`
--

INSERT INTO `transaction_details` (`td_Id`, `sender_Acct_No`, `receiver_Acct_No`, `transferred_Amt`, `date_Time`) VALUES
(1, 456789456, 985687542, '1500', '2021-05-02 18:20:19'),
(2, 147852369, 147852369, '1000', '2021-05-03 18:20:19'),
(3, 123456789, 985687542, '1500', '2021-05-10 18:22:05'),
(4, 147852369, 985687542, '2500', '2021-05-11 18:22:05');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_Id` varchar(13) NOT NULL,
  `user_Name` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_Id`, `user_Name`, `email`) VALUES
('111', 'Sam', 'sam@gmail.com'),
('222', 'Andrew', 'andrw@gmail.com'),
('333', 'Rensy', 'ren@gmail'),
('444', 'nusdush', 'simbah@gmail.com'),
('555', 'Akila', 'akila@gmail');

-- --------------------------------------------------------

--
-- Table structure for table `user_account`
--

CREATE TABLE `user_account` (
  `user_Id` varchar(13) NOT NULL,
  `account_No` int(10) NOT NULL,
  `balance` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_account`
--

INSERT INTO `user_account` (`user_Id`, `account_No`, `balance`) VALUES
('111', 123456789, '85000'),
('333', 147852369, '45000'),
('111', 456789456, '20000'),
('222', 789789789, '32000'),
('444', 985687542, '55500');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `audit_events`
--
ALTER TABLE `audit_events`
  ADD PRIMARY KEY (`audit_Id`);

--
-- Indexes for table `transaction_details`
--
ALTER TABLE `transaction_details`
  ADD PRIMARY KEY (`td_Id`),
  ADD KEY `sender_Acct_No` (`sender_Acct_No`),
  ADD KEY `receiver_Acct_No` (`receiver_Acct_No`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_Id`);

--
-- Indexes for table `user_account`
--
ALTER TABLE `user_account`
  ADD PRIMARY KEY (`account_No`),
  ADD KEY `user_Id` (`user_Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `audit_events`
--
ALTER TABLE `audit_events`
  MODIFY `audit_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `transaction_details`
--
ALTER TABLE `transaction_details`
  MODIFY `td_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaction_details`
--
ALTER TABLE `transaction_details`
  ADD CONSTRAINT `transaction_details_ibfk_1` FOREIGN KEY (`sender_Acct_No`) REFERENCES `user_account` (`account_No`),
  ADD CONSTRAINT `transaction_details_ibfk_2` FOREIGN KEY (`receiver_Acct_No`) REFERENCES `user_account` (`account_No`);

--
-- Constraints for table `user_account`
--
ALTER TABLE `user_account`
  ADD CONSTRAINT `user_account_ibfk_1` FOREIGN KEY (`user_Id`) REFERENCES `user` (`user_Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

CREATE DATABASE  IF NOT EXISTS `sse` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `sse`;
-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: sse
-- ------------------------------------------------------
-- Server version	5.7.26-0ubuntu0.19.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Credit_Card`
--

DROP TABLE IF EXISTS `Credit_Card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Credit_Card` (
  `Card_Number` varchar(280) COLLATE utf8_bin NOT NULL,
  `MemberID` int(11) NOT NULL,
  `CVV` int(3) NOT NULL,
  `ExpiryDate` date NOT NULL,
  PRIMARY KEY (`Card_Number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Credit_Card`
--

LOCK TABLES `Credit_Card` WRITE;
/*!40000 ALTER TABLE `Credit_Card` DISABLE KEYS */;
INSERT INTO `Credit_Card` VALUES ('1234567788912345',30,188,'2019-04-29'),('1234567812345678',31,123,'2019-04-30'),('1234567890123456',29,123,'2019-04-30'),('1234567891234123',32,123,'2019-05-14'),('1234567899999922',29,111,'2019-04-30');
/*!40000 ALTER TABLE `Credit_Card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Guest`
--

DROP TABLE IF EXISTS `Guest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Guest` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Guest_Name` varchar(128) COLLATE utf8_bin NOT NULL,
  `Guest_Surname` varchar(128) COLLATE utf8_bin NOT NULL,
  `Address` varchar(280) COLLATE utf8_bin NOT NULL,
  `Card_Number` varchar(45) COLLATE utf8_bin NOT NULL,
  `Phone_Number` int(15) NOT NULL,
  `Email_Address` varchar(128) COLLATE utf8_bin NOT NULL,
  `CVV` int(3) NOT NULL,
  `ExpiryDate` date NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Guest`
--

LOCK TABLES `Guest` WRITE;
/*!40000 ALTER TABLE `Guest` DISABLE KEYS */;
INSERT INTO `Guest` VALUES (8,'Abdulrahman Salim','Akinyede','43 Parslickstown Court','1234567890123456',852777322,'Abdulrahman.salim@ucdconnect.ie',123,'2019-04-29'),(44,'t','t','th','1111111111111111',834206066,'t@t.t',123,'2019-04-30'),(45,'Thomas Anderson','Test','36 Crannagh Castle','1234567812345678',834206066,'thomas.anderson@ucdconnect.ie',123,'2019-05-02'),(46,'Tom','Tom','Tom','1234567812345678',1234,'Tom@Tom.com',123,'2019-05-21');
/*!40000 ALTER TABLE `Guest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Members`
--

DROP TABLE IF EXISTS `Members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Members` (
  `User_Name` varchar(45) NOT NULL,
  `User_Password` varchar(280) NOT NULL,
  PRIMARY KEY (`User_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Members`
--

LOCK TABLES `Members` WRITE;
/*!40000 ALTER TABLE `Members` DISABLE KEYS */;
INSERT INTO `Members` VALUES ('t','t'),('tom','tom');
/*!40000 ALTER TABLE `Members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reservations`
--

DROP TABLE IF EXISTS `Reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reservations` (
  `Reservation_Id` bigint(20) NOT NULL COMMENT '\\n',
  `GuestID` int(11) NOT NULL,
  `start` date NOT NULL,
  `end` date NOT NULL,
  `duration` int(11) DEFAULT NULL,
  `numberRooms` int(3) NOT NULL,
  `status` varchar(45) COLLATE utf8_bin NOT NULL,
  `bookingDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `reservationType` varchar(45) COLLATE utf8_bin NOT NULL,
  `price` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`Reservation_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reservations`
--

LOCK TABLES `Reservations` WRITE;
/*!40000 ALTER TABLE `Reservations` DISABLE KEYS */;
INSERT INTO `Reservations` VALUES (1018639452708551,32,'2019-05-15','2019-05-22',NULL,1,'Active','2019-05-01 22:06:07','Member','3150.0'),(5482819089468110,45,'2019-05-01','2019-05-16',NULL,2,'Active','2019-05-01 21:47:48','Guest','3600.0'),(10678326152847750,46,'2019-05-02','2019-05-04',NULL,2,'Active','2019-05-02 15:49:16','Guest','480.0'),(108531092716783030,32,'2019-05-15','2019-05-21',NULL,4,'Active','2019-05-01 22:05:19','Member','2592.0'),(1012910412054661571,44,'2019-05-01','2019-05-02',NULL,2,'Active','2019-04-30 11:53:20','Guest','240.0');
/*!40000 ALTER TABLE `Reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reserved_Rooms`
--

DROP TABLE IF EXISTS `Reserved_Rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reserved_Rooms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roomNumber` varchar(45) COLLATE utf8_bin NOT NULL,
  `reservationID` varchar(45) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reserved_Rooms`
--

LOCK TABLES `Reserved_Rooms` WRITE;
/*!40000 ALTER TABLE `Reserved_Rooms` DISABLE KEYS */;
INSERT INTO `Reserved_Rooms` VALUES (1,'14b','1012910412054661571'),(2,'15b','1012910412054661571'),(3,'16b','5482819089468110'),(4,'17b','5482819089468110'),(5,'1a','108531092716783030'),(6,'20c','108531092716783030'),(7,'23b','108531092716783030'),(8,'2a','108531092716783030'),(9,'8a','1018639452708551'),(10,'13b','10678326152847750'),(11,'16b','10678326152847750');
/*!40000 ALTER TABLE `Reserved_Rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Room`
--

DROP TABLE IF EXISTS `Room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Room` (
  `Room_Number` varchar(10) COLLATE utf8_bin NOT NULL,
  `capacity` int(11) DEFAULT NULL,
  `price` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`Room_Number`),
  UNIQUE KEY `Room_Number` (`Room_Number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Room`
--

LOCK TABLES `Room` WRITE;
/*!40000 ALTER TABLE `Room` DISABLE KEYS */;
INSERT INTO `Room` VALUES ('10b',2,'120'),('11b',2,'120'),('13b',2,'120'),('14b',2,'120'),('15b',2,'120'),('16b',2,'120'),('17b',2,'120'),('18b',4,'120'),('19b',4,'120'),('1a',4,'120'),('20c',4,'120'),('23b',4,'120'),('2a',4,'120'),('3a',4,'300'),('4a',10,'300'),('5a',10,'300'),('6a',10,'300'),('7a',10,'500'),('8a',2,'500'),('9a',2,'300');
/*!40000 ALTER TABLE `Room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Starwood`
--

DROP TABLE IF EXISTS `Starwood`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Starwood` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Member_Name` varchar(128) COLLATE utf8_bin NOT NULL,
  `Member_Surname` varchar(128) COLLATE utf8_bin NOT NULL,
  `User_Name` varchar(128) COLLATE utf8_bin NOT NULL,
  `Address` varchar(280) COLLATE utf8_bin NOT NULL,
  `Card_Number` varchar(280) COLLATE utf8_bin NOT NULL,
  `Phone_Number` int(11) NOT NULL,
  `Email_Address` varchar(128) COLLATE utf8_bin NOT NULL,
  `CVV` int(3) NOT NULL,
  `ExpiryDate` date NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `User_Name_UNIQUE` (`User_Name`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Starwood`
--

LOCK TABLES `Starwood` WRITE;
/*!40000 ALTER TABLE `Starwood` DISABLE KEYS */;
INSERT INTO `Starwood` VALUES (30,'Abdulrahman Salim','a','b','43 Parslickstown Court','1234567788912345',852777322,'Abdulrahman.salim@ucdconnect.ie',188,'2019-04-29'),(32,'t','t','tom','t','1234567812345678',1234,'t@t.com',234,'2019-05-16'),(33,'t','t','t','t','1234567812345678',123,'t@t.com',123,'2019-05-22');
/*!40000 ALTER TABLE `Starwood` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-02 21:02:49

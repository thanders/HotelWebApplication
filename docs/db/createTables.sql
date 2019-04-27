CREATE DATABASE  IF NOT EXISTS `sse` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `sse`;
-- MySQL dump 10.13  Distrib 8.0.13, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: sse
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
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
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Credit_Card` (
  `Card_Number` int(16) NOT NULL,
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
/*!40000 ALTER TABLE `Credit_Card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Guest`
--

DROP TABLE IF EXISTS `Guest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Guest` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Guest_Name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Guest_Surname` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Address` varchar(280) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Card_Number` int(19) NOT NULL,
  `Phone_Number` int(15) NOT NULL,
  `Email_Address` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `CVV` int(3) NOT NULL,
  `ExpiryDate` date NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Guest`
--

LOCK TABLES `Guest` WRITE;
/*!40000 ALTER TABLE `Guest` DISABLE KEYS */;
/*!40000 ALTER TABLE `Guest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Members`
--

DROP TABLE IF EXISTS `Members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Members` (
  `User_Name` varchar(45) NOT NULL,
  `User_Password` varchar(45) NOT NULL,
  PRIMARY KEY (`User_Name`),
  UNIQUE KEY `Username_UNIQUE` (`User_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Members`
--

LOCK TABLES `Members` WRITE;
/*!40000 ALTER TABLE `Members` DISABLE KEYS */;
/*!40000 ALTER TABLE `Members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reservations`
--

DROP TABLE IF EXISTS `Reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Reservations` (
  `Reservation_Id` int(11) NOT NULL AUTO_INCREMENT,
  `GuestID` int(11) NOT NULL,
  `start` date NOT NULL,
  `end` date NOT NULL,
  `duration` int(11) DEFAULT NULL,
  `numberRooms` int(3) NOT NULL,
  `status` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `bookingDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `reservationType` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `price` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`Reservation_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reservations`
--

LOCK TABLES `Reservations` WRITE;
/*!40000 ALTER TABLE `Reservations` DISABLE KEYS */;
/*!40000 ALTER TABLE `Reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reserved_Rooms`
--

DROP TABLE IF EXISTS `Reserved_Rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Reserved_Rooms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roomNumber` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `reservationID` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reserved_Rooms`
--

LOCK TABLES `Reserved_Rooms` WRITE;
/*!40000 ALTER TABLE `Reserved_Rooms` DISABLE KEYS */;
INSERT INTO `Reserved_Rooms` VALUES (1,'2a','75'),(2,'3a','75'),(3,'2a','76'),(4,'3a','76'),(5,'2a','77'),(6,'3a','77'),(7,'10b','78'),(8,'10b','79'),(9,'10b','80'),(10,'10b','81'),(11,'11b','82'),(12,'3a','83'),(13,'19b','84'),(14,'15b','85'),(15,'19b','85'),(16,'11b','87'),(17,'13b','87'),(18,'14b','88'),(19,'16b','88'),(20,'17b','88'),(21,'1a','89');
/*!40000 ALTER TABLE `Reserved_Rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Room`
--

DROP TABLE IF EXISTS `Room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Room` (
  `Room_Number` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `capacity` int(11) DEFAULT NULL,
  `price` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`Room_Number`),
  UNIQUE KEY `Room_Number` (`Room_Number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Room`
--

LOCK TABLES `Room` WRITE;
/*!40000 ALTER TABLE `Room` DISABLE KEYS */;
INSERT INTO `Room` VALUES ('10b',2,'120'),('11b',2,'120'),('13b',2,'120'),('14b',2,'120'),('15b',2,'120'),('16b',2,'120'),('17b',2,'120'),('18b',4,'120'),('19b',4,'120'),('1a',4,'120'),('20c',4,'120'),('23b',4,'120'),('2a',4,'120'),('3a',4,'300'),('4a',10,'300'),('5a',10,'300'),('6a',10,'300'),('7a',10,'10000'),('8a',2,'10000'),('9a',2,'300');
/*!40000 ALTER TABLE `Room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Starwood`
--

DROP TABLE IF EXISTS `Starwood`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Starwood` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Member_Name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Member_Surname` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `User_Name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Address` varchar(280) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Card_Number` int(16) NOT NULL,
  `Phone_Number` int(11) NOT NULL,
  `Email_Address` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `CVV` int(3) NOT NULL,
  `ExpiryDate` date NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Starwood`
--

LOCK TABLES `Starwood` WRITE;
/*!40000 ALTER TABLE `Starwood` DISABLE KEYS */;
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

-- Dump completed on 2019-04-27 22:15:24

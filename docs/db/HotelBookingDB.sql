-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: sse
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.17.10.1

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
  `Card_Number` int(11) NOT NULL,
  `Card_Owner` varchar(128) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`Card_Number`),
  UNIQUE KEY `Card_Number` (`Card_Number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Credit_Card`
--

LOCK TABLES `Credit_Card` WRITE;
/*!40000 ALTER TABLE `Credit_Card` DISABLE KEYS */;
INSERT INTO `Credit_Card` VALUES (1233232,'Abdul');
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
  `Card_Number` int(11) NOT NULL,
  `Phone_Number` int(11) NOT NULL,
  `Email_Address` varchar(128) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Card_Number` (`Card_Number`),
  UNIQUE KEY `Phone_Number` (`Phone_Number`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Guest`
--

LOCK TABLES `Guest` WRITE;
/*!40000 ALTER TABLE `Guest` DISABLE KEYS */;
INSERT INTO `Guest` VALUES (1,'Abdul','Salim','Dublin 5',1233232,852222432,'Abdulj947@gmail');
/*!40000 ALTER TABLE `Guest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reservations`
--

DROP TABLE IF EXISTS `Reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reservations` (
  `Reservation_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Reserved_By` varchar(128) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`Reservation_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reservations`
--

LOCK TABLES `Reservations` WRITE;
/*!40000 ALTER TABLE `Reservations` DISABLE KEYS */;
INSERT INTO `Reservations` VALUES (1,'Abdul'),(2,'Tom');
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
  `Number_of_Rooms` int(11) NOT NULL,
  `FK_ReservationID` int(11) NOT NULL,
  `Room_Type_ID` int(11) NOT NULL,
  `Status` varchar(45) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ReservationID_idx` (`FK_ReservationID`),
  KEY `Room_Type_ID_idx` (`Room_Type_ID`),
  CONSTRAINT `Room_Type_ID` FOREIGN KEY (`Room_Type_ID`) REFERENCES `Room_Type` (`room_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ReservationID` FOREIGN KEY (`FK_ReservationID`) REFERENCES `Reservations` (`Reservation_Id`) ON DELETE cascade ON UPDATE restrict
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reserved_Rooms`
--

LOCK TABLES `Reserved_Rooms` WRITE;
/*!40000 ALTER TABLE `Reserved_Rooms` DISABLE KEYS */;
INSERT INTO `Reserved_Rooms` VALUES (1,5,1,2,'test'),(4,2,1,1,'test1');
/*!40000 ALTER TABLE `Reserved_Rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Room`
--

DROP TABLE IF EXISTS `Room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Room` (
  `Room_Number` int(11) NOT NULL AUTO_INCREMENT,
  `FK_Room_Type_ID` int(11) NOT NULL,
  PRIMARY KEY (`Room_Number`),
  UNIQUE KEY `Room_Number` (`Room_Number`),
  KEY `FK_ROOM_TYPE_ID_idx` (`FK_Room_Type_ID`),
  CONSTRAINT `FK_ROOM_TYPE_ID` FOREIGN KEY (`FK_Room_Type_ID`) REFERENCES `Room_Type` (`room_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Room`
--

LOCK TABLES `Room` WRITE;
/*!40000 ALTER TABLE `Room` DISABLE KEYS */;
INSERT INTO `Room` VALUES (2,1),(1,2);
/*!40000 ALTER TABLE `Room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Room_Type`
--

DROP TABLE IF EXISTS `Room_Type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Room_Type` (
  `room_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) CHARACTER SET utf8 NOT NULL,
  `max_capacity` int(11) NOT NULL,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`room_type_id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Room_Type`
--

LOCK TABLES `Room_Type` WRITE;
/*!40000 ALTER TABLE `Room_Type` DISABLE KEYS */;
INSERT INTO `Room_Type` VALUES (1,'The Presidential Suite',10,'Presidential',0),(2,'A standard room',2,'Standard',0);
/*!40000 ALTER TABLE `Room_Type` ENABLE KEYS */;
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
  `User_Password` varchar(128) COLLATE utf8_bin NOT NULL,
  `Address` varchar(280) COLLATE utf8_bin NOT NULL,
  `Card_Number` int(11) NOT NULL,
  `Memebership_Status` varchar(128) COLLATE utf8_bin NOT NULL,
  `Phone_Number` int(11) NOT NULL,
  `Email_Address` varchar(128) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Card_Number` (`Card_Number`),
  UNIQUE KEY `Phone_Number` (`Phone_Number`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Starwood`
--

LOCK TABLES `Starwood` WRITE;
/*!40000 ALTER TABLE `Starwood` DISABLE KEYS */;
INSERT INTO `Starwood` VALUES (1,'Tom','Yates','Tom.yates','Password1','Dublin 5',1233232,'Active',852222432,'Abdulj947@gmail');
/*!40000 ALTER TABLE `Starwood` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hosted_at`
--

DROP TABLE IF EXISTS `hosted_at`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hosted_at` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guest_id` int(11) NOT NULL,
  `occupied_room_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `guest_id` FOREIGN KEY (`id`) REFERENCES `Guest` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `occupied_room_id` FOREIGN KEY (`id`) REFERENCES `occupied_room` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hosted_at`
--

LOCK TABLES `hosted_at` WRITE;
/*!40000 ALTER TABLE `hosted_at` DISABLE KEYS */;
/*!40000 ALTER TABLE `hosted_at` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `occupied_room`
--

DROP TABLE IF EXISTS `occupied_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `occupied_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `check_in` date NOT NULL,
  `check_out` date NOT NULL,
  `room_id` int(11) NOT NULL,
  `reservation_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (reservation_id) REFERENCES Reservations(Reservation_Id) ON DELETE Cascade ON UPDATE restrict
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `occupied_room`
--

LOCK TABLES `occupied_room` WRITE;
/*!40000 ALTER TABLE `occupied_room` DISABLE KEYS */;
INSERT INTO `occupied_room` VALUES (1,'2018-07-06','2018-10-02',2,2),(2,'2018-05-04','2018-02-22',1,1),(9,'2018-05-04','2018-05-04',1,2);
/*!40000 ALTER TABLE `occupied_room` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-15 23:44:05

-- MySQL dump 10.15  Distrib 10.0.28-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: localhost
-- ------------------------------------------------------
-- Server version	10.0.38-MariaDB

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
-- Table structure for table `Bed`
--

DROP TABLE IF EXISTS `Bed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Bed` (
  `ID` varchar(15) DEFAULT NULL,
  `patientID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bed`
--

LOCK TABLES `Bed` WRITE;
/*!40000 ALTER TABLE `Bed` DISABLE KEYS */;
INSERT INTO `Bed` VALUES ('020',1235842509),('012',1235842508);
/*!40000 ALTER TABLE `Bed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Log`
--

DROP TABLE IF EXISTS `Log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Log` (
  `time` datetime DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Log`
--

LOCK TABLES `Log` WRITE;
/*!40000 ALTER TABLE `Log` DISABLE KEYS */;
INSERT INTO `Log` VALUES ('2021-04-13 13:52:00','add Nurse ID:2003 Username:manager1'),('2021-04-13 13:53:00','Nurse:2001 Change patient:patient1 from bed:000 to 013'),('2021-04-13 13:54:00','MedicalWorker: 1002 Check Patient:patient2 Detail'),('2021-04-13 13:54:00','MedicalWorker: 1002 Check Patient:patient1 Detail'),('2021-04-13 13:54:00','MedicalWorker: 1002 Check Patient:patient1 Detail'),('2021-04-13 13:54:00','Doctor: 1002 doctor2 attach prescription:medicine3 to 013->Patient:patient1'),('2021-04-13 13:54:00','Doctor: 1002 doctor2 attach prescription:medicine4 to 013->Patient:patient1'),('2021-04-13 13:54:00','Doctor: 1002 doctor2 remove medicine from prescription:medicine3 to 013->Patient:patient1'),('2021-04-13 13:55:00','Nurse: 1002 update bed 013 Prescription  medicine name:medicine4 doseAmt:3 doseUnit:1g'),('2021-04-13 13:55:00','Nurse: 1002 update bed 013 Prescription  medicine name:medicine4 doseAmt:3 doseUnit:2g'),('2021-04-13 13:57:00','MedicalWorker: 1002 Check Patient:patient1 Detail'),('2021-04-13 13:57:00','Nurse: 1002 update bed 013 Prescription  medicine name:medicine4 doseAmt:4 doseUnit:3g'),('2021-04-13 13:58:00','Nurse:2001 Change patient:patient1 from bed:013 to 012');
/*!40000 ALTER TABLE `Log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Prescription`
--

DROP TABLE IF EXISTS `Prescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Prescription` (
  `patientID` int(11) DEFAULT NULL,
  `workerID` int(11) DEFAULT NULL,
  `medicineName` varchar(10) DEFAULT NULL,
  `medicinedoseAmt` varchar(10) DEFAULT NULL,
  `medicinedoseUnit` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Prescription`
--

LOCK TABLES `Prescription` WRITE;
/*!40000 ALTER TABLE `Prescription` DISABLE KEYS */;
INSERT INTO `Prescription` VALUES (1235842509,1001,'medicine1','3','2g'),(1235842508,1002,'medicine4','4','3g');
/*!40000 ALTER TABLE `Prescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `needIsolation` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (1235842508,'patient1','Male','True'),(1235842509,'patient2','Female','True'),(1235842510,'patient3','Male','False'),(1235842511,'patient4','Female','True');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shifts`
--

DROP TABLE IF EXISTS `shifts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shifts` (
  `id` int(11) DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `day` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shifts`
--

LOCK TABLES `shifts` WRITE;
/*!40000 ALTER TABLE `shifts` DISABLE KEYS */;
INSERT INTO `shifts` VALUES (2001,'08:00:00','18:00:00','2021-03-03'),(1002,'08:05:00','15:30:00','2021-05-14');
/*!40000 ALTER TABLE `shifts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) DEFAULT NULL,
  `username` varchar(10) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  `usertype` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1032425,'manager1','12345','manager'),(1002,'doctor2','12345','doctor'),(2001,'nurse1','12345','nurse'),(2003,'doctor3','12345','nurse');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-13 14:06:23

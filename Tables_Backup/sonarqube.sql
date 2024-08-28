-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: sonarcubedb
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `project_names`
--

use sonarcubedb
DROP TABLE IF EXISTS `project_names`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project_names` (
  `Project_Name` varchar(70) NOT NULL,
  PRIMARY KEY (`Project_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_names`
--

LOCK TABLES `project_names` WRITE;
/*!40000 ALTER TABLE `project_names` DISABLE KEYS */;
INSERT INTO `project_names` VALUES ('access-control-service'),('alarm-export-service'),('alarmlinkmanagement-service'),('asr ui'),('asr-forwarder'),('asr-testware'),('asr-topology-service'),('asr-topology-service-model'),('asrl rest api'),('asrl-configuration-mode'),('asrl-correlation'),('asrl-record-model'),('asrl-service'),('EO-AGAT-DAWN'),('eric-udr-dbmonitor');
/*!40000 ALTER TABLE `project_names` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qualitygate`
--

DROP TABLE IF EXISTS `qualitygate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qualitygate` (
  `Proj_Name` varchar(50) DEFAULT NULL,
  `Metrics` varchar(50) DEFAULT NULL,
  `Bugs` varchar(50) DEFAULT NULL,
  `CodeSmells` varchar(20) DEFAULT NULL,
  `security_rating` varchar(10) DEFAULT NULL,
  `Coverage` varchar(10) DEFAULT NULL,
  `Duplications` varchar(10) DEFAULT NULL,
  KEY `proj_fk` (`Proj_Name`),
  CONSTRAINT `proj_fk` FOREIGN KEY (`Proj_Name`) REFERENCES `project_names` (`Project_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qualitygate`
--

LOCK TABLES `qualitygate` WRITE;
/*!40000 ALTER TABLE `qualitygate` DISABLE KEYS */;
INSERT INTO `qualitygate` VALUES ('asr-topology-service','Passed','1','42','2B','95Tests','0.4%'),('asrl rest api','Passed','0','42','0A','0Tests','0%'),('alarmlinkmanagement-service','Failed','1','1','0A','0Tests','0%');
/*!40000 ALTER TABLE `qualitygate` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-07 11:06:54

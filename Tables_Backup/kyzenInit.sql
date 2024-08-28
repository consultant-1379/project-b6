-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: kyzen
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
-- Table structure for table `build_metric`
--

DROP TABLE IF EXISTS `build_metric`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `build_metric` (
  `metric_id` bigint NOT NULL AUTO_INCREMENT,
  `num_integration_tests_passing` int NOT NULL,
  `num_of_executions` int NOT NULL,
  `num_of_failures` int NOT NULL,
  `num_of_success` int NOT NULL,
  `num_unit_tests_passing` int NOT NULL,
  `total_integration_tests` int NOT NULL,
  `total_unit_tests` int NOT NULL,
  `team_id` bigint NOT NULL,
  PRIMARY KEY (`metric_id`),
  KEY `FKkkyfsbf7hlwsvgmed5sml6f4s` (`team_id`),
  CONSTRAINT `FKkkyfsbf7hlwsvgmed5sml6f4s` FOREIGN KEY (`team_id`) REFERENCES `team` (`team_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `build_metric`
--

LOCK TABLES `build_metric` WRITE;
/*!40000 ALTER TABLE `build_metric` DISABLE KEYS */;
INSERT INTO `build_metric` VALUES (1,90,10,1,9,80,100,100,1),(2,90,10,1,9,80,100,100,1),(4,0,20,5,15,19878,0,19882,2);
/*!40000 ALTER TABLE `build_metric` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `code_metric`
--

DROP TABLE IF EXISTS `code_metric`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `code_metric` (
  `metric_id` bigint NOT NULL AUTO_INCREMENT,
  `num_active_days` int NOT NULL,
  `num_commits` int NOT NULL,
  `num_lines_added` int NOT NULL,
  `num_lines_deleted` int NOT NULL,
  `team_id` bigint NOT NULL,
  PRIMARY KEY (`metric_id`),
  KEY `FKqj1bucym53os580mbwvhd0vok` (`team_id`),
  CONSTRAINT `FKqj1bucym53os580mbwvhd0vok` FOREIGN KEY (`team_id`) REFERENCES `team` (`team_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code_metric`
--

LOCK TABLES `code_metric` WRITE;
/*!40000 ALTER TABLE `code_metric` DISABLE KEYS */;
INSERT INTO `code_metric` VALUES (1,25,19,800,12,1),(2,25,19,800,12,1),(3,0,136,171,132,2);
/*!40000 ALTER TABLE `code_metric` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `signum` varchar(255) DEFAULT NULL,
  `team_id` bigint NOT NULL,
  PRIMARY KEY (`member_id`),
  KEY `FKcjte2jn9pvo9ud2hyfgwcja0k` (`team_id`),
  CONSTRAINT `FKcjte2jn9pvo9ud2hyfgwcja0k` FOREIGN KEY (`team_id`) REFERENCES `team` (`team_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'mairtin.deady@ericsson.com','Mairtin Deady','edeamai',2),(2,'paul.brian.sheridan@ericsson.com','Paul Brian Sheridan','eeipsen',2),(3,'sangeet.kapoor@ericsson.com','Sangeet Kapoor','eilsang',2),(4,'meabh.cody@ericsson.com','Meabh Cody','emeacod',2),(5,'james.peters@ericsson.com','James Peters','empjtae',2),(6,'niranjani.sundaresan@ericsson.com','Niranjani Sundaresan','enirsai',2),(7,'shiva.subedi@ericsson.com','Shiva Subedi','esbdshv',2),(8,'bartlomiej.zaucha@ericsson.com','Bartlomiej Zaucha','ezaubar',2),(9,'mairtin.deady@ericsson.com','CK','edeamai',5),(10,'a@ck.com','George','asdsl',1),(11,'a@ck.com','Anusha','asdsl',1),(12,'a@ck.com','Caolon','asdsl',1);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sonar_metric`
--

DROP TABLE IF EXISTS `sonar_metric`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sonar_metric` (
  `metric_id` bigint NOT NULL AUTO_INCREMENT,
  `bugs` varchar(255) DEFAULT NULL,
  `code_smells` varchar(255) DEFAULT NULL,
  `coverage` varchar(255) DEFAULT NULL,
  `duplications` varchar(255) DEFAULT NULL,
  `metrics` varchar(255) DEFAULT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `security_rating` varchar(255) DEFAULT NULL,
  `team_id` bigint NOT NULL,
  PRIMARY KEY (`metric_id`),
  KEY `FK3vwosk8whq3eo09e2a60eure6` (`team_id`),
  CONSTRAINT `FK3vwosk8whq3eo09e2a60eure6` FOREIGN KEY (`team_id`) REFERENCES `team` (`team_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sonar_metric`
--

LOCK TABLES `sonar_metric` WRITE;
/*!40000 ALTER TABLE `sonar_metric` DISABLE KEYS */;
INSERT INTO `sonar_metric` VALUES (1,'0','42','0Tests','0%','Passed','asrl rest api','0A',2);
/*!40000 ALTER TABLE `sonar_metric` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team` (
  `team_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`team_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (1,'No Team'),(2,'TeamVolt'),(3,'TeamTry'),(4,'TeamTry2'),(5,'TeamTry2');
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team_repo`
--

DROP TABLE IF EXISTS `team_repo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team_repo` (
  `member_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `team_id` bigint NOT NULL,
  PRIMARY KEY (`member_id`),
  KEY `FKgl20truqco98g7c11j7x7oj1n` (`team_id`),
  CONSTRAINT `FKgl20truqco98g7c11j7x7oj1n` FOREIGN KEY (`team_id`) REFERENCES `team` (`team_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team_repo`
--

LOCK TABLES `team_repo` WRITE;
/*!40000 ALTER TABLE `team_repo` DISABLE KEYS */;
INSERT INTO `team_repo` VALUES (1,'asrl-topology-service-model','',2),(2,'asr-topology-service','',2),(3,'asr-testware','',2),(4,'asr-ui','',2),(5,'asrl-configuration-model','',2),(6,'asrl-record-model','',2),(7,'asrl-service','',2),(8,'asr-forwarder','',2),(9,'asrl-rest-api','',2),(10,'asrl-correlation','',2),(11,'asrl-topology-service-model','lammamamP.com',5);
/*!40000 ALTER TABLE `team_repo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-12 12:12:37

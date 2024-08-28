-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: gerrit_metrics
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
-- Temporary view structure for view `all_details`
--

DROP TABLE IF EXISTS `all_details`;
/*!50001 DROP VIEW IF EXISTS `all_details`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `all_details` AS SELECT 
 1 AS `t_id`,
 1 AS `c_author`,
 1 AS `c_date`,
 1 AS `c_hash`,
 1 AS `c_in_main_branch`,
 1 AS `c_message`,
 1 AS `c_lines_added`,
 1 AS `c_lines_deleted`,
 1 AS `c_project_name`,
 1 AS `m_email`,
 1 AS `m_id`,
 1 AS `m_name`,
 1 AS `t_name`,
 1 AS `t_group_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `all_details`
--

/*!50001 DROP VIEW IF EXISTS `all_details`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `all_details` AS select `t`.`t_id` AS `t_id`,`d`.`c_author` AS `c_author`,`d`.`c_date` AS `c_date`,`d`.`c_hash` AS `c_hash`,`d`.`c_in_main_branch` AS `c_in_main_branch`,`d`.`c_message` AS `c_message`,`d`.`c_lines_added` AS `c_lines_added`,`d`.`c_lines_deleted` AS `c_lines_deleted`,`d`.`c_project_name` AS `c_project_name`,`d`.`m_email` AS `m_email`,`d`.`m_id` AS `m_id`,`d`.`m_name` AS `m_name`,`t`.`t_name` AS `t_name`,`t`.`t_group_id` AS `t_group_id` from (`details` `d` join `team` `t` on((`t`.`t_id` = `d`.`t_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-07 16:18:05

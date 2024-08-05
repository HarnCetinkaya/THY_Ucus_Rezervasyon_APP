-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: ucus_rezervasyon
-- ------------------------------------------------------
-- Server version	8.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ucuslar`
--

DROP TABLE IF EXISTS `ucuslar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ucuslar` (
  `ucus_id` int NOT NULL AUTO_INCREMENT,
  `kalkis_yeri` varchar(100) NOT NULL,
  `varis_yeri` varchar(100) NOT NULL,
  `kalkis_zamani` datetime NOT NULL,
  `varis_zamani` datetime NOT NULL,
  `mevcut_koltuk` int NOT NULL,
  `ucus_turu` enum('yurtici','yurtdisi') NOT NULL,
  `ucus_tipi` varchar(50) NOT NULL,
  `fiyat` decimal(10,2) NOT NULL,
  PRIMARY KEY (`ucus_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ucuslar`
--

LOCK TABLES `ucuslar` WRITE;
/*!40000 ALTER TABLE `ucuslar` DISABLE KEYS */;
INSERT INTO `ucuslar` VALUES (5,'İstanbul','New York','2024-06-01 14:00:00','2024-06-01 20:00:00',20,'yurtdisi','Tek Yön',2500.00),(6,'İstanbul','Ankara','2024-06-01 08:00:00','2024-06-01 09:00:00',14,'yurtici','Tek Yön',500.00),(7,'İstanbul','Berlin','2024-07-01 10:00:00','2024-07-01 12:00:00',150,'yurtdisi','Tek Yön',1200.00),(8,'Ankara','Paris','2024-08-01 08:00:00','2024-08-01 11:00:00',100,'yurtdisi','Tek Yön',2500.00),(9,'İstanbul','Elazığ','2024-07-15 08:00:00','2024-07-15 11:00:00',100,'yurtici','Gidşi-Dönüş',2500.00),(10,'İstanbul','Antalya','2024-08-15 15:00:00','2024-08-15 18:00:00',25,'yurtici','Tek Yön',3000.00),(11,'New York','İzmir','2024-11-29 13:00:00','2024-11-29 16:25:00',55,'yurtdisi','Gidiş-Dönüş',8500.00);
/*!40000 ALTER TABLE `ucuslar` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-20 10:52:11

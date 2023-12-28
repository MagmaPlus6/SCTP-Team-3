CREATE DATABASE  IF NOT EXISTS `final_project_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `final_project_db`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: final_project_db
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `order_detail_id` int NOT NULL,
  `price` decimal(38,2) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `size` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`order_detail_id`),
  KEY `FKrws2q0si6oyd6il8gqe2aennc` (`order_id`),
  KEY `FKb8bg2bkty0oksa3wiq5mp5qnc` (`product_id`),
  CONSTRAINT `FKb8bg2bkty0oksa3wiq5mp5qnc` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKrws2q0si6oyd6il8gqe2aennc` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (3,32.49,3,3,18,'M'),(4,225.30,6,3,19,'XL'),(5,37.55,1,7,19,'XS'),(6,10.83,1,7,18,'XS'),(7,37.55,1,7,19,'XS'),(8,150.20,4,8,19,'L'),(9,108.30,10,8,18,'XS'),(10,112.65,3,8,19,'XL'),(11,187.75,5,9,19,'L'),(12,375.50,10,9,19,'M'),(13,10.83,1,9,18,'XL'),(14,21.66,2,9,18,'XS'),(15,37.55,1,10,19,'XL'),(16,37.55,1,10,19,'M');
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail_sequence_generator`
--

DROP TABLE IF EXISTS `order_detail_sequence_generator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail_sequence_generator` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail_sequence_generator`
--

LOCK TABLES `order_detail_sequence_generator` WRITE;
/*!40000 ALTER TABLE `order_detail_sequence_generator` DISABLE KEYS */;
INSERT INTO `order_detail_sequence_generator` VALUES (17);
/*!40000 ALTER TABLE `order_detail_sequence_generator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_sequence_generator`
--

DROP TABLE IF EXISTS `order_sequence_generator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_sequence_generator` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_sequence_generator`
--

LOCK TABLES `order_sequence_generator` WRITE;
/*!40000 ALTER TABLE `order_sequence_generator` DISABLE KEYS */;
INSERT INTO `order_sequence_generator` VALUES (11);
/*!40000 ALTER TABLE `order_sequence_generator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL,
  `order_date` datetime(6) DEFAULT NULL,
  `subtotal` decimal(38,2) DEFAULT NULL,
  `total` decimal(38,2) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `shipping_fee` decimal(38,2) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (3,'2023-12-24 18:03:45.466548',290.28,295.57,'Matt',4.99),(4,'2023-12-25 11:02:57.666575',59.21,64.20,'Matt',4.99),(5,'2023-12-25 11:15:44.218412',85.93,90.92,'Matt',4.99),(7,'2023-12-25 11:44:25.166298',85.93,90.92,'Matt',4.99),(8,'2023-12-25 11:46:06.312154',371.15,376.14,'Matt',4.99),(9,'2023-12-28 12:17:02.139646',595.74,600.73,'Matt',4.99),(10,'2023-12-28 14:57:40.513731',75.10,80.09,'Matt',4.99);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(38,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (3,'Love, Bonito.','women-long-sleeve.jpg','Long Sleeve T Shirt',8.99),(4,'Tommy Hilfiger','women-pants.jpg','Pull Up Pants',12.17),(5,'Adidas','women-tracksuit.jpg','3-stripes Tracksuit',43.10),(8,'Curious Creatures','women-gym-pants.jpg','Jogging Gym Pants',16.01),(12,'Chic Reverie','Blue dress.jpg','blue dress',7.00),(17,'ZestThreads','BlueReebokShirt.jpg','Fitness T Shirt',14.39),(18,'Beyond the Vines','women-blouse.jpg','Button Down Blouse',10.83),(19,'Urban Coco','women-skirt.jpg','Pocket Skirt',37.55),(20,'Kydra','women-gym-top.jpg','Women Gym Top',20.06),(22,'Love, Bonito.','76782cb3-fdff-4b71-8cb7-45600a9997b6_ladies winter coat.png','Ladies Winter Coat',56.40);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `your_sequence_generator`
--

DROP TABLE IF EXISTS `your_sequence_generator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `your_sequence_generator` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `your_sequence_generator`
--

LOCK TABLES `your_sequence_generator` WRITE;
/*!40000 ALTER TABLE `your_sequence_generator` DISABLE KEYS */;
INSERT INTO `your_sequence_generator` VALUES (23);
/*!40000 ALTER TABLE `your_sequence_generator` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-28 20:30:20

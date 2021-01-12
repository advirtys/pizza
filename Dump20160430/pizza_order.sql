-- MySQL dump 10.13  Distrib 5.6.30, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: pizza
-- ------------------------------------------------------
-- Server version	5.6.30-0ubuntu0.15.10.1

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
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `status` int(2) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (3,1,'Абылкасов Сабитова 22 53 7026443394','Пепперони / Pepperoni - 2 ',2,'2016-04-08',1),(4,1,'Абылкасов Сабитова 22 53 7026443394','Coca Cola / Coca Cola - 2\nПепперони / Pepperoni - 1\n',3,'2016-04-08',1),(6,1,'Абылкасов Сабитова 22 53 7026443394','Маругай вакаме / Marugán wakame - 1\n',1,'2016-04-10',1),(7,2,'Сидоров Космонавтов 52 10 7026443394','Coca Cola / Coca Cola - 1\n',1,'2016-04-10',1),(8,1,'Абылкасов Сабитова 22 53 7026443394','Пепперони / Pepperoni - 1\n',1,'2016-04-11',1),(9,2,'Сидоров Космонавтов 52 10 7026443394','Пепперони / Pepperoni - 1\nКарбонара / Carbonara - 1\n',2,'2016-04-11',0),(10,9,'https://gist.github.com/Hondrus/1ba2284d309fccffe6b3bc6cb37b2875 https://gist.github.com/Hondrus/1ba2284d309fccffe6b3bc6cb37b2875 5 2 https://gist.github.com/Hondrus/1ba2284d309fccffe6b3bc6cb37b2875','Пепперони / Pepperoni - 1\nМаругай вакаме / Marugán wakame - 1\nSprite / Sprite - 1\n',3,'2016-04-11',0),(11,9,'DROP TABLE USERS; 1 1 1 1','Пепперони / Pepperoni - 1\n',1,'2016-04-11',0),(12,2,'Сидоров Космонавтов 52 10 7026443394','Пепперони / Pepperoni - 1\n',1,'2016-04-12',0);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-30  8:14:28

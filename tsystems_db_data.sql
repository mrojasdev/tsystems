CREATE DATABASE  IF NOT EXISTS `tsystems` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tsystems`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: tsystems
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `c_addresses`
--

DROP TABLE IF EXISTS `c_addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `c_addresses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `client_id` int DEFAULT NULL,
  `country` varchar(60) DEFAULT NULL,
  `city` varchar(60) DEFAULT NULL,
  `postal_code` int DEFAULT NULL,
  `street` varchar(80) DEFAULT NULL,
  `home` varchar(10) DEFAULT NULL,
  `apartment` varchar(10) DEFAULT NULL,
  `deleted` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_address_client` (`client_id`),
  CONSTRAINT `fk_address_client` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_addresses`
--

LOCK TABLES `c_addresses` WRITE;
/*!40000 ALTER TABLE `c_addresses` DISABLE KEYS */;
INSERT INTO `c_addresses` VALUES (1,1,'UnUnited States','Old York',10001,'123 Side St','Apt 45','Suite 789',1),(2,1,'Spain','Granada',18092,'C/ Santa Ana','24','1',1),(4,1,'United States','New York',10001,'123 Main St','Apt 45','Suite 789',0),(5,1,'UnUnited States','Old York',10001,'123 Side St','Apt 45','Suite 789',0),(6,7,'Spain','Madrid',18920,'C/ Ejemplo','45','21',0),(7,7,'Germany','Köln',21920,'Christinastrasse','25','2',0),(15,7,'Spain','Granada',18070,'C/ Ancha de Capuchinos','23','2',1),(16,11,'Spain','Granada',18070,'C/ Antonio Machado','11','2',0),(17,11,'Germany','Köln',50667,'Mengelbergstrasse','3','1',0),(18,11,'Spain','Granada',18292,'C/ Example','2','3',1);
/*!40000 ALTER TABLE `c_addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `client_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cart_client` (`client_id`),
  KEY `fk_cart_product` (`product_id`),
  CONSTRAINT `fk_cart_client` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`),
  CONSTRAINT `fk_cart_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (35,15,6),(36,15,6),(37,15,10),(38,15,9),(39,15,9),(66,7,6),(67,7,4);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(20) NOT NULL,
  `surname` varchar(40) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `passwd` varchar(130) NOT NULL,
  `email` varchar(60) DEFAULT NULL,
  `user_role` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'User','Generic','2000-01-01','$2a$10$qIOUyuKVysoA/imIIxOdp.boRqGZI6VXWvaksjWk2s6311suUuLjy','prueba@prueba.es','ROLE_USER'),(5,'Test','Ing','2001-03-01','$2a$10$hgYyibASV7sKWY0umPRao..APgxKUF0QmXS8aD3tdU4ObKUcfcdoi','prueba@prueba.com','ROLE_USER'),(7,'Admin','Istrator','1995-01-01','$2a$10$5sQL4nSWNzriKeVelI7LC.RgzGPYeGj4m2wyCoZZ7rdTMWhIjXPR.','emaildeprueba@gmail.com','ROLE_ADMIN'),(11,'Manuel','Lopez','2000-04-01','$2a$10$qBuSjrcEsXZEuX.ZYtDdouSd4MdH.B613L9pPVuF9OkETEnNnwR4S','mrojasdev@gmail.com','ROLE_USER'),(12,'John','Doe','2000-04-01','$2a$10$/BOM.eFlCHLSbTOzdOpvyOmwfFpZVZFE6PbxfbJ.MLNwFdYO1bJLe','email@email.com','ROLE_USER'),(13,'Client','Client','2000-04-01','$2a$10$OMSOJcIK7H0ZjAQV32WTFeeMeW6wPDOcJYlGAEB9ORJFyRTS0OmwG','client@client.com','ROLE_USER'),(15,'Manuel','Lopez','2000-04-01','$2a$10$PmtXW0uSP3gzNaRBFdvs8OnQt9RQfyfMA90Qyxdw0zK6vAQLv9Wtu','elzorrogris47@gmail.com','ROLE_USER');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_products`
--

DROP TABLE IF EXISTS `order_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_products` (
  `order_id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` int DEFAULT NULL,
  `total_price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`order_id`,`product_id`),
  KEY `fk_orderproduct_product` (`product_id`),
  CONSTRAINT `fk_orderproduct_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `fk_orderproduct_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_products`
--

LOCK TABLES `order_products` WRITE;
/*!40000 ALTER TABLE `order_products` DISABLE KEYS */;
INSERT INTO `order_products` VALUES (1,1,5,49.00),(1,2,51,50.00),(2,1,2,399.98),(2,4,2,399.98),(2,7,40,399.98),(2,11,38,399.98),(18,3,4,799.96),(18,4,1,59.99),(18,6,1,1299.99),(18,7,1,129.99),(19,5,1,1999.99),(20,5,1,1999.99),(20,6,1,1299.99),(20,7,1,129.99),(21,3,1,199.99),(22,3,1,199.99),(22,8,1,799.99),(22,10,1,299.99),(22,15,1,499.99),(23,7,1,129.99),(24,3,1,199.99),(24,7,1,129.99),(24,8,1,799.99),(25,2,1,189.99),(25,7,1,425.99),(26,1,1,322.99),(26,2,1,189.99),(27,4,1,2333.99),(27,7,1,425.99),(27,11,1,598.99),(28,1,1,322.99),(28,2,2,379.98),(29,1,1,322.99),(29,2,1,189.99),(29,3,1,169.99);
/*!40000 ALTER TABLE `order_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `client_id` int NOT NULL,
  `client_address` int NOT NULL,
  `payment_method` varchar(20) NOT NULL,
  `delivery_method` varchar(20) NOT NULL,
  `payment_status` varchar(10) DEFAULT NULL,
  `order_status` varchar(20) DEFAULT NULL,
  `order_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_client` (`client_id`),
  KEY `fk_order_address` (`client_address`),
  CONSTRAINT `fk_order_address` FOREIGN KEY (`client_address`) REFERENCES `c_addresses` (`id`),
  CONSTRAINT `fk_order_client` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,4,'Credit Card','Express','Paid','Processing','2023-12-02'),(2,1,4,'Credit Card','Express','Pending','Shipped','2023-12-02'),(3,1,4,'Credit Card','Express','Pending','Delivered','2023-12-02'),(4,5,4,'Credit Card','Express','Paid','Delivered','2023-12-02'),(18,7,6,'Card','UPS','Pending','Pending payment','2023-12-09'),(19,7,6,'Paypal','UPS Express','Pending','Pending payment','2023-12-09'),(20,7,6,'Paypal','UPS Express','Paid','Delivered','2023-12-09'),(21,7,6,'Paypal','DHL','Paid','Delivered','2023-12-09'),(22,7,6,'Card','UPS','Pending','Pending payment','2023-12-09'),(23,7,7,'Paypal','DHL','Paid','Delivered','2023-12-09'),(24,7,6,'Card','UPS','Pending','Pending payment','2023-12-10'),(25,7,7,'Paypal','UPS','Paid','Delivered','2023-12-11'),(26,11,16,'Card','DHL','Pending','Pending payment','2023-12-11'),(27,11,17,'Paypal','DHL','Pending','Pending payment','2023-12-11'),(28,11,17,'Card','UPS Express','Paid','Delivered','2023-12-11'),(29,7,7,'Paypal','UPS Express','Pending','Pending payment','2023-12-13');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(60) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `category` varchar(60) DEFAULT NULL,
  `brand` varchar(30) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `weight` decimal(10,2) DEFAULT NULL,
  `volume` decimal(10,2) DEFAULT NULL,
  `stock` int unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Jackson JS32-T Kelly',322.99,'Guitars','Jackson','White',1.00,1.00,350),(2,'Squier Affinity Strat',189.99,'Guitars','Squier','Green',1.00,1.00,350),(3,'Harley Benton JB-75MN',169.99,'Basses','Harley Benton','Wood',0.50,0.10,100),(4,'Fender AV II 66 Jazz',2333.99,'Basses','Fender','Aquamarine',0.80,0.20,150),(5,'Millenium MX420 Studio',435.99,'Drumkits','Millenium','Black',150.00,25.00,50),(6,'Harley Benton TE-52 NA',159.99,'Guitars','Harley Benton','Wood',2.00,0.50,75),(7,'Yamaha P-145 B',425.99,'Pianos','Yamaha','Black',0.80,0.20,120),(8,'Harley Benton HB-20R',79.95,'Amplifiers','Harley Benton','Black',30.00,5.00,80),(9,'Gibson SG Standard HC',1666.00,'Guitars','Gibson','Red',0.40,0.08,90),(10,'Boss Katana 50 MKII',235.99,'Amplifiers','Boss','Black',0.20,0.02,110),(11,'Roland FP-30X BK',598.99,'Pianos','Roland','Black',0.70,0.15,130),(12,'Yamaha C40',111.99,'Guitars','Yamaha','Wood',75.00,10.00,60),(13,'Harley Benton TE-20HH',99.99,'Guitars','Harley Benton','Black',0.45,0.09,70),(14,'ESP LTD Snakebyte',1999.99,'Guitars','ESP','Camo',0.30,0.05,180),(15,'Gibson 80s Explorer',2589.99,'Guitars','Gibson','Black',10.00,1.50,40),(16,'ESP LTD MH-10',249.99,'Guitars','ESP','Black',2.00,0.20,30),(17,'Jackson WR1 Warrior',3899.99,'Guitars','Jackson','Black',0.90,0.20,100),(18,'Squier Aff. Jazz Bass V',279.99,'Basses','Squier','White',40.00,6.00,65),(19,'Fender AM Ultra-J',2499.99,'Basses','Fender','White',2.50,0.80,45),(20,'Squier CV 70s Jazz Bass',425.99,'Basses','Squier','Wood',0.20,0.02,220),(21,'Startone SAS-75 Alto Sax',289.99,'Saxophones','Startone','Gold',0.10,0.01,150),(22,'Startone SCS-75 Curved',349.99,'Saxophones','Startone','Gold',8.00,1.00,80),(23,'Jupiter JSS1000Q',1439.99,'Saxophones','Jupiter','Gold',0.50,0.10,130),(24,'Sankyo CF 401 Flute',5599.99,'Flutes','Sankyo','Metal',5.00,0.30,100),(25,'Yamaha YFL-272 Flute',695.99,'Flutes','Yamaha','Metal',0.15,0.02,110),(26,'PRS SE Custom 24 FE',699.99,'Basses','PRS','Blue',25.00,4.00,40),(27,'Ibanez SDGB1-DMT',1690.99,'Basses','Ibanez','Green',0.80,0.15,120),(28,'Fender LTD Player',964.99,'Basses','Fender','Wood',5.00,0.30,55),(29,'Ibanez AEGB24FE-MHS',498.99,'Basses','Ibanez','Wood',0.70,0.20,140),(30,'Fender CB-60SCE A-Bass',269.99,'Basses','Fender','Black',10.00,0.50,75),(31,'Gibson SG Standard HC',1669.99,'Guitars','Gibson','Red',2.00,0.50,90),(32,'Epiphone Joe Bonamassa',1499.99,'Guitars','Epiphone','Red',0.60,0.15,100),(33,'Epiphone 1961 Les Paul SG',949.99,'Guitars','Epiphone','Red',1.00,1.00,200),(34,'Yamaha Revstar RSS20',795.99,'Guitars','Yamaha','Green',1.00,1.00,150);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-16 15:59:58

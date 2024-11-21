-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: medical_records
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `illnes`
--

DROP TABLE IF EXISTS `illnes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `illnes` (
  `illnes_id` int NOT NULL AUTO_INCREMENT,
  `ill_description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ill_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `patient_registration` date DEFAULT NULL,
  PRIMARY KEY (`illnes_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `illnes`
--

LOCK TABLES `illnes` WRITE;
/*!40000 ALTER TABLE `illnes` DISABLE KEYS */;
INSERT INTO `illnes` VALUES (1,'Гостре респіраторне захворювання','Грип','2024-01-15'),(2,'Запалення легенів, викликане інфекцією','Пневмонія','2024-02-10'),(3,'Хронічний кашель, загальне нездужання','Бронхіт','2024-03-05'),(4,'Зниження рівня глюкози в крові','Цукровий діабет','2024-04-20'),(5,'Гострий головний біль та висока температура','Мігрень','2024-05-12'),(6,'Проблеми з роботою щитовидної залози','Гіпотиреоз','2024-06-25'),(7,'Підвищений кров\'яний тиск','Гіпертензія','2024-07-03'),(8,'Алергічна реакція на певні подразники','Алергія','2024-08-15'),(9,'Запалення шлунково-кишкового тракту','Гастрит','2024-09-08'),(10,'Непереносимість глютену','Целіакія','2024-10-01'),(11,'Надмірне накопичення жирів у печінці','Жирова дистрофія печінки','2024-10-12'),(12,'Дегенеративне ураження суглобів','Артроз','2024-11-05'),(13,'Хвороба, що спричиняє висипання на шкірі','Дерматит','2024-11-20'),(14,'Порушення обміну речовин в організмі','Гіперглікемія','2024-12-10'),(15,'Інфекційне запалення мозкових оболонок','Менінгіт','2024-12-25'),(32,'Інфекційне захворювання','Алергія','2024-10-03'),(33,'Дегенеративне ураження сугробів','Артроз','2024-07-17');
/*!40000 ALTER TABLE `illnes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `patient_id` int NOT NULL AUTO_INCREMENT,
  `date_of_birth` date DEFAULT NULL,
  `patient_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `patient_ill` int DEFAULT NULL,
  `illnes_id` int DEFAULT NULL,
  PRIMARY KEY (`patient_id`),
  KEY `FK7lnmc3vdrbam6ytdwbcbu4xxv` (`patient_ill`),
  KEY `FK69gix8jmyu9xa9oqqpr9qo4m3` (`illnes_id`),
  CONSTRAINT `FK69gix8jmyu9xa9oqqpr9qo4m3` FOREIGN KEY (`illnes_id`) REFERENCES `illnes` (`illnes_id`),
  CONSTRAINT `FK7lnmc3vdrbam6ytdwbcbu4xxv` FOREIGN KEY (`patient_ill`) REFERENCES `illnes` (`illnes_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (1,'1990-05-15','Іванов Іван',1,NULL),(2,'1985-03-22','Петров Петро',2,NULL),(3,'1992-07-30','Сидоренко Ольга',3,NULL),(4,'1978-09-10','Коваленко Марія',4,NULL),(5,'2000-11-25','Дмитренко Андрій',5,NULL),(6,'1995-01-12','Ткаченко Микола',6,NULL),(7,'1980-04-03','Гриценко Олена',7,NULL),(8,'1999-06-18','Бохарченко Василь',8,NULL),(9,'1994-08-05','Мельник Тетяна',9,NULL),(10,'1989-02-13','Романчук Юрій',10,NULL),(11,'1991-10-22','Шевченко Наталія',11,NULL),(12,'1976-12-19','Короленко Віктор',12,NULL),(13,'1983-03-09','Костенко Олександр',13,NULL),(14,'2001-07-27','Лисенко Ірина',14,NULL),(15,'1996-05-02','Семенов Володимир',15,NULL),(16,'1998-09-15','Остапенко Анна',1,NULL),(17,'1979-11-30','Хоменко Віталій',2,NULL),(18,'1984-06-08','Савченко Катерина',3,NULL),(19,'1993-04-14','Федоренко Сергій',4,NULL),(20,'2002-03-03','Бондаренко Євген',5,NULL),(21,'1997-07-20','Шаповал Алла',6,NULL),(22,'1981-09-02','Кравченко Валентина',7,NULL),(23,'1988-12-27','Мартиненко Микола',8,NULL),(24,'1990-10-11','Слободянюк Василь',9,NULL),(25,'1982-01-16','Іващенко Ганна',10,NULL),(26,'1986-11-18','Гончаренко Вікторія',11,NULL),(27,'1992-02-07','Назаренко Олексій',12,NULL),(28,'1987-05-25','Кошовий Андрій',13,NULL),(29,'1975-04-04','Мороз Ігор',14,NULL),(30,'2000-12-21','Соломаха Юлія',15,NULL),(31,'1995-08-17','Лапенко Григорій',1,NULL),(32,'1983-05-08','Зубченко Роман',2,NULL),(33,'1998-03-22','Куцко Олександра',3,NULL),(34,'1996-06-30','Павленко Світлана',4,NULL),(35,'1989-09-28','Арко Богдан',5,NULL),(40,'2001-09-30','Пол Джонс',32,NULL),(41,'1994-06-18','Лобець Дмитро',33,NULL);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-22 20:57:22

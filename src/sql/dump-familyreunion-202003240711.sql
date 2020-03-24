-- MySQL dump 10.13  Distrib 5.5.62, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: familyreunion
-- ------------------------------------------------------
-- Server version	5.7.29-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `addressbook`
--

DROP TABLE IF EXISTS `addressbook`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `addressbook`
(
    `id`            int(11) unsigned NOT NULL AUTO_INCREMENT,
    `lastname`      varchar(50)  DEFAULT NULL,
    `firstname`     varchar(50)  DEFAULT NULL,
    `streetaddress` varchar(255) DEFAULT NULL,
    `city`          varchar(255) DEFAULT NULL,
    `state`         varchar(255) DEFAULT NULL,
    `country`       varchar(255) DEFAULT NULL,
    `zipcode`       varchar(10)  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 9
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addressbook`
--

LOCK TABLES `addressbook` WRITE;
/*!40000 ALTER TABLE `addressbook`
    DISABLE KEYS */;
INSERT INTO `addressbook`
VALUES (2, 'milton', 'chappox', '100 fake street', 'columbus', 'oh', 'USA', '43081'),
       (3, 'Milton', 'Lasana', '5744 Buenos Aires Blvd', 'Westerville', 'OH', 'USA', '43081'),
       (4, 'Parker', 'Milton', '5744 Buenos Aires Blvd', 'Westerville', 'OH', 'USA', '43081'),
       (5, 'Ramadhani', 'Aseri', '100 fake st', 'Westerville', 'Ohio', 'United States', '43081'),
       (6, 'Takenawa', 'Trisha', '400 Burche Road', 'Wester', 'OH', 'USA', '42091'),
       (7, 'Falcon', 'Captain', '100 Falcon Ln', 'Falcon', 'PU', 'NCH', '12345'),
       (8, 'Character', 'Lucas', '980 Some Game', 'Cant', 'Remember', 'ATAL', '09434');
/*!40000 ALTER TABLE `addressbook`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login`
(
    `id`        int(11) unsigned NOT NULL AUTO_INCREMENT,
    `username`  varchar(50)               DEFAULT '',
    `password`  varchar(255)              DEFAULT NULL,
    `email`     varchar(255)              DEFAULT NULL,
    `lastlogin` varchar(50)               DEFAULT NULL,
    `lastip`    varchar(13)               DEFAULT NULL,
    `level`     int(1)           NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 19
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login`
    DISABLE KEYS */;
INSERT INTO `login`
VALUES (1, 'chappo', 'milton', 'chappo@teamhierro.com', '12/6/19', '192.168.0.31', 1),
       (14, 'lasana', '218b9828acd159d713484c4e37eaf3b4', 'lasana@milton.com', 'December 9, 2019, 12:04 pm',
        '192.168.0.4', 1),
       (15, 'Hamachi', 'milton', 'hamachi@milton.com', '2020-03-20 14:29:10.783', '127.0.0.1', 0),
       (16, 'test', 'test123', 'test@test.com', '2020-03-20 14:54:02.476', '127.0.0.1', 0),
       (17, 'test1', 'test123', 'test1@test.com', '2020-03-20 14:55:29.043', '127.0.0.1', 0),
       (18, 'test2', 'milton', 'test2@test.com', '2020-03-20 14:58:45.59', '127.0.0.1', 0);
/*!40000 ALTER TABLE `login`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logindetails`
--

DROP TABLE IF EXISTS `logindetails`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logindetails`
(
    `id`            int(11) unsigned NOT NULL AUTO_INCREMENT,
    `loginid`       int(11)      DEFAULT NULL,
    `addressbookid` int(11)      DEFAULT NULL,
    `birthday`      varchar(50)  DEFAULT '',
    `phonenumber`   varchar(20)  DEFAULT NULL,
    `email`         varchar(255) DEFAULT NULL,
    `funfact`       longtext,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logindetails`
--

LOCK TABLES `logindetails` WRITE;
/*!40000 ALTER TABLE `logindetails`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `logindetails`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `polls`
--

DROP TABLE IF EXISTS `polls`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `polls`
(
    `id`            int(11) unsigned NOT NULL AUTO_INCREMENT,
    `pollheader`    text,
    `polltext`      longtext,
    `numberofvotes` int(11) DEFAULT NULL,
    `datestart`     date    DEFAULT NULL,
    `dateend`       date    DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `polls`
--

LOCK TABLES `polls` WRITE;
/*!40000 ALTER TABLE `polls`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `polls`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pollsresults`
--

DROP TABLE IF EXISTS `pollsresults`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pollsresults`
(
    `id`         int(11) unsigned NOT NULL AUTO_INCREMENT,
    `pollsid`    int(11)      DEFAULT NULL,
    `loginid`    int(11)      DEFAULT NULL,
    `voteoption` varchar(255) DEFAULT NULL,
    `votedate`   date         DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pollsresults`
--

LOCK TABLES `pollsresults` WRITE;
/*!40000 ALTER TABLE `pollsresults`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `pollsresults`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'familyreunion'
--
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2020-03-24  7:11:24

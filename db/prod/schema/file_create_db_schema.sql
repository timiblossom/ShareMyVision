-- MySQL dump 10.11
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	5.0.77

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
-- Table structure for table `event`
--

create database file_db;
use file_db;

DROP TABLE IF EXISTS `event`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `event` (
  `event_id` BIGINT(20) UNSIGNED NOT NULL auto_increment,
  `event_title` VARCHAR(255) DEFAULT NULL,
  `event_code` VARCHAR(100) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `folder_url` VARCHAR(255) DEFAULT NULL,
  `tags` text DEFAULT NULL,
  `uid` BIGINT(20) DEFAULT NULL,
  `aid` BIGINT(20) DEFAULT NULL,
  `status` VARCHAR(20) DEFAULT NULL,
  `is_public` boolean DEFAULT false, 
  `expired_on` DATETIME NULL DEFAULT NULL,
  `posted_on` DATETIME NOT NULL,
  `updated_on` DATETIME NOT NULL,
  `version` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0, 
  `created_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY  (`event_id`),
  UNIQUE KEY `event_id_UNIQUE` (`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `event`
--


--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `item` (
  `item_id` BIGINT(20) UNSIGNED NOT NULL auto_increment,
  `item_title` VARCHAR(150) DEFAULT NULL,
  `item_code` VARCHAR(150) DEFAULT NULL,
  `description` text DEFAULT NULL,  
  `tags` text DEFAULT NULL,
  `uid` BIGINT(20) DEFAULT NULL,
  `aid` BIGINT(20) DEFAULT NULL,
  `status` VARCHAR(20) DEFAULT NULL,
  `is_public` boolean DEFAULT false,  
  `width` INT(11) DEFAULT NULL,
  `height` INT(11) DEFAULT NULL,
  `mime_type` VARCHAR(50) DEFAULT NULL,
  `size` BIGINT(20) UNSIGNED NOT NULL,
  `duration` INT(11) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `altitude` double DEFAULT NULL,
  `location` VARCHAR(255) DEFAULT NULL,
  `device_id` INT(11) DEFAULT NULL,
  `storage_id` VARCHAR(255) DEFAULT NULL,
  `storage_id1` VARCHAR(255) DEFAULT NULL,
  `storage_id2` VARCHAR(255) DEFAULT NULL,
  `storage_id3` VARCHAR(255) DEFAULT NULL,
  `process_done` boolean DEFAULT false,  
  `sequence_id` INT(11) DEFAULT NULL,
  `event_id` BIGINT(20) UNSIGNED NOT NULL,
  `expired_on` DATETIME NULL default NULL,
  `posted_on` DATETIME NOT NULL,
  `updated_on` DATETIME NOT NULL,
  `version` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0, 
  `created_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY  (`item_id`),
  UNIQUE KEY `item_id_UNIQUE` (`item_id`),
  KEY `event_ref` (`event_id`),
  CONSTRAINT `event_ref` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;


DROP TABLE IF EXISTS `file_system`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE  TABLE `file_system` (
  `uid` BIGINT UNSIGNED NOT NULL,
  `file_policy_id` BIGINT DEFAULT 1,
  `server` VARCHAR(100) NOT NULL ,
  `file_id1` VARCHAR(45) NOT NULL ,
  `file_id2` VARCHAR(45) NULL ,
  `file_id3` VARCHAR(45) NULL ,
  `used_space` BIGINT NULL ,
  `event_count` INT NULL DEFAULT 0 ,
  `file_count` INT NULL DEFAULT 0 ,
  `updated_on` DATETIME NOT NULL,
  `version` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0, 
  `created_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `file_id1_UNIQUE` (`file_id1`),
  UNIQUE KEY `file_id2_UNIQUE` (`file_id2`),
  UNIQUE KEY `file_id3_UNIQUE` (`file_id3`))
ENGINE = InnoDB DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;



DROP TABLE IF EXISTS `file_policy`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE  TABLE IF NOT EXISTS `file_policy` (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  policy_id BIGINT UNSIGNED NOT NULL DEFAULT 1,
  name VARCHAR(50) NOT NULL ,
  value VARCHAR(100) NOT NULL ,    
  updated_on DATETIME NOT NULL ,
  version BIGINT(20) UNSIGNED NOT NULL DEFAULT 0, 
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (id),
  KEY `event_ref` (`policy_id`) )
ENGINE = InnoDB;
SET character_set_client = @saved_cs_client;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-05-19 20:46:33

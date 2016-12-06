CREATE DATABASE  IF NOT EXISTS `mobilecanteen` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `mobilecanteen`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: mobilecanteen
-- ------------------------------------------------------
-- Server version	5.7.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ementa`
--

DROP TABLE IF EXISTS ementa;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE ementa (
  id int(11) NOT NULL,
  diadasemana varchar(45) NOT NULL,
  `data` date NOT NULL,
  refeicao varchar(45) NOT NULL,
  sopa varchar(45) NOT NULL,
  carne varchar(45) NOT NULL,
  peixe varchar(45) NOT NULL,
  vegetariano varchar(45) NOT NULL,
  sobremesa varchar(45) NOT NULL,
  PRIMARY KEY (id)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ementa`
--


--
-- Table structure for table `estudante`
--

DROP TABLE IF EXISTS estudante;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE estudante (
  id int(11) NOT NULL,
  nome varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  email varchar(45) NOT NULL,
  divida double NOT NULL DEFAULT '0',
  PRIMARY KEY (id)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudante`
--

INSERT INTO estudante VALUES (2150363,'Ryan','ryan','ryan@ryan.com',0);
INSERT INTO estudante VALUES (2150380,'João Lobo','lobo','joaolobo@lobo.com',0);
INSERT INTO estudante VALUES (2150393,'André','andre','andre@andre.com',0);

--
-- Table structure for table `fatura`
--

DROP TABLE IF EXISTS fatura;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE fatura (
  id int(11) NOT NULL,
  `data` datetime NOT NULL,
  preco float NOT NULL,
  cantina varchar(45) NOT NULL,
  refeicao varchar(45) NOT NULL,
  prato varchar(45) NOT NULL,
  id_estudante int(11) NOT NULL,
  PRIMARY KEY (id),
  KEY fk_estudante_faturas_idx (id_estudante),
  CONSTRAINT fk_estudante_fatura FOREIGN KEY (id_estudante) REFERENCES estudante (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fatura`
--


--
-- Table structure for table `migration`
--

DROP TABLE IF EXISTS migration;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE migration (
  version varchar(180) NOT NULL,
  apply_time int(11) DEFAULT NULL,
  PRIMARY KEY (version)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `migration`
--

INSERT INTO migration VALUES ('m000000_000000_base',1479725260);
INSERT INTO migration VALUES ('m130524_201442_init',1479725262);

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS user;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  id int(11) NOT NULL,
  username varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  auth_key varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  password_hash varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  password_reset_token varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  email varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` smallint(6) NOT NULL DEFAULT '10',
  created_at int(11) NOT NULL,
  updated_at int(11) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY username (username),
  UNIQUE KEY email (email),
  UNIQUE KEY password_reset_token (password_reset_token)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed

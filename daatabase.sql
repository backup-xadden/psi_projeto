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
-- Table structure for table `auth_assignment`
--

DROP TABLE IF EXISTS auth_assignment;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE auth_assignment (
  item_name varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  user_id varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  created_at int(11) DEFAULT NULL,
  PRIMARY KEY (item_name,user_id),
  CONSTRAINT auth_assignment_ibfk_1 FOREIGN KEY (item_name) REFERENCES auth_item (`name`) ON DELETE CASCADE ON UPDATE CASCADE
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_assignment`
--

INSERT INTO auth_assignment VALUES ('admin','1',1481215458);

--
-- Table structure for table `auth_item`
--

DROP TABLE IF EXISTS auth_item;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE auth_item (
  `name` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `type` int(11) NOT NULL,
  description text COLLATE utf8_unicode_ci,
  rule_name varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `data` text COLLATE utf8_unicode_ci,
  created_at int(11) DEFAULT NULL,
  updated_at int(11) DEFAULT NULL,
  PRIMARY KEY (`name`),
  KEY rule_name (rule_name),
  KEY `idx-auth_item-type` (`type`),
  CONSTRAINT auth_item_ibfk_1 FOREIGN KEY (rule_name) REFERENCES auth_rule (`name`) ON DELETE SET NULL ON UPDATE CASCADE
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_item`
--

INSERT INTO auth_item VALUES ('admin',1,NULL,NULL,NULL,1481215457,1481215457);
INSERT INTO auth_item VALUES ('uploadEmenta',2,'Upload ementa nova',NULL,NULL,1481215457,1481215457);

--
-- Table structure for table `auth_item_child`
--

DROP TABLE IF EXISTS auth_item_child;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE auth_item_child (
  parent varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  child varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (parent,child),
  KEY child (child),
  CONSTRAINT auth_item_child_ibfk_1 FOREIGN KEY (parent) REFERENCES auth_item (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT auth_item_child_ibfk_2 FOREIGN KEY (child) REFERENCES auth_item (`name`) ON DELETE CASCADE ON UPDATE CASCADE
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_item_child`
--

INSERT INTO auth_item_child VALUES ('admin','uploadEmenta');

--
-- Table structure for table `auth_rule`
--

DROP TABLE IF EXISTS auth_rule;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE auth_rule (
  `name` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `data` text COLLATE utf8_unicode_ci,
  created_at int(11) DEFAULT NULL,
  updated_at int(11) DEFAULT NULL,
  PRIMARY KEY (`name`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_rule`
--


--
-- Table structure for table `ementa`
--

DROP TABLE IF EXISTS ementa;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE ementa (
  id int(11) NOT NULL,
  diadasemana text NOT NULL,
  `data` text NOT NULL,
  refeicao text NOT NULL,
  sopa text NOT NULL,
  carne text NOT NULL,
  peixe text NOT NULL,
  vegetariano text NOT NULL,
  sobremesa text NOT NULL,
  haementa varchar(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (id)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ementa`
--

INSERT INTO ementa VALUES (1,'Segunda-feira','05-12-2016','almoco','Hortaliça','Perna de porco estufada com puré','Marmotas frita com feijão frade','Corgete á Brás','Fruta | doce | iogurte','1');
INSERT INTO ementa VALUES (2,'Segunda-feira','05-12-2016','jantar','Hortaliça','Almôndegas de tomatada com arroz','Pescada cozida com batata ovo e feijão verde','Tarte de arroz com nabiça','Fruta | doce | iogurte','1');
INSERT INTO ementa VALUES (3,'Terça-feira','06-12-2016','almoco','Agrião com cenoura','Strogonoff de Perú com fusilli','Tortilha de peixe com espinafres','Arroz de ervilhas com cogumelos','Fruta | doce | iogurte','1');
INSERT INTO ementa VALUES (4,'Terça-feira','06-12-2016','jantar','Agrião com cenoura','Vitela fatiada com puré','Lasanha de peixe e frutos do mar','Gratinado de queijo com castanhas','Fruta | doce | iogurte','1');
INSERT INTO ementa VALUES (5,'Quarta-feira','07-12-2016','almoco','Peixe','Empadão de vitela','Bacalhau à Brás','Quiche de cogumelos e espinafres','Fruta | doce | iogurte','1');
INSERT INTO ementa VALUES (6,'Quarta-feira','07-12-2016','jantar','Peixe','Bife á cervejaria com arroz e  batata frita','Solha frita com salada de feijão frade ou açorda','Couve de bruxelas salteadas','Fruta | doce | iogurte','1');
INSERT INTO ementa VALUES (7,'Quinta-feira','08-12-2016','almoco','ENCERRADO','ENCERRADO','ENCERRADO','ENCERRADO','ENCERRADO','0');
INSERT INTO ementa VALUES (8,'Quinta-feira','08-12-2016','jantar','ENCERRADO','ENCERRADO','ENCERRADO','ENCERRADO','ENCERRADO','0');
INSERT INTO ementa VALUES (9,'Sexta-feira','09-12-2016','almoco','Feijão verde','Frango com castanhas e arroz de açafrão','Lulas estufadas com batata cozida','Beringela á grega','Fruta | doce | iogurte','1');
INSERT INTO ementa VALUES (10,'Sexta-feira','09-12-2016','jantar','Feijão verde','Espetada de perú grelhada com arroz de ervilhas','Pescada á zé do Pipo com puré de batata','Strogonoff vegetariano','Fruta | doce | iogurte','1');

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
  id_user int(11) NOT NULL,
  PRIMARY KEY (id),
  KEY fk_estudante_fatura_idx (id_user),
  CONSTRAINT fk_estudante_fatura FOREIGN KEY (id_user) REFERENCES `user` (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fatura`
--

INSERT INTO fatura VALUES (1,'2015-05-11 00:00:00',2,'1','almoco','vegetariano',1);

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
INSERT INTO migration VALUES ('m140506_102106_rbac_init',1481214900);

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

INSERT INTO user VALUES (1,'2150393','6P1GAc3jvtNk4ivaoENRRHUa9kaoi_i7','$2y$13$uCRjpBDM2Xgqf0I3qOTRbeCy0yI0lPgyQMwRPQ/sfJ8WBczhCITLe',NULL,'andre@andre.com',10,1481211478,1481211478);
INSERT INTO user VALUES (2,'2150363','IQpYGlu-GPBUgNZ2_MTvm3WBJgXBJLEv','$2y$13$SOmPUpZoQWTpD0Wi2CNJy.SOztqpTF.bCae5LGKG1Y6DHfRWXKtTG',NULL,'ryan@ryan.com',10,1481211583,1481211583);
INSERT INTO user VALUES (3,'2150380','jvNpbV7fx5FIBSICnejdTe8bFcS-NnmK','$2y$13$nz8mgOCDXTVMvUltbd.EEeFv2J1MOI6RZJfzg898wvTTut.xa/dw2',NULL,'joao@joao.com',10,1481211616,1481211616);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed

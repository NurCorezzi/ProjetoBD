CREATE DATABASE  IF NOT EXISTS `csv_bd` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `csv_bd`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: csv_bd
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `aluno`
--

DROP TABLE IF EXISTS `aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aluno` (
  `CO_IES_ALUNO` int(5) NOT NULL,
  `CO_CURSO_ALUNO` int(7) NOT NULL,
  `CO_ALUNO_CURSO` int(8) NOT NULL,
  `CO_ALUNO_CURSO_ORIGEM` varchar(8) DEFAULT NULL,
  `CO_ALUNO` bigint(12) DEFAULT NULL,
  `DS_COR_RACA_ALUNO` varchar(32) DEFAULT NULL,
  `DS_SEXO_ALUNO` varchar(9) DEFAULT NULL,
  `NU_ANO_ALUNO_NASC` int(4) DEFAULT NULL,
  `NU_MES_ALUNO_NASC` int(2) DEFAULT NULL,
  `NU_DIA_ALUNO_NASC` int(2) DEFAULT NULL,
  `NU_IDADE_ALUNO` int(2) DEFAULT NULL,
  `DS_NACIONALIDADE_ALUNO` varchar(48) DEFAULT NULL,
  `IN_DEF_AUDITIVA` varchar(1) DEFAULT NULL,
  `IN_DEF_FISICA` varchar(1) DEFAULT NULL,
  `IN_DEF_INTELECTUAL` varchar(1) DEFAULT NULL,
  `IN_DEF_SUPERDOTACAO` varchar(1) DEFAULT NULL,
  `IN_TGD_AUTISMO_INFANTIL` varchar(1) DEFAULT NULL,
  `DS_ALUNO_SITUACAO` varchar(41) DEFAULT NULL,
  `IN_ING_VESTIBULAR` int(1) DEFAULT NULL,
  `IN_ING_ENEM` int(1) DEFAULT NULL,
  `IN_ING_AVALIACAO_SERIADA` int(1) DEFAULT NULL,
  `IN_RESERVA_RENDA_FAMILIAR` varchar(1) DEFAULT NULL,
  `IN_FIN_REEMB_FIES` varchar(1) DEFAULT NULL,
  `IN_FIN_NAOREEMB_PROUNI_INTEGR` varchar(1) DEFAULT NULL,
  `IN_BOLSA_ESTAGIO` varchar(1) DEFAULT NULL,
  `IN_BOLSA_PESQUISA` varchar(1) DEFAULT NULL,
  `IN_MATRICULA` int(1) DEFAULT NULL,
  `IN_CONCLUINTE` int(1) DEFAULT NULL,
  PRIMARY KEY (`CO_ALUNO_CURSO`),
  KEY `CO_IES_idx` (`CO_IES_ALUNO`),
  KEY `CO_CURSO_idx` (`CO_CURSO_ALUNO`),
  CONSTRAINT `CO_CURSO_ALUNO` FOREIGN KEY (`CO_CURSO_ALUNO`) REFERENCES `curso` (`CO_CURSO`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `CO_IES_ALUNO` FOREIGN KEY (`CO_IES_ALUNO`) REFERENCES `ies` (`CO_IES`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curso` (
  `CO_IES_CURSO` int(5) NOT NULL,
  `NO_MUNICIPIO_CURSO` varchar(23) DEFAULT NULL,
  `SGL_UF_CURSO` varchar(2) DEFAULT NULL,
  `NO_REGIAO_CURSO` varchar(12) DEFAULT NULL,
  `CO_CURSO` int(7) NOT NULL,
  `NO_CURSO` varchar(86) DEFAULT NULL,
  `DS_SITUACAO_CURSO` varchar(12) DEFAULT NULL,
  `DS_GRAU_ACADEMICO` varchar(13) DEFAULT NULL,
  `DS_MODALIDADE_ENSINO` varchar(17) DEFAULT NULL,
  `DS_NIVEL_ACADEMICO` varchar(33) DEFAULT NULL,
  `IN_GRATUITO` varchar(1) DEFAULT NULL,
  `CO_LOCAL_OFERTA` varchar(7) DEFAULT NULL,
  `NU_CARGA_HORARIA` int(4) DEFAULT NULL,
  `DT_INICIO_FUNCIONAMENTO` varchar(10) DEFAULT NULL,
  `IN_AJUDA_DEFICIENTE` int(1) DEFAULT NULL,
  `IN_MATERIAL_DIGITAL` varchar(1) DEFAULT NULL,
  `IN_RECURSOS_INFORMATICA` varchar(1) DEFAULT NULL,
  `IN_INTEGRAL_CURSO` varchar(1) DEFAULT NULL,
  `IN_MATUTINO_CURSO` varchar(1) DEFAULT NULL,
  `IN_VESPERTINO_CURSO` varchar(1) DEFAULT NULL,
  `IN_NOTURNO_CURSO` varchar(1) DEFAULT NULL,
  `NU_INTEGRALIZACAO_INTEGRAL` varchar(2) DEFAULT NULL,
  `QT_VAGAS_NOVAS_INTEGRAL` varchar(3) DEFAULT NULL,
  `QT_VAGAS_NOVAS_MATUTINO` varchar(3) DEFAULT NULL,
  `QT_VAGAS_NOVAS_VESPERTINO` varchar(3) DEFAULT NULL,
  `QT_MATRICULA_CURSO` int(4) DEFAULT NULL,
  `QT_CONCLUINTE_CURSO` varchar(3) DEFAULT NULL,
  `QT_INGRESSO_CURSO` int(3) DEFAULT NULL,
  PRIMARY KEY (`CO_CURSO`),
  KEY `CO_IES_idx` (`CO_IES_CURSO`),
  CONSTRAINT `CO_IES` FOREIGN KEY (`CO_IES_CURSO`) REFERENCES `ies` (`CO_IES`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `docente`
--

DROP TABLE IF EXISTS `docente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `docente` (
  `CO_IES_DOCENTE` int(5) NOT NULL,
  `CO_DOCENTE_IES` int(6) NOT NULL,
  `CO_DOCENTE` bigint(12) DEFAULT NULL,
  `DS_SITUACAO_DOCENTE` varchar(50) DEFAULT NULL,
  `DS_ESCOLARIDADE_DOCENTE` varchar(14) DEFAULT NULL,
  `DS_REGIME_TRABALHO` varchar(38) DEFAULT NULL,
  `DS_SEXO_DOCENTE` varchar(9) DEFAULT NULL,
  `NU_ANO_DOCENTE_NASC` int(4) DEFAULT NULL,
  `NU_MES_DOCENTE_NASC` int(2) DEFAULT NULL,
  `NU_DIA_DOCENTE_NASC` int(2) DEFAULT NULL,
  `NU_IDADE_DOCENTE` int(2) DEFAULT NULL,
  `DS_COR_RACA_DOCENTE` varchar(34) DEFAULT NULL,
  `DS_NACIONALIDADE_DOCENTE` varchar(48) DEFAULT NULL,
  `CO_UF_NASCIMENTO` varchar(2) DEFAULT NULL,
  `CO_MUNICIPIO_NASCIMENTO` varchar(7) DEFAULT NULL,
  `IN_DOCENTE_DEFICIENCIA` int(1) DEFAULT NULL,
  `IN_ATU_GRAD_PRESENCIAL` varchar(1) DEFAULT NULL,
  `IN_ATU_POS_EAD` varchar(1) DEFAULT NULL,
  `IN_ATU_PESQUISA` varchar(1) DEFAULT NULL,
  `IN_BOLSA_PESQUISA` varchar(1) DEFAULT NULL,
  `IN_SUBSTITUTO` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`CO_DOCENTE_IES`),
  KEY `CO_IES_idx` (`CO_IES_DOCENTE`),
  CONSTRAINT `CO_IES_DOCENTE` FOREIGN KEY (`CO_IES_DOCENTE`) REFERENCES `ies` (`CO_IES`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ies`
--

DROP TABLE IF EXISTS `ies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ies` (
  `CO_IES` int(11) NOT NULL,
  `NO_IES` text,
  `SGL_IES` text,
  `NO_MANTENEDORA` text,
  `DS_CATEGORIA_ADMINISTRATIVA` text,
  `DS_ORGANIZACAO_ACADEMICA` text,
  `NO_MUNICIPIO_IES` text,
  `SGL_UF_IES` text,
  `NO_REGIAO_IES` text,
  `QT_TEC_TOTAL` int(11) DEFAULT NULL,
  `QT_TEC_MEDIO_FEM` int(11) DEFAULT NULL,
  `QT_TEC_MEDIO_MASC` int(11) DEFAULT NULL,
  `QT_TEC_SUPERIOR_FEM` int(11) DEFAULT NULL,
  `QT_TEC_SUPERIOR_MASC` int(11) DEFAULT NULL,
  `QT_TEC_MESTRADO_FEM` int(11) DEFAULT NULL,
  `QT_TEC_MESTRADO_MASC` int(11) DEFAULT NULL,
  `QT_TEC_DOUTORADO_FEM` int(11) DEFAULT NULL,
  `QT_TEC_DOUTORADO_MASC` int(11) DEFAULT NULL,
  `IN_ACESSO_PORTAL_CAPES` int(11) DEFAULT NULL,
  `VL_RECEITA_PROPRIA` double DEFAULT NULL,
  `VL_DES_PESSOAL_REM_DOCENTE` double DEFAULT NULL,
  `VL_DES_PESSOAL_REM_TECNICO` double DEFAULT NULL,
  `VL_DES_PESSOAL_ENCARGO` double DEFAULT NULL,
  `VL_DES_CUSTEIO` double DEFAULT NULL,
  `VL_DES_INVESTIMENTO` double DEFAULT NULL,
  `VL_DES_PESQUISA` double DEFAULT NULL,
  `QT_DOCENTE_TOTAL` bigint(21) DEFAULT NULL,
  `QT_ALUNO_TOTAL` bigint(21) DEFAULT NULL,
  PRIMARY KEY (`CO_IES`),
  UNIQUE KEY `CO_IES_UNIQUE` (`CO_IES`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER check_person_count BEFORE UPDATE ON ies  
FOR EACH ROW
BEGIN
	if ((SELECT count(*) FROM information_schema.COLUMNS 
		WHERE 	TABLE_SCHEMA = 'csv_bd' 
				AND TABLE_NAME = 'ies' 
				AND COLUMN_NAME = 'QT_DOCENTE_TOTAL') <> 0 ) then
	begin
		if (NEW.QT_DOCENTE_TOTAL <= 0 OR NEW.QT_ALUNO_TOTAL <= 0 OR NEW.QT_TEC_TOTAL <= 0) THEN
		begin
			SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Valor de alunos, tecnicos e docentes não pode ser menor ou igual a zero ', MYSQL_ERRNO = 1001;	
		end;
        end if;
    end;
    end if;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Temporary view structure for view `ies_pessoal`
--

DROP TABLE IF EXISTS `ies_pessoal`;
/*!50001 DROP VIEW IF EXISTS `ies_pessoal`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `ies_pessoal` AS SELECT 
 1 AS `CO_IES`,
 1 AS `NO_IES`,
 1 AS `DS_CATEGORIA_ADMINISTRATIVA`,
 1 AS `QT_TEC_TOTAL`,
 1 AS `QT_DOCENTE_TOTAL`,
 1 AS `QT_ALUNO_TOTAL`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `local_oferta`
--

DROP TABLE IF EXISTS `local_oferta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `local_oferta` (
  `CO_LOCAL_OFERTA_IES` int(7) NOT NULL,
  `NO_LOCAL_OFERTA` varchar(80) DEFAULT NULL,
  `CO_IES_LOCAL` int(5) NOT NULL,
  `CO_UF_LOCAL_OFERTA` int(2) DEFAULT NULL,
  `SGL_UF_LOCAL_OFERTA` varchar(2) DEFAULT NULL,
  `CO_MUNICIPIO_LOCAL_OFERTA` int(7) DEFAULT NULL,
  `NO_MUNICIPIO_LOCAL_OFERTA` varchar(25) DEFAULT NULL,
  `IN_SEDE` varchar(1) DEFAULT NULL,
  `CO_CURSO_POLO` varchar(6) DEFAULT NULL,
  `CO_CURSO_LOCAL` int(7) NOT NULL,
  `IN_LOCAL_OFERTA_NEAD` int(1) DEFAULT NULL,
  `IN_LOCAL_OFERTA_UAB` int(1) DEFAULT NULL,
  `IN_LOCAL_OFERTA_REITORIA` int(1) DEFAULT NULL,
  `IN_LOCAL_OFERTA_POLO` int(1) DEFAULT NULL,
  `IN_LOCAL_OFERTA_UNID_ACADEMICA` int(1) DEFAULT NULL,
  `DT_INICIO_FUNCIONAMENTO` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`CO_LOCAL_OFERTA_IES`,`CO_CURSO_LOCAL`),
  KEY `CO_CURSO_idx` (`CO_CURSO_LOCAL`),
  KEY `CO_IES_idx` (`CO_IES_LOCAL`),
  CONSTRAINT `CO_CURSO_LOCAL` FOREIGN KEY (`CO_CURSO_LOCAL`) REFERENCES `curso` (`CO_CURSO`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `CO_IES_LOCAL` FOREIGN KEY (`CO_IES_LOCAL`) REFERENCES `ies` (`CO_IES`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'csv_bd'
--

--
-- Dumping routines for database 'csv_bd'
--
/*!50003 DROP FUNCTION IF EXISTS `getRaca` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getRaca`(escolaridade varchar(14)) RETURNS varchar(34) CHARSET utf8
BEGIN
	declare raca varchar(34) ;

	select DS_COR_RACA_DOCENTE into raca 
				 			   	from (select DC.DS_COR_RACA_DOCENTE, 
				 			   				  count(*)
				 			   		   from docente as DC join ies as IE on DC.CO_IES = IE.CO_IES 
									   where IE.NO_REGIAO_IES = 'Centro-Oeste' 
				 			   				 and DC.DS_SEXO_DOCENTE = 'MASCULINO'
                                             and DC.DS_ESCOLARIDADE_DOCENTE = escolaridade
				 			   		   group by DC.DS_COR_RACA_DOCENTE
				 			   		   order by count(*) desc limit 1) as Tabbs;
    return name;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_ies_table` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_ies_table`()
BEGIN
	if ((SELECT count(*) FROM information_schema.COLUMNS 
		WHERE 	TABLE_SCHEMA = 'csv_bd' 
				AND TABLE_NAME = 'ies' 
				AND COLUMN_NAME = 'QT_DOCENTE_TOTAL') = 0 ) then
	begin
		ALTER TABLE ies 
		ADD COLUMN QT_DOCENTE_TOTAL bigint(21) default null,
		ADD COLUMN QT_ALUNO_TOTAL bigint(21) default null;
    end;
    end if;
    
    
    begin
		declare i int(4);
        select count(*) into i from ies where QT_DOCENTE_TOTAL is null;
        
        if(i <> 0)then
			BEGIN
			CREATE TABLE if not exists temp_tab as select * from ies_pessoal;
			
			while(i != 0)do
			begin
				declare cod_ies int(4);
				
				select CO_IES into cod_ies from ies where QT_DOCENTE_TOTAL is null limit 0,1;
				
				update ies
				set QT_DOCENTE_TOTAL = (select QT_DOCENTE_TOTAL from temp_tab where CO_IES = cod_ies),
					QT_ALUNO_TOTAL = (select QT_ALUNO_TOTAL from temp_tab where CO_IES = cod_ies)
				where CO_IES = cod_ies;
				
				select i-1 into i;
			end;
			end while;
			
			drop table temp_tab;
			end;
		end if;
        
    end;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `ies_pessoal`
--

/*!50001 DROP VIEW IF EXISTS `ies_pessoal`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `ies_pessoal` AS select `i`.`CO_IES` AS `CO_IES`,`i`.`NO_IES` AS `NO_IES`,`i`.`DS_CATEGORIA_ADMINISTRATIVA` AS `DS_CATEGORIA_ADMINISTRATIVA`,`i`.`QT_TEC_TOTAL` AS `QT_TEC_TOTAL`,(select count(0) from `docente` `d` where ((`d`.`CO_IES_DOCENTE` = `i`.`CO_IES`) and (`d`.`DS_SITUACAO_DOCENTE` = 'Em exerc?cio'))) AS `QT_DOCENTE_TOTAL`,(select count(0) from `aluno` `a` where ((`a`.`CO_IES_ALUNO` = `i`.`CO_IES`) and (`a`.`DS_ALUNO_SITUACAO` = 'Cursando'))) AS `QT_ALUNO_TOTAL` from `ies` `i` */;
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

-- Dump completed on 2017-11-25 12:39:41

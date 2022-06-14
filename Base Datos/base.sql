CREATE DATABASE `restaurante` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `restaurante`;

CREATE TABLE `alimentos` (
  `id_alimento` int NOT NULL AUTO_INCREMENT,
  `alimento` varchar(50) CHARACTER SET latin1 NOT NULL,
  `precio` decimal(5,2) NOT NULL,
  PRIMARY KEY (`id_alimento`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

CREATE TABLE `bebidas` (
  `id_bebida` int NOT NULL AUTO_INCREMENT,
  `bebida` varchar(50) CHARACTER SET latin1 NOT NULL,
  `precio` decimal(5,2) NOT NULL,
  PRIMARY KEY (`id_bebida`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

CREATE TABLE `factura` (
  `id_factura` int NOT NULL AUTO_INCREMENT,
  `id_alimento` int,
  `id_bebida` int,
  `subtotal` decimal(5,2) NOT NULL,
  PRIMARY KEY (`id_factura`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `restaurante`.`vistaalimentos` AS select `restaurante`.`alimentos`.`id_alimento` AS `id_alimento`,`restaurante`.`alimentos`.`alimento` AS `alimento`, `restaurante`.`alimentos`.`precio` AS `precio` from `restaurante`.`alimentos`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `restaurante`.`vistabebidas` AS select `restaurante`.`bebidas`.`id_bebida` AS `id_bebida`,`restaurante`.`bebidas`.`bebida` AS `bebida`, `restaurante`.`bebidas`.`precio` AS `precio` from `restaurante`.`bebidas`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `restaurante`.`vistafactura` AS select `restaurante`.`factura`.`id_factura` AS `id_factura`,`restaurante`.`factura`.`id_alimento` AS `id_alimento`,`restaurante`.`factura`.`id_bebida` AS `id_bebida`,`restaurante`.`factura`.`subtotal` AS `subtotal` from (`restaurante`.`alimentos` join `restaurante`.`bebidas` join `restaurante`.`factura`) where (`restaurante`.`alimentos`.`id_alimento` = `restaurante`.`factura`.`id_alimento`) and (`restaurante`.`bebidas`.`id_bebida` = `restaurante`.`factura`.`id_bebida`);

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `BorrarDatos`(IN CodigoB INT)
    READS SQL DATA
BEGIN 
    DECLARE error INT ;
    START TRANSACTION;
    DELETE FROM factura WHERE id_factura  = CodigoB;
    SET error = (SELECT @error);
    IF(error=0) THEN 
    ROLLBACK; 
    ELSE COMMIT;
    END IF;
    END$$
DELIMITER ;



DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertarDatos`(IN id_factura INT, id_alimento INT, id_bebida INT, subtotal decimal(5,2))
    READS SQL DATA
BEGIN DECLARE error INT; 
START TRANSACTION; 
INSERT INTO factura VALUES(id_factura, id_alimento, id_bebida, subtotal); 
SET error=(SELECT @error); 
IF(error=0)THEN ROLLBACK; 
ELSE COMMIT; 
END IF; 
END$$
DELIMITER ;

DELIMITER $$

DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarDatos`(IN id_facturaM INT, id_alimentoM INT, id_bebidaM INT, subtotalM decimal(5,2))
BEGIN 
 DECLARE error INT; 
 START TRANSACTION; 
 UPDATE factura SET id_factura=id_facturaM, id_alimento=id_alimentoM, id_bebida=id_bebidaM, subtotal=subtotalM 
 WHERE id_factura=id_facturaM; 
 SET error=(SELECT @error); 
 IF(error=0)THEN 
 ROLLBACK; 
 ELSE 
 COMMIT; 
 END IF; 
 END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `verifyUser`(IN pusuario TEXT, IN ppassword TEXT, OUT outCout INT)
    READS SQL DATA
BEGIN  
    SELECT id_usuario INTO outCout FROM usuario WHERE usuario  = pusuario and password = ppassword;    
    END$$
DELIMITER ;
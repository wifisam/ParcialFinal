CREATE DATABASE tienda;
USE tienda;

CREATE TABLE `producto` (
  `idproducto` int NOT NULL AUTO_INCREMENT COMMENT 'Llave principal PK',
  `descripcion` varchar(25) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Almacena descripcion del producto',
  `precio` double DEFAULT '0' COMMENT 'Para almacena precio double 5.23',
  `estado` varchar(1) COLLATE utf8_spanish_ci DEFAULT 'A' COMMENT 'para indicar si esta activo=A o desactivo=D',
  PRIMARY KEY (`idproducto`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(120) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(220) COLLATE utf8_spanish_ci NOT NULL,
  `country` varchar(120) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

INSERT INTO `users` (`id`,`name`,`email`,`country`) VALUES (1,'Alex','alex@gmail.com','ESV');
INSERT INTO `users` (`id`,`name`,`email`,`country`) VALUES (2,'Pedro','pedro@gmail.com','GUA');
INSERT INTO `users` (`id`,`name`,`email`,`country`) VALUES (4,'Diego','diego@gmail.com','ESP');

INSERT INTO `producto` (`idproducto`,`descripcion`,`precio`,`estado`) VALUES (1,'TV',500.23,'A');
INSERT INTO `producto` (`idproducto`,`descripcion`,`precio`,`estado`) VALUES (2,'PC',10.23,'A');
INSERT INTO `producto` (`idproducto`,`descripcion`,`precio`,`estado`) VALUES (5,'Mouse',8.56,'D');
INSERT INTO `producto` (`idproducto`,`descripcion`,`precio`,`estado`) VALUES (6,'Mouse',45,'A');
INSERT INTO `producto` (`idproducto`,`descripcion`,`precio`,`estado`) VALUES (7,'Mouse',4,'A');
INSERT INTO `producto` (`idproducto`,`descripcion`,`precio`,`estado`) VALUES (8,'Mouse',7,'D');

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `vistaproducto` AS
    SELECT 
        `producto`.`idproducto` AS `idproducto`,
        `producto`.`descripcion` AS `descripcion`,
        `producto`.`precio` AS `precio`,
        `producto`.`estado` AS `estado`
    FROM
        `producto`;
        


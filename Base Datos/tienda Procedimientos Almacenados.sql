CREATE PROCEDURE `crearProducto`(Pdescripcion VARCHAR(25), 
Pprecio DOUBLE, 
Pestado VARCHAR(1)
)
BEGIN 
 DECLARE error INT; 
	START TRANSACTION; 
	INSERT INTO producto (descripcion,precio,estado) VALUES(Pdescripcion, Pprecio, Pestado); 
	SET error=(SELECT @error); 
	 IF(error=0)THEN ROLLBACK; 
	  ELSE COMMIT; 
	 END IF; 
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarProducto`(IN Pidproducto INT)
BEGIN 
 DECLARE error INT ;
 START TRANSACTION;
 DELETE FROM producto WHERE idproducto  = Pidproducto;
 SET error = (SELECT @error);
 IF(error=0) THEN 
  ROLLBACK; 
 ELSE 
  COMMIT;
 END IF;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarProducto`(Pidproducto INT, 
Pdescripcion VARCHAR(25), 
Pprecio DOUBLE, 
Pestado VARCHAR(1)
)
BEGIN
 DECLARE error INT; 
 START TRANSACTION; 
 UPDATE producto SET descripcion =Pdescripcion ,precio=Pprecio ,estado=Pestado 
 WHERE idproducto = Pidproducto;  
 SET error=(SELECT @error); 
 IF(error=0)THEN 
   ROLLBACK; 
 ELSE 
   COMMIT; 
 END IF; 
END


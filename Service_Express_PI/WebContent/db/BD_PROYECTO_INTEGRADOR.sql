DROP DATABASE IF exists PROYECTO_INTEGRADOR;

CREATE DATABASE PROYECTO_INTEGRADOR;

 DROP DATABASE IF EXISTS PROYECTO_INTEGRADOR;
 CREATE DATABASE PROYECTO_INTEGRADOR;
 
USE PROYECTO_INTEGRADOR;

DROP TABLE IF exists TB_DISTRITO;
CREATE TABLE TB_DISTRITO
(cod_distrito int auto_increment primary key,
des_distrito varchar(40)
);

DROP TABLE IF exists TB_CLIENTE;
CREATE TABLE TB_CLIENTE(
cod_cliente int auto_increment primary key,
nom_cliente varchar(40) ,
ape_cliente varchar(50),
email_cliente varchar(60),
cel_cliente varchar(15),
con_cliente varchar(25),
foto  varchar(100)
);

DROP TABLE IF exists TB_USUARIO;
CREATE TABLE TB_USUARIO(
cod_usuario int auto_increment primary key,
nom_usuario varchar(40) ,
ape_usuario varchar(50),
email_usuario varchar(60),
cel_usuario varchar(15),
con_usuario varchar(25),
foto  varchar(50),
tipo enum('COURIER','ADMINISTRADOR')NOT NULL DEFAULT 'COURIER'
);



DROP TABLE IF exists TB_PEDIDOS;

CREATE TABLE TB_PEDIDOS(

	codigo int auto_increment primary key,
	direccion_ini varchar(150) ,
	distrito1 int references TB_DISTRITO ,
	remitente varchar(100) ,
	celular1 varchar(15) ,
    direccion_fin varchar(150),
	distrito2 int references TB_DISTRITO,
	receptor varchar(100) ,
	celular2 varchar(15) ,
	instrucciones varchar(200),
	documento_peq int ,
	documento_med int ,
	documento_gra int ,
	kilometros varchar(10),
	tiempo varchar(20),
	precio decimal,
    fechaRegistro DATETIME ,
    cod_cliente INT REFERENCES TB_CLIENTE,
    estado enum('POR CONFIRMAR', 'ASIGNADO' ,'RECOGIDO','COMPLETADO','CANCELADO') NOT NULL DEFAULT 'POR CONFIRMAR',
	cod_usuario int DEFAULT NULL references tb_usuario,
    pago varchar(20),
    soles decimal
);

-- INSERT PARA LA TABLA TB_DISTRITO--
-- ******************************************************************
INSERT INTO TB_DISTRITO VALUES(null,'Ancón');
INSERT INTO TB_DISTRITO VALUES(null,'Ate');
INSERT INTO TB_DISTRITO VALUES(null,'Barranco');
INSERT INTO TB_DISTRITO VALUES(null,'Breña');
INSERT INTO TB_DISTRITO VALUES(null,'Carabayllo');
INSERT INTO TB_DISTRITO VALUES(null,'Chaclacayo');
INSERT INTO TB_DISTRITO VALUES(null,'Chorrillos');
INSERT INTO TB_DISTRITO VALUES(null,'Cieneguilla');
INSERT INTO TB_DISTRITO VALUES(null,'Comas');
INSERT INTO TB_DISTRITO VALUES(null,'El Agustino');
INSERT INTO TB_DISTRITO VALUES(null,'Independencia');
INSERT INTO TB_DISTRITO VALUES(null,'Jesús María');
INSERT INTO TB_DISTRITO VALUES(null,'La Molina');
INSERT INTO TB_DISTRITO VALUES(null,'La Victoria');
INSERT INTO TB_DISTRITO VALUES(null,'Lima');
INSERT INTO TB_DISTRITO VALUES(null,'Lince');
INSERT INTO TB_DISTRITO VALUES(null,'Los Olivos');
INSERT INTO TB_DISTRITO VALUES(null,'Lurigancho');
INSERT INTO TB_DISTRITO VALUES(null,'Lurín');
INSERT INTO TB_DISTRITO VALUES(null,'Magdalena del Mar');
INSERT INTO TB_DISTRITO VALUES(null,'Miraflores');
INSERT INTO TB_DISTRITO VALUES(null,'Pachacamac');
INSERT INTO TB_DISTRITO VALUES(null,'Pucusana');
INSERT INTO TB_DISTRITO VALUES(null,'Pueblo Libre');
INSERT INTO TB_DISTRITO VALUES(null,'Puente Piedra');
INSERT INTO TB_DISTRITO VALUES(null,'Punta Hermosa');
INSERT INTO TB_DISTRITO VALUES(null,'Punta Negra');
INSERT INTO TB_DISTRITO VALUES(null,'Rímac');
INSERT INTO TB_DISTRITO VALUES(null,'San Bartolo');
INSERT INTO TB_DISTRITO VALUES(null,'San Borja');
INSERT INTO TB_DISTRITO VALUES(null,'San Isidro');
INSERT INTO TB_DISTRITO VALUES(null,'San Juan de Lurigancho');
INSERT INTO TB_DISTRITO VALUES(null,'San Juan de Miraflores');
INSERT INTO TB_DISTRITO VALUES(null,'San Luis');
INSERT INTO TB_DISTRITO VALUES(null,'San Martín de Porres');
INSERT INTO TB_DISTRITO VALUES(null,'San Miguel');
INSERT INTO TB_DISTRITO VALUES(null,'Santa Anita');
INSERT INTO TB_DISTRITO VALUES(null,'Santa María del Mar');
INSERT INTO TB_DISTRITO VALUES(null,'Santa Rosa');
INSERT INTO TB_DISTRITO VALUES(null,'Santiago de Surco');
INSERT INTO TB_DISTRITO VALUES(null,'Surquillo');
INSERT INTO TB_DISTRITO VALUES(null,'Villa El Salvador');
INSERT INTO TB_DISTRITO VALUES(null,'Villa María del Triunfo');


-- ******************************************************************

-- INSERT PARA LA TABLA TB_USUARIO--
-- ******************************************************************
insert into tb_usuario values(null,'Alex','Acuña','alx@gmail.com','989898989','123',null,'ADMINISTRADOR');
insert into tb_usuario values(null,'Siamy','Proleon Poma','mspoma@gmail.com','9999222112','12345',null,'COURIER');
-- ******************************************************************

-- consulta de las tablas--
-- ******************************************************************
select * from  TB_CLIENTE;
select * from tb_usuario;
select * from TB_PEDIDOS;
-- ******************************************************************



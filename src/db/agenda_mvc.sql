CREATE DATABASE agenda_mvc;

USE agenda_mvc;

CREATE TABLE contactos( 
    id_contacto integer NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(50) NOT NULL,
    email varchar(50) NOT NULL,
    telefono varchar(10) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO contactos (nombre, email, telefono) VALUES 
('Esteban','steph@steph.com','7757578912'), 
('Jessica','jess@jessy.com', '7757965965'),
('Andrea','andrea@andy.com','7759567369');


SELECT * FROM contactos;

CREATE USER 'esteban'@'localhost' IDENTIFIED BY 'esteban';
GRANT ALL PRIVILEGES ON agenda_mvc.* TO 'esteban'@'localhost';
FLUSH PRIVILEGES;

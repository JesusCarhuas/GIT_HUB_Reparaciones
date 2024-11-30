
-- =============================================
-- Seleccionar la base de datos
-- =============================================

use REPARACIONES_UNI;


-- =============================================
-- Cargar Datos de Prueba
-- =============================================

-- Tabla: TECNICO


-- Inserciones en la tabla TECNICO
INSERT INTO dbo.TECNICO (Nombre, ApellidoPaternoT, ApellidoMaternoT, TecnicoDni, Direccion, Telefono, Email, Contrasena, Activo)
VALUES 
('Carlos', 'Ramirez', 'Lopez', '12345678', 'Av. Los Olivos 123', '999888777', 'carlos.ramirez@example.com', 'contrasenaSegura123', 0);

INSERT INTO dbo.TECNICO (Nombre, ApellidoPaternoT, ApellidoMaternoT, TecnicoDni, Direccion, Telefono, Email, Contrasena, Activo)
VALUES 
('María', 'Fernandez', 'Gomez', '87654321', 'Jr. Las Flores 456', '988777666', 'maria.fernandez@example.com', 'claveFuerte456', 0);

INSERT INTO dbo.TECNICO (Nombre, ApellidoPaternoT, ApellidoMaternoT, TecnicoDni, Direccion, Telefono, Email, Contrasena, Activo)
VALUES 
('Luis', 'Sanchez', 'Perez', '11223344', 'Calle Los Pinos 789', '977666555', 'luis.sanchez@example.com', 'passSegura789', 0);

INSERT INTO dbo.TECNICO (Nombre, ApellidoPaternoT, ApellidoMaternoT, TecnicoDni, Direccion, Telefono, Email, Contrasena, Activo)
VALUES 
('Ana', 'Torres', 'Rojas', '55667788', 'Av. El Sol 321', '966555444', 'ana.torres@example.com', 'seguraClave1010', 0);

INSERT INTO dbo.TECNICO (Nombre, ApellidoPaternoT, ApellidoMaternoT, TecnicoDni, Direccion, Telefono, Email, Contrasena, Activo)
VALUES 
('Jorge', 'Castillo', 'Vega', '33445566', 'Jr. La Luna 654', '955444333', 'jorge.castillo@example.com', 'passwordSeguro2024', 0);


-- Tabla: ESTADO

INSERT INTO dbo.ESTADO (Descripcion) VALUES ('EN PROCESO');
INSERT INTO dbo.ESTADO (Descripcion) VALUES ('REPARADO');
INSERT INTO dbo.ESTADO (Descripcion) VALUES ('LISTO PARA ENTREGAR');
INSERT INTO dbo.ESTADO (Descripcion) VALUES ('FINALIZADO');



-- Tabla: TIPOITEM

INSERT INTO dbo.TIPOITEM (NombreTipo) VALUES ('Procesador');
INSERT INTO dbo.TIPOITEM (NombreTipo) VALUES ('Tarjeta Madre');
INSERT INTO dbo.TIPOITEM (NombreTipo) VALUES ('Memoria RAM');
INSERT INTO dbo.TIPOITEM (NombreTipo) VALUES ('Disco Duro / SSD');
INSERT INTO dbo.TIPOITEM (NombreTipo) VALUES ('Fuente de Poder');
INSERT INTO dbo.TIPOITEM (NombreTipo) VALUES ('Tarjeta Gráfica');
INSERT INTO dbo.TIPOITEM (NombreTipo) VALUES ('Enfriamiento / Ventiladores');
INSERT INTO dbo.TIPOITEM (NombreTipo) VALUES ('Pantalla / Monitor');
INSERT INTO dbo.TIPOITEM (NombreTipo) VALUES ('Teclado');
INSERT INTO dbo.TIPOITEM (NombreTipo) VALUES ('Mouse');
INSERT INTO dbo.TIPOITEM (NombreTipo) VALUES ('Batería');
INSERT INTO dbo.TIPOITEM (NombreTipo) VALUES ('Cables / Conectores');
INSERT INTO dbo.TIPOITEM (NombreTipo) VALUES ('Unidad Óptica / DVD');
INSERT INTO dbo.TIPOITEM (NombreTipo) VALUES ('Chasis / Carcasa');
INSERT INTO dbo.TIPOITEM (NombreTipo) VALUES ('Adaptador de Corriente');
INSERT INTO dbo.TIPOITEM (NombreTipo) VALUES ('Servicios');

-- Tabla: ITEM

-- Procesadores
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (1, 'Intel Core i9-13900K', 'Intel', 599.99, 50);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (1, 'AMD Ryzen 9 7900X', 'AMD', 569.99, 30);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (1, 'Intel Core i7-12700K', 'Intel', 399.99, 45);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (1, 'AMD Ryzen 7 7700X', 'AMD', 349.99, 40);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (1, 'Intel Core i5-13600K', 'Intel', 299.99, 60);

INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (1, 'AMD Ryzen 5 7600X', 'AMD', 359.99, 80);

-- Tarjetas Madres
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (2, 'ASUS ROG Strix Z690', 'ASUS', 299.99, 20);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (2, 'MSI MPG B550', 'MSI', 179.99, 25);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (2, 'Gigabyte AORUS X570', 'Gigabyte', 249.99, 18);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (2, 'ASRock Z590', 'ASRock', 189.99, 15);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (2, 'ASUS TUF Gaming B550', 'ASUS', 149.99, 40);

-- Memorias RAM
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (3, 'Corsair Vengeance LPX 16GB', 'Corsair', 79.99, 120);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (3, 'G.Skill Ripjaws V 32GB', 'G.Skill', 139.99, 60);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (3, 'Kingston HyperX Fury 16GB', 'Kingston', 69.99, 100);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (3, 'Crucial Ballistix 32GB', 'Crucial', 119.99, 50);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (3, 'Teamgroup T-Force Vulcan 16GB', 'Teamgroup', 64.99, 80);

-- Discos Duros / SSD
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (4, 'Samsung 970 EVO Plus 1TB', 'Samsung', 129.99, 70);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (4, 'WD Blue SN570 500GB', 'Western Digital', 49.99, 100);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (4, 'Crucial P3 1TB SSD', 'Crucial', 89.99, 60);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (4, 'Kingston A2000 1TB', 'Kingston', 74.99, 80);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (4, 'Seagate Barracuda 2TB', 'Seagate', 54.99, 50);

-- Fuentes de Poder
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (5, 'Corsair RM850x 850W', 'Corsair', 139.99, 60);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (5, 'EVGA SuperNOVA 650 G5', 'EVGA', 99.99, 70);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (5, 'Seasonic FOCUS GX 750W', 'Seasonic', 109.99, 80);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (5, 'Cooler Master MWE Gold 750W', 'Cooler Master', 89.99, 100);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (5, 'Thermaltake Toughpower GX2 600W', 'Thermaltake', 59.99, 50);

-- Tarjetas Gráficas
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (6, 'NVIDIA GeForce RTX 3080', 'NVIDIA', 699.99, 30);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (6, 'AMD Radeon RX 6800 XT', 'AMD', 649.99, 25);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (6, 'NVIDIA GeForce RTX 3070', 'NVIDIA', 499.99, 50);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (6, 'NVIDIA GeForce GTX 1660 Ti', 'NVIDIA', 279.99, 100);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (6, 'AMD Radeon RX 5700 XT', 'AMD', 399.99, 60);

-- Enfriamiento / Ventiladores
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (7, 'Cooler Master Hyper 212', 'Cooler Master', 39.99, 150);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (7, 'Noctua NH-D15', 'Noctua', 89.99, 80);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (7, 'Corsair iCUE H100i', 'Corsair', 149.99, 60);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (7, 'NZXT Kraken X63', 'NZXT', 169.99, 50);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (7, 'Thermaltake Water 3.0', 'Thermaltake', 129.99, 40);

-- Monitores
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (8, 'Samsung Odyssey G7 27"', 'Samsung', 599.99, 20);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (8, 'LG 27GL850-B 27" 144Hz', 'LG', 349.99, 50);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (8, 'Dell UltraSharp U2720Q 27" 4K', 'Dell', 479.99, 30);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (8, 'ASUS TUF VG27AQ 27" 165Hz', 'ASUS', 399.99, 40);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (8, 'Acer Predator XB273K 27" 144Hz', 'Acer', 549.99, 25);
-- Teclados
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (9, 'Logitech G Pro X', 'Logitech', 129.99, 100);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (9, 'Corsair K95 RGB Platinum', 'Corsair', 199.99, 70);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (9, 'Razer BlackWidow V3', 'Razer', 129.99, 60);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (9, 'SteelSeries Apex Pro', 'SteelSeries', 199.99, 50);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (9, 'HyperX Alloy FPS Pro', 'HyperX', 89.99, 80);

-- Mouses
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (10, 'Logitech G502 HERO', 'Logitech', 79.99, 150);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (10, 'Razer DeathAdder Elite', 'Razer', 49.99, 120);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (10, 'SteelSeries Rival 600', 'SteelSeries', 99.99, 80);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (10, 'Corsair Dark Core RGB', 'Corsair', 89.99, 90);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (10, 'Zowie EC2-A', 'Zowie', 59.99, 100);

-- Baterías
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (11, 'Duracell AA', 'Duracell', 1.49, 500);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (11, 'Energizer CR2032', 'Energizer', 3.99, 300);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (11, 'Panasonic Eneloop Pro AA', 'Panasonic', 10.99, 200);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (11, 'Sony CR2025', 'Sony', 2.49, 350);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (11, 'Varta High Energy AAA', 'Varta', 1.99, 250);

-- Cables / Conectores
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (12, 'Cable HDMI 2.1', 'AmazonBasics', 9.99, 400);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (12, 'Cable USB-C a USB-A', 'Anker', 8.99, 350);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (12, 'Cable SATA III', 'StarTech', 12.99, 300);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (12, 'Cable de red Ethernet Cat6', 'Mediabridge', 6.99, 500);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (12, 'Adaptador USB-C a HDMI', 'UGREEN', 14.99, 250);

-- Unidades Ópticas / DVD
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (13, 'LG WH16NS40', 'LG', 64.99, 50);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (13, 'Pioneer BDR-XD07B', 'Pioneer', 89.99, 30);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (13, 'Asus BW-16D1HT', 'ASUS', 99.99, 40);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (13, 'Samsung SE-208GB', 'Samsung', 29.99, 60);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (13, 'LG GP60NB60', 'LG', 39.99, 70);

-- Chasis / Carcasa
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (14, 'NZXT H510', 'NZXT', 69.99, 80);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (14, 'Corsair iCUE 4000X RGB', 'Corsair', 109.99, 60);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (14, 'Fractal Design Meshify C', 'Fractal Design', 89.99, 50);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (14, 'Cooler Master MasterBox Q300L', 'Cooler Master', 59.99, 100);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (14, 'Phanteks P400A', 'Phanteks', 79.99, 75);

-- Adaptadores de Corriente
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (15, 'Adaptador de corriente para portátil Dell 65W', 'Dell', 29.99, 150);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (15, 'Cargador universal 90W', 'Targus', 39.99, 100);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (15, 'Adaptador de corriente HP 45W', 'HP', 24.99, 120);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (15, 'Cargador Lenovo 65W', 'Lenovo', 32.99, 80);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (15, 'Adaptador de corriente Apple 61W', 'Apple', 49.99, 60);



--servicios

-- Servicios más generales
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES
(16, 'Reparación de Computadora', 'Técnico', 50.00, 999999),
(16, 'Instalación de Software', 'Técnico', 30.00, 999999),
(16, 'Diagnóstico de Fallas', 'Técnico', 20.00, 999999),
(16, 'Mantenimiento Preventivo', 'Técnico', 40.00, 999999),
(16, 'Reemplazo de Batería', 'Técnico', 60.00, 999999),
(16, 'Instalación de Hardware', 'Técnico', 70.00, 999999),
(16, 'Limpieza Interna de PC', 'Técnico', 25.00, 999999),
(16, 'Instalación de Red', 'Técnico', 100.00, 999999),
(16, 'Configuración de Sistema', 'Técnico', 45.00, 999999),
(16, 'Actualización de Software', 'Técnico', 35.00, 999999),
(16, 'Reparación de Pantalla', 'Técnico', 80.00, 999999),
(16, 'Cambio de Tarjeta Gráfica', 'Técnico', 90.00, 999999),
(16, 'Restauración de Sistema', 'Técnico', 50.00, 999999),
(16, 'Optimización de PC', 'Técnico', 40.00, 999999),
(16, 'Recuperación de Datos', 'Técnico', 120.00, 999999),
(16, 'Instalación de Antivirus', 'Técnico', 20.00, 999999);

-- Servicios más complejos relacionados con los componentes
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES
(16, 'Reemplazo de Procesador', 'Técnico', 100.00, 999999),
(16, 'Actualización de BIOS para compatibilidad de procesador', 'Técnico', 80.00, 999999),
(16, 'Reemplazo de Tarjeta Madre', 'Técnico', 150.00, 999999),
(16, 'Reparación de Puerto USB en la tarjeta madre', 'Técnico', 50.00, 999999),
(16, 'Instalación de Memoria RAM', 'Técnico', 40.00, 999999),
(16, 'Prueba y diagnóstico de Memoria RAM', 'Técnico', 30.00, 999999),
(16, 'Reemplazo de Disco Duro / SSD', 'Técnico', 120.00, 999999),
(16, 'Clonación de Disco Duro a SSD', 'Técnico', 100.00, 999999),
(16, 'Reemplazo de Fuente de Poder', 'Técnico', 80.00, 999999),
(16, 'Reparación de Fuente de Poder', 'Técnico', 60.00, 999999),
(16, 'Instalación de Tarjeta Gráfica', 'Técnico', 70.00, 999999),
(16, 'Reemplazo de Tarjeta Gráfica', 'Técnico', 90.00, 999999),
(16, 'Limpieza de Ventiladores', 'Técnico', 25.00, 999999),
(16, 'Instalación de Ventiladores adicionales', 'Técnico', 40.00, 999999),
(16, 'Reparación de Pantalla', 'Técnico', 80.00, 999999),
(16, 'Reemplazo de Pantalla', 'Técnico', 120.00, 999999),
(16, 'Reparación de Teclado', 'Técnico', 30.00, 999999),
(16, 'Reemplazo de Teclado', 'Técnico', 50.00, 999999),
(16, 'Reparación de Mouse', 'Técnico', 25.00, 999999),
(16, 'Reemplazo de Mouse', 'Técnico', 35.00, 999999),
(16, 'Reemplazo de Batería', 'Técnico', 60.00, 999999),
(16, 'Calibración de Batería', 'Técnico', 30.00, 999999),
(16, 'Reparación de Cables', 'Técnico', 15.00, 999999),
(16, 'Reemplazo de Conectores', 'Técnico', 20.00, 999999),
(16, 'Reparación de Unidad Óptica', 'Técnico', 50.00, 999999),
(16, 'Reemplazo de Unidad Óptica', 'Técnico', 70.00, 999999),
(16, 'Reparación de Chasis', 'Técnico', 40.00, 999999),
(16, 'Cambio de Carcasa', 'Técnico', 60.00, 999999),
(16, 'Reemplazo de Adaptador de Corriente', 'Técnico', 30.00, 999999),
(16, 'Reparación de Adaptador de Corriente', 'Técnico', 40.00, 999999);

-- Servicios más simples y comunes
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES
(16, 'Limpieza de Computadora', 'Técnico', 25.00, 999999),
(16, 'Diagnóstico General de PC', 'Técnico', 30.00, 999999),
(16, 'Instalación de Antivirus', 'Técnico', 20.00, 999999),
(16, 'Actualización de Sistema Operativo', 'Técnico', 40.00, 999999),
(16, 'Instalación de Software', 'Técnico', 25.00, 999999),
(16, 'Backup y Recuperación de Archivos', 'Técnico', 50.00, 999999),
(16, 'Optimización de Sistema', 'Técnico', 35.00, 999999),
(16, 'Configuración de Red Local', 'Técnico', 45.00, 999999),
(16, 'Instalación de Router', 'Técnico', 30.00, 999999),
(16, 'Configuración de Wi-Fi', 'Técnico', 25.00, 999999),
(16, 'Instalación de Cortafuegos', 'Técnico', 40.00, 999999),
(16, 'Reparación de Laptop', 'Técnico', 50.00, 999999),
(16, 'Reparación de PC de Sobremesa', 'Técnico', 50.00, 999999),
(16, 'Configuración de Impresora', 'Técnico', 25.00, 999999),
(16, 'Configuración de Correo Electrónico', 'Técnico', 30.00, 999999),
(16, 'Instalación de Drivers', 'Técnico', 20.00, 999999);



--seleccion

SELECT * FROM TECNICO
SELECT * FROM TIPOITEM
SELECT * FROM ITEM
SELECT * FROM ESTADO
select * from REGISTRO
select * from COMPUTADORA
select * from CLIENTE
select * from USOITEMS
SELECT COUNT(1) FROM USOITEMS WHERE SERIERegistro = ?
SELECT COUNT(1) FROM REGISTRO WHERE SERIERegistro = 'RUHP100002'
select * from REGISTRO

SELECT 
    CASE 
        WHEN DATEDIFF(DAY, FechaEstimadaDeEntrega, COALESCE(FechaRealDeEntrega, '2024-12-12')) >= 0 
            THEN CONCAT('+', DATEDIFF(DAY, FechaEstimadaDeEntrega, COALESCE(FechaRealDeEntrega, '2024-12-12')))
        ELSE CONCAT('-', ABS(DATEDIFF(DAY, FechaEstimadaDeEntrega, COALESCE(FechaRealDeEntrega, '2024-12-12'))))
    END AS dias_diferencia
FROM REGISTRO
WHERE SERIERegistro = 'RUHP100002';

SELECT DATEDIFF(DAY, FechaEstimadaDeEntrega, COALESCE(FechaRealDeEntrega, '2024-12-12')) 
                FROM REGISTRO
                WHERE SERIERegistro = 'RUHP100002'


select sum(CostoTotal) from USOITEMS where SERIERegistro = ?
select Total from REGISTRO where SERIERegistro = 'RUHP100001'
update REGISTRO set Importe = ?,Impuesto = ?, TOTAL =?,FechaRealDeEntrega=?,ProblemaReportado = ? WHERE SERIERegistro = ?
update REGISTRO set FechaRealDeEntrega= '2024-12-08' WHERE SERIERegistro = 'RUHP100001'
select CONVERT(VARCHAR, FechaDeRegistro, 23) FROM REGISTRO where SERIERegistro = 'RUHP100001'

/*
DELETE FROM CLIENTE
WHERE  ClienteDni = '12342679'
GO
select COUNT(1) contador from CLIENTE*/
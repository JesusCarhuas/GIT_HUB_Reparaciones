--CLIENTE
INSERT INTO dbo.CLIENTE (Nombre, ApellidoPaternoC, ApellidoMaternoC, ClienteDni, Direccion, Telefono, Email)
VALUES
('JOSE', 'QUISPE', 'Ram�rez', '12345678', 'Av. Los �lamos 123', '987654321', 'JOSE.111@gmail.com'),
('Jes�s', 'CARBAJAL', 'Ram�rez', '12345678', 'Av. Los �lamos 123', '987654321', 'jesus.carhuas@gmail.com');
GO

--COMPUTADORA

INSERT INTO dbo.COMPUTADORA (IDCliente, Marca, NumeroSerie, AnioFabricacion)
VALUES
(1, 'Dell', 'ABC12345', 2022),
(2, 'Dell', 'ABC12345', 2023);
GO


--TECNICO
INSERT INTO dbo.TECNICO (Nombre, ApellidoPaternoT, ApellidoMaternoT, TecnicoDni, Direccion, Telefono, Email, Contrasena, Activo)
VALUES 
('Carlos', 'Ramirez', 'Lopez', '12345678', 'Av. Los Olivos 123', '999888777', 'carlos.ramirez@example.com', 'contrasenaSegura123', 0),
('juan', 'Ramirez', 'Lopez', '12555678', 'Av. Los Olivos 123', '999888777', 'carlos.ramirez@example.com', 'contrasenaSegura123', 0);
--ESTADOS
-- Tabla: ESTADO

INSERT INTO dbo.ESTADO (Descripcion) VALUES ('EN PROCESO');
INSERT INTO dbo.ESTADO (Descripcion) VALUES ('REPARADO');
INSERT INTO dbo.ESTADO (Descripcion) VALUES ('LISTO PARA ENTREGAR');
INSERT INTO dbo.ESTADO (Descripcion) VALUES ('FINALIZADO');

--Prueba Registro
INSERT INTO dbo.REGISTRO (
    SERIERegistro, IDCliente, IDComputadora, IDTecnico, IDEstado,
    Adelanto, Importe,Impuesto,TOTAL, FechaDeRegistro,
    FechaEstimadaDeEntrega, ProblemaReportado
)
VALUES (
    'R00002',    -- SERIERegistro
    1,           -- IDCliente
    1,           -- IDComputadora
    1,           -- IDTecnico
    1,           -- IDEstado
    100.00,      -- Adelanto
	500.00,      -- importe
	180.00,      --igv
	680.00,      --total
    GETDATE(), -- FechaDeRegistro
    '2024-11-20', -- FechaEstimadaDeEntrega
    'Problema de arranque del sistema operativo' -- ProblemaReportado
);

/*
-- Tabla: TIPOITEM

INSERT INTO dbo.TIPOITEM (NombreTipo) VALUES ('Procesador');
INSERT INTO dbo.TIPOITEM (NombreTipo) VALUES ('Servicios');
---Tabla: ITEMS
-- Procesadores
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (1, 'Intel Core i9-13900K', 'Intel', 599.99, 50);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (1, 'AMD Ryzen 9 7900X', 'AMD', 569.99, 30);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (1, 'Intel Core i7-12700K', 'Intel', 399.99, 45);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (1, 'AMD Ryzen 7 7700X', 'AMD', 349.99, 40);
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (1, 'Intel Core i5-13600K', 'Intel', 299.99, 60);
-- Servicios
INSERT INTO dbo.ITEM (IDTipo, Nombre, Marca, CostoUnitario, Stock) VALUES (2, 'Reemplazo de Procesador', 'T�cnico', 100.00, 999999);
*/

/*


--TABLA USOITEMS

INSERT INTO dbo.USOITEMS (IDItem, SERIERegistro, CostoUnitario, Cantidad)
VALUES 
(1, 'R00001', 600, 1),
(6, 'R00001', 100, 1)

INSERT INTO dbo.PAGO(SERIERegistro,MontoPago) VALUES 
('R00001',100),
('R00001',726)
*/
SELECT * FROM USOITEMS
SELECT sum(CostoTotal) SUMA from usoitems where serieregistro = 'R00001'
UPDATE REGISTRO SET Importe = ? WHERE SERIERegistro = ?
UPDATE TECNICO SET Activo = 1 WHERE IDTecnico = ?

select Adelanto from REGISTRO WHERE SERIERegistro = 'R00001'
select 

select * from registro
select TOTAL - Adelanto AS Saldo from REGISTRO where SERIERegistro = 'RUDEL00001' 

select * from cliente
select * from computadora
select * from tecnico

select * from estado

select * from PAGO



select * from tipoitem
select * from ITEM
SELECT * FROM USOITEMS
select * from REGISTRO 


SELECT COUNT(1) contador FROM REGISTRO WHERE SERIERegistro = 'R00001'

SELECT CostoUnitario FROM ITEM WHERE idItem = 1
SELECT Stock FROM ITEM WHERE idItem = 1

select * from TIPOITEM

SELECT * FROM Item WHERE idTipo = ?
SELECT * FROM REGISTRO
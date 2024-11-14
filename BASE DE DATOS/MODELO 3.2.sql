
-- ======================================================
-- CREACI�N DE LA BASE DE DATOS
-- ======================================================
--ELIMINACION DE UNA BASE DE DATOS CON EL MISMO NOMBRE

USE master;
GO

IF( EXISTS ( SELECT 1 FROM sys.sysdatabases WHERE name='REPARACIONES_UNI' ) )
BEGIN
		ALTER DATABASE REPARACIONES_UNI SET SINGLE_USER WITH ROLLBACK IMMEDIATE; 
		DROP DATABASE REPARACIONES_UNI;
END;
GO
--CREACION
CREATE DATABASE REPARACIONES_UNI;
go

USE REPARACIONES_UNI;
GO





-- ==========================================================
-- Creaci�n de las Tablas
-- ==========================================================
-- Tabla CLIENTE
CREATE TABLE dbo.CLIENTE (
    IDCliente INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
	ApellidoPaternoC VARCHAR(25) NOT NULL,
    ApellidoMaternoC VARCHAR(25) NOT NULL,
	ClienteDni          CHAR(8) NOT NULL,
    Direccion VARCHAR(50) NOT NULL,
    Telefono VARCHAR(20) NOT NULL,
    Email VARCHAR(50) NOT NULL
);
GO



-- Tabla COMPUTADORA
CREATE TABLE dbo.COMPUTADORA (
    IDComputadora INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    IDCliente INT NOT NULL,
    Marca	VARCHAR(50) NOT NULL,
	NumeroSerie VARCHAR(50) NOT NULL,
	AnioFabricacion INT NOT NULL, 
    CONSTRAINT FK_COMPUTADORA_CLIENTE FOREIGN KEY (IDCliente) REFERENCES dbo.CLIENTE (IDCliente)
);
GO

-- Tabla TECNICO
CREATE TABLE dbo.TECNICO (
    IDTecnico INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    ApellidoPaternoT VARCHAR(25) NOT NULL,
    ApellidoMaternoT VARCHAR(25) NOT NULL,
    Direccion VARCHAR(50) NOT NULL,
    Telefono VARCHAR(25) NOT NULL,
    Email VARCHAR(100) NOT NULL,
	Contrasena VARCHAR(255) NOT NULL,
    FechaRegistro DATETIME DEFAULT GETDATE(),
    Activo BIT NOT NULL
);
GO

-- Tabla ESTADO
CREATE TABLE dbo.ESTADO (
    IDEstado INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    Descripcion VARCHAR(20) NOT NULL
);
GO

-- Tabla REGISTRO
CREATE TABLE dbo.REGISTRO (
    SERIERegistro VARCHAR(10) NOT NULL PRIMARY KEY,
    IDCliente INT NOT NULL,
    IDComputadora INT NOT NULL,
    IDTecnico INT NOT NULL,
	IDEstado INT NOT NULL,
	Adelanto NUMERIC(10,2) NOT NULL,
	Importe NUMERIC(10,2) ,
	Impuesto NUMERIC(10,2) ,
	TOTAL NUMERIC(10,2) ,
	FechaDeRegistro DATE NOT NULL,
	FechaEstimadaDeEntrega DATE NOT NULL,
	FechaRealDeEntrega DATE,
    ProblemaReportado VARCHAR(200),
	CONSTRAINT FK_REGISTRO_COMPUTADORA FOREIGN KEY (IDComputadora) REFERENCES dbo.COMPUTADORA (IDComputadora),
    CONSTRAINT FK_REGISTRO_CLIENTE FOREIGN KEY (IDCliente) REFERENCES dbo.CLIENTE (IDCliente),
    CONSTRAINT FK_REGISTRO_TECNICO FOREIGN KEY (IDTecnico) REFERENCES dbo.TECNICO (IDTecnico),
    CONSTRAINT FK_REGISTRO_ESTADO FOREIGN KEY (IDEstado) REFERENCES dbo.ESTADO (IDEstado)
);
GO




-- Tabla TIPOITEM
CREATE TABLE dbo.TIPOITEM (
    IDTipo INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    NombreTipo VARCHAR(30) NOT NULL
);
GO

-- Tabla ITEM
CREATE TABLE dbo.ITEM (
    IDItem INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    IDTipo INT NOT NULL,
    Nombre VARCHAR(100) NOT NULL,
	Marca VARCHAR(30) NOT NULL,
    CostoUnitario NUMERIC(10,2) NOT NULL,
    Stock FLOAT NOT NULL,
    CONSTRAINT FK_TIPO_ITEM FOREIGN KEY (IDTipo) REFERENCES dbo.TIPOITEM (IDTipo)
);
GO


-- Tabla USOITEMS
CREATE TABLE dbo.USOITEMS (
    IDUsoItem INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    IDItem INT NOT NULL,
	SERIERegistro VARCHAR(10) NOT NULL,
    CostoUnitario NUMERIC(10, 2) NOT NULL,
    Cantidad INT NOT NULL,
    CostoTotal AS (CostoUnitario * Cantidad) PERSISTED,
    CONSTRAINT FK_USOITEMS_ITEM FOREIGN KEY (IDItem) REFERENCES dbo.ITEM (IDItem),
	CONSTRAINT FK_REPARACION_REGISTRO FOREIGN KEY (SERIERegistro) REFERENCES dbo.REGISTRO (SERIERegistro)
);
GO



-- Tabla PAGO
CREATE TABLE dbo.PAGO (
    IDPago INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	SERIERegistro VARCHAR(10) NOT NULL,
    MontoPago NUMERIC(10,2) NOT NULL,
	CONSTRAINT FK_PAGO_REGISTRO FOREIGN KEY (SERIERegistro) REFERENCES dbo.REGISTRO (SERIERegistro),
);
GO
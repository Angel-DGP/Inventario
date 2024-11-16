/*Deletes de las tablas if existen*/
DROP TABLE IF EXISTS detalles_venta;
DROP TABLE IF EXISTS cabecera_venta;
DROP TABLE IF EXISTS historial_stock;
DROP TABLE IF EXISTS detalle_pedido;
DROP TABLE IF EXISTS cabecera_pedido;
DROP TABLE IF EXISTS estado_pedido;
DROP TABLE IF EXISTS proveedores;
DROP TABLE IF EXISTS tipo_documento;
DROP TABLE IF EXISTS producto;
DROP TABLE IF EXISTS unidades_medida;
DROP TABLE IF EXISTS categorias_unidad_medida;
DROP TABLE IF EXISTS categorias;
/*FIN DE DELETES*/

/*-------------CREATE TABLES----------------*/
/*categorias*/
create table categorias (
	codigo_cat serial not null,
	nombre varchar(100) not null,
	categoria_padre int,
	constraint categorias_pk primary key (codigo_cat),
	constraint categorias_fk foreign key (categoria_padre) references categorias(codigo_cat)
);
insert into categorias(nombre,categoria_padre) values
('Materia Prima',null),
('Proteina',1),
('Salsas',1),
('Punto de Venta',null),
('Bebidas',4),
('Con alcohol',5),
('Sin alcohol',5);
select * from categorias;
/*END categorias*/

/*categorias_unidad_medida*/
create table categorias_unidad_medida (
	codigo_cudm char(1) not null,
	nombre varchar(100) not null,
	constraint categorias_um_pk primary key (codigo_cudm)
);
insert into categorias_unidad_medida(codigo_cudm,nombre) values
('U','Unidades'),
('V','Volumen'),
('P','Peso'),
('L','Longitud');
select * from categorias_unidad_medida;
/*END categorias*/

/*unidades_medida*/
create table unidades_medida(
	codigo_udm char(3) not null,
	descripcion varchar(100) not null,
	categoria_udm char(1) not null,
	constraint um_pk primary key (codigo_udm),
	constraint um_fk foreign key (categoria_udm) references categorias_unidad_medida(codigo_cudm)
);
insert into unidades_medida(codigo_udm,descripcion,categoria_udm) values
('ml','mililitros','V'),
('l','litros','V'),
('u','unidad','U'),
('d','docena','U'),
('g','gramos','P'),
('kg','kilogramos','P'),
('lb','libras','P');
select * from unidades_medida;
/*END unidades_medida*/
/*PRODUCTO*/
create table producto(
	codigo_producto char(3) not null,
	nombre varchar(100) not null,
	udm char(3) not null,
	precio_venta money not null,
	tiene_iva boolean not null,
	coste money not null,
	categoria_fk int not null,
	stock int not null,
	constraint producto_pk primary key(codigo_producto),
	constraint producto_fk_categoria foreign key (categoria_fk) references categorias(codigo_cat),
	constraint producto_fk_unidadm foreign key (udm) references unidades_medida(codigo_udm)
);
insert into producto(codigo_producto,nombre,udm,precio_venta,tiene_iva,coste,categoria_fk,stock) values
('001','Coca Cola','u',0.5804,true,0.3729,7,110),
('002','Salsa de Tomate','kg',0.95,true,0.8736,3,0),
('003','FUZTEA','u',0.80,true,0.70,7,50);
select * from producto;
/*END PRODUCTOS*/

/*Tipo de documento*/
create table tipo_documento(
	codigo char(1) not null,
	descripcion varchar(25) not null,
	constraint td_pk primary key(codigo)
);
insert into tipo_documento(codigo,descripcion)
values ('C','CEDULA'),
('R','RUC');
select * from tipo_documento;
/*END TIPO DOCUMENTO*/
/*Proveedores*/
create table proveedores(
	identificador char(10) not null,
	tipo_documento char(1) not null,
	nombre varchar(100) not null,
	telefono char(10) not null,
	correo varchar(100) not null,
	direccion varchar(150) not null,
	constraint proveedores_pk primary key (identificador),
	constraint proveedores_fk foreign key (tipo_documento) references tipo_documento(codigo)
);
insert into proveedores (identificador,tipo_documento,nombre,telefono,correo,direccion) values 
('1792285747','C','SANTIAGO MOSQUERA','0998765432','mosquera.santiago@gmail.com','Cumbaya'),
('1792285123','R','PAPAS FEKAS','0998765243','fekas.papas.muy@gmail.com','Cumbaca');
select * from proveedores;
/*END PROVEEDORES*/

/*ESTADO PEDIDO*/
create table estado_pedido(
	codigo char(1) not null,
	descripcion varchar(50) not null,
	constraint estado_pedido_pk primary key (codigo)
);
insert into estado_pedido(codigo,descripcion) values ('S','SOLICITADO'),
('R','RECIBIDO');
select * from estado_pedido;
/*END ESTADO PEDIDO*/

/*CABECERA PEDIDO*/
create table cabecera_pedido (
	numero serial not null,
	proveedor char(10) not null,
	fecha date not null,
	estado char(1) not null,
	constraint cpd_pk primary key (numero),
	constraint cpd_fk_prv foreign key (proveedor) references proveedores(identificador),
	constraint cpd_fk_estado foreign key (estado) references estado_pedido(codigo)
);
insert into cabecera_pedido (proveedor,fecha,estado) values
('1792285747','6/11/2024','R'),
('1792285747','6/11/2024','S');
select * from cabecera_pedido;
/*END CABECERA PEDIDO*/

/*DETALLE PEDIDO*/
create table detalle_pedido (
	codigo serial not null,
	cabecera_pedido int not null,
	producto char(3) not null,
	cantidad_solicitada int not null,
	subtotal money not null,
	cantidad_recibida int not null,
	constraint dp_pk primary key (codigo),
	constraint dp_fk_cpd foreign key (cabecera_pedido) references cabecera_pedido(numero),
	constraint dp_fk_prd foreign key (producto) references producto(codigo_producto)
);
insert into detalle_pedido (cabecera_pedido,producto,cantidad_solicitada,subtotal,cantidad_recibida)
values (1,'001',100,37.29,100),
(1,'003',50,11.8,50);
select * from detalle_pedido;
/*HISTORIAL STOCK*/
create table historial_stock (
	codigo serial not null,
	fecha timestamp without time zone not null,
	referencia varchar(60) not null,
	producto char(3) not null,
	cantidad int not null,
	constraint hs_pk primary key (codigo),
	constraint hs_fk_prd foreign key (producto) references producto(codigo_producto)
	);
insert into historial_stock(fecha,referencia,producto,cantidad) values
('20/11/2024 19:59:07','PEDIDO 1','001',100),
('20/11/2024 19:59:07','PEDIDO 1','003',50),
('20/11/2024 19:19:37','PEDIDO 2','001',10),
('20/12/2024 20:29:47','VENTA 1','001',-5),
('20/12/2024 21:51:12','VENTA 1','003',-1);
select * from historial_stock;
/*END HISTORIAL STOCK*/

/*CABECERA VENTA*/
create table cabecera_venta (
	codigo serial not null,
	fecha date not null,
	total_sin_iva money not null,
	iva money not null,
	total money not null,
	constraint cv_pk primary key (codigo)
);
insert into cabecera_venta (fecha,total_sin_iva,iva,total) values
('6/11/2024',3.26,0.39,3.65);
select * from cabecera_venta;
/*END CABECERA VENTA*/

/*DETALLES VENTA*/
create table detalles_venta (
	codigo serial not null,
	cabecera_venta int not null,
	producto char(3) not null,
	cantidad int not null,
	precio_venta money not null,
	subtotal money not null,
	subtotal_mas_iva money not null,
	constraint dv_pk primary key (codigo),
	constraint dv_cv_fk foreign key (cabecera_venta) references cabecera_venta (codigo),
	constraint dv_prd_fk foreign key (producto) references producto(codigo_producto)
);
insert into detalles_venta(cabecera_venta,producto,cantidad,precio_venta,subtotal,subtotal_mas_iva) values
(1,'001',5,0.58,2.90,3.25),
(1,'003',1,0.36,0.36,0.4);
select * from detalles_venta;
create table usuarios
(
	idusuario int not null auto_increment,
	password varchar(255) not null,
	username varchar(100) not null,
	apaterno varchar(40) not null,
	amaterno varchar(40) not null,
	nombre varchar(40) not null,
	tipoUsuario int not null,
	primary key(idusuario),
	unique (username)

) engine = Innodb;

create table categoria 
(
	idcategoria int not null auto_increment,
	nombre varchar(50) not null,
	primary key (idcategoria)
) engine= Innodb;


create table productos
(
	idproducto int not null auto_increment,
	precioventa double not null,
	nombre varchar(100) not null, 
	cantidad int not null,
	codigo varchar(30) not null,
	preciocompra double not null,
	categoria int not null,
	descripcion varchar(255),
	foreign key (categoria) references categoria(idcategoria)
	on delete restrict on update cascade,
	primary key (idproducto)
) engine = Innodb;

create table ventas
(
	idventa int not null auto_increment,
	noventa int not null,
	idproducto int not null,
	fecha date not null,
	precio double not null,
	cantidad int not null,
	foreign key (idproducto) references productos(idproducto)
	on delete restrict
	on update cascade,
	primary key(idventa)
) engine = Innodb;


create table compras
(
	idcompra int not null auto_increment,
	nocompra int not null,
	precio double not null,
	cantidad int not null,
	idproducto int not null,
	fecha date not null,
	foreign key(idproducto) references productos(idproducto)
	on delete restrict
	on update cascade,
	primary key (idcompra)
) engine = Innodb;
create table usuarios(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    email varchar(100) not null,
    clave varchar(100) not null,
    activo tinyint default 1,
    primary key(id)
);

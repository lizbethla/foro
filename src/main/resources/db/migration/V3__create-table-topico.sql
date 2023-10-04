create table topicos(
    id bigint not null auto_increment,
    titulo varchar(150) not null,
    mensaje varchar(500) not null,
    fecha date not null,
    status varchar(50) not null,
    autor bigint not null,
    curso bigint not null,
    primary key(id),
    constraint fk_topico_usuario foreign key(autor) references usuarios(id),
    constraint fk_topico_curso foreign key(curso) references cursos(id)
);
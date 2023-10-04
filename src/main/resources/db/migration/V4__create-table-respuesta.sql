create table respuestas(
    id bigint not null auto_increment,
    mensaje varchar(500) not null,
    topico bigint not null,
    fecha date not null,
    autor bigint not null,
    solucion tinyint not null,
    primary key(id),
    constraint fk_respuesta_topico foreign key(topico) references topicos(id),
    constraint fk_respuesta_usuario foreign key(autor) references usuarios(id)
);
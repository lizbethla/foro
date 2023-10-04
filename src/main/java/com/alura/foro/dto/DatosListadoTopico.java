package com.alura.foro.dto;

import java.time.LocalDateTime;

import com.alura.foro.model.Curso;
import com.alura.foro.model.StatusTopico;
import com.alura.foro.model.Topico;
import com.alura.foro.model.Usuario;

public record DatosListadoTopico(String titulo, String mensaje, LocalDateTime fecha, StatusTopico status, Usuario autor,
		Curso curso) {
	public DatosListadoTopico(Topico topico) {
		this(topico.getTitulo(), topico.getMensaje(), topico.getFecha(), topico.getStatus(),
				new Usuario(topico.getAutor().getId(), topico.getAutor().getNombre(), topico.getAutor().getEmail(),
						null, topico.getAutor().getActivo()),
				new Curso(topico.getCurso().getId(), topico.getCurso().getNombre(), topico.getCurso().getCategoria(),
						topico.getCurso().getActivo()));
	}
}

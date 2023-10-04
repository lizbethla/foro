package com.alura.foro.model;

import java.time.LocalDateTime;

import com.alura.foro.dto.DatosActualizarTopico;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String mensaje;
	private LocalDateTime fecha = LocalDateTime.now();
	@Enumerated(EnumType.STRING)
	private StatusTopico status = StatusTopico.NO_RESPONDIDO;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "autor")
	private Usuario autor;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "curso")
	private Curso curso;
	
	public Topico(String titulo, String mensaje, Usuario autor, Curso curso) {
		this.titulo = titulo;
		this.mensaje = mensaje;
		this.autor = autor;
		this.curso = curso;
	}

	public void actualizarDatosTopico(DatosActualizarTopico datosActualizarTopico) {
		if (datosActualizarTopico.titulo() != null) {
			this.titulo = datosActualizarTopico.titulo();
		}
		
		if (datosActualizarTopico.mensaje() != null) {
			this.mensaje = datosActualizarTopico.mensaje();
		}

		if (datosActualizarTopico.status() != null) {
			this.status = datosActualizarTopico.status();
		}
		
		if (datosActualizarTopico.autor() != null) {
			this.autor = new Usuario(datosActualizarTopico.autor(),null,null,null,null);
		}
		
		if (datosActualizarTopico.curso() != null) {
			this.curso = new Curso(datosActualizarTopico.curso(),null,null,null);
		}
	}


}

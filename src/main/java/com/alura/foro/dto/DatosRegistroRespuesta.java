package com.alura.foro.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroRespuesta(
		@NotBlank
		String mensaje,
		Long topico,
		LocalDateTime fecha,
		Long autor,
		Boolean solucion) {

}

package com.alura.foro.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroTopico(
		@NotBlank
		String titulo,
		@NotBlank
		String mensaje,
		Long autor,
		Long curso) {

}

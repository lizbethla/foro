package com.alura.foro.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroCurso(	

		@NotBlank
		String nombre,
		@NotBlank
		String categoria) {
}

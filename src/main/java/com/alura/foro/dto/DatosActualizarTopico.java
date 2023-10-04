package com.alura.foro.dto;

import com.alura.foro.model.StatusTopico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(@NotNull Long id, @NotBlank String titulo, @NotBlank String mensaje,
		StatusTopico status , Long autor, Long curso) {

}

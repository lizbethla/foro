package com.alura.foro.dto;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarCurso(@NotNull Long id, String nombre, String categoria) {

}

package com.alura.foro.dto;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarUsuario(@NotNull Long id, String nombre, String email, String clave) {

}

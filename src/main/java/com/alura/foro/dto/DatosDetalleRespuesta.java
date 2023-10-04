package com.alura.foro.dto;

import java.time.LocalDateTime;

import com.alura.foro.model.Respuesta;
import com.alura.foro.model.Topico;
import com.alura.foro.model.Usuario;

public record DatosDetalleRespuesta(Long id, String mensaje, LocalDateTime fecha, Boolean solucion) {
	public DatosDetalleRespuesta(Respuesta respuesta) {		
		this(respuesta.getId(), respuesta.getMensaje(), respuesta.getFecha(), respuesta.getSolucion());
	}

}

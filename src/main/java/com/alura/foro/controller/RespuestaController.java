package com.alura.foro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.foro.dto.DatosDetalleRespuesta;
import com.alura.foro.dto.DatosRegistroRespuesta;
import com.alura.foro.dto.DatosRegistroTopico;
import com.alura.foro.dto.DatosRespuestaTopico;
import com.alura.foro.model.Respuesta;
import com.alura.foro.model.Topico;
import com.alura.foro.repository.CursoRepository;
import com.alura.foro.repository.RespuestaRepository;
import com.alura.foro.repository.TopicoRepository;
import com.alura.foro.repository.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {
	
	@Autowired
	private RespuestaRepository respuestaRepository;
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping
	public ResponseEntity<DatosDetalleRespuesta> registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta,
															UriComponentsBuilder uriBuilder) {
		
		var topico = topicoRepository.findById(datosRegistroRespuesta.topico()).get();
		var autor = usuarioRepository.findById(datosRegistroRespuesta.autor()).get();	

	
		var respuesta = new Respuesta(null,datosRegistroRespuesta.mensaje(), topico, datosRegistroRespuesta.fecha(), autor,
				datosRegistroRespuesta.solucion());
		respuestaRepository.save(respuesta);
		
		var uri = uriBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();
		
		
		return ResponseEntity.created(uri).body(new DatosDetalleRespuesta(respuesta));
	}

/*
	@GetMapping
	public ResponseEntity<Page<DatosListadoCurso>> listadoCursos(@PageableDefault(size = 4) Pageable paginacion) {
		return ResponseEntity.ok(topicoRepository.findByActivoTrue(paginacion).map(DatosListadoCurso::new));

	}

	@PutMapping
	@Transactional
	public ResponseEntity<DatosRespuestaTopico> actualizarCurso(@RequestBody @Valid DatosActualizarCurso datosActualizarCurso) {
		Curso topico = topicoRepository.getReferenceById(datosActualizarCurso.id());
		topico.actualizarDatosCurso(datosActualizarCurso);
		return ResponseEntity.ok(new DatosRespuestaTopico(topico));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity eliminarCurso(@PathVariable Long id) {
		Curso topico = topicoRepository.getReferenceById(id);
		topico.desactivarCurso();
		return ResponseEntity.noContent().build();
	}

*/


}

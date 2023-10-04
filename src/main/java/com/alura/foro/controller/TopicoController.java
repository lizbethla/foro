package com.alura.foro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.foro.dto.DatosActualizarTopico;
import com.alura.foro.dto.DatosListadoTopico;
import com.alura.foro.dto.DatosRegistroTopico;
import com.alura.foro.dto.DatosRespuestaTopico;
import com.alura.foro.model.Topico;
import com.alura.foro.repository.CursoRepository;
import com.alura.foro.repository.TopicoRepository;
import com.alura.foro.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@PostMapping
	public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
															UriComponentsBuilder uriBuilder) {
		
		var autor = usuarioRepository.findById(datosRegistroTopico.autor()).get();	
        var curso = cursoRepository.findById(datosRegistroTopico.curso()).get();     

		var topico = new Topico(datosRegistroTopico.titulo(),datosRegistroTopico.mensaje(), autor, curso);
		topicoRepository.save(topico);
		var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new DatosRespuestaTopico(topico));
	}


	@GetMapping
	public ResponseEntity<Page<DatosListadoTopico>> listadoTopicos(@PageableDefault(size = 4) Pageable paginacion) {
		return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosListadoTopico::new));
	}

	@PutMapping
	@Transactional
	public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
		Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
		topico.actualizarDatosTopico(datosActualizarTopico);
		return ResponseEntity.ok(new DatosRespuestaTopico(topico));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity eliminarTopico(@PathVariable Long id) {
		Topico topico = topicoRepository.getReferenceById(id);
		topicoRepository.delete(topico);
		return ResponseEntity.noContent().build();
	}

}

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

import com.alura.foro.dto.DatosActualizarCurso;
import com.alura.foro.dto.DatosListadoCurso;
import com.alura.foro.dto.DatosRegistroCurso;
import com.alura.foro.dto.DatosRespuestaCurso;
import com.alura.foro.model.Curso;
import com.alura.foro.repository.CursoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cursos")
public class CursoController {

	@Autowired
	private CursoRepository cursoRepository;

	@PostMapping
	public ResponseEntity<DatosRespuestaCurso> registrarCurso(@RequestBody @Valid DatosRegistroCurso datosRegistroCurso,
															UriComponentsBuilder uriBuilder) {
		var curso = new Curso(datosRegistroCurso);
		cursoRepository.save(curso);
		var uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
		return ResponseEntity.created(uri).body(new DatosRespuestaCurso(curso));
	}

	@GetMapping
	public ResponseEntity<Page<DatosListadoCurso>> listadoCursos(@PageableDefault(size = 4) Pageable paginacion) {
		return ResponseEntity.ok(cursoRepository.findByActivoTrue(paginacion).map(DatosListadoCurso::new));

	}

	@PutMapping
	@Transactional
	public ResponseEntity<DatosRespuestaCurso> actualizarCurso(@RequestBody @Valid DatosActualizarCurso datosActualizarCurso) {
		Curso curso = cursoRepository.getReferenceById(datosActualizarCurso.id());
		curso.actualizarDatosCurso(datosActualizarCurso);
		return ResponseEntity.ok(new DatosRespuestaCurso(curso));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity eliminarCurso(@PathVariable Long id) {
		Curso curso = cursoRepository.getReferenceById(id);
		curso.desactivarCurso();
		return ResponseEntity.noContent().build();
	}

}

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
import com.alura.foro.dto.DatosActualizarUsuario;
import com.alura.foro.dto.DatosListadoCurso;
import com.alura.foro.dto.DatosListadoUsuario;
import com.alura.foro.dto.DatosRegistroCurso;
import com.alura.foro.dto.DatosRegistroUsuario;
import com.alura.foro.dto.DatosRespuestaCurso;
import com.alura.foro.dto.DatosRespuestaUsuario;
import com.alura.foro.model.Usuario;
import com.alura.foro.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping
	public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario,
															UriComponentsBuilder uriBuilder) {
		var usuario = new Usuario(datosRegistroUsuario);
		usuarioRepository.save(usuario);
		var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(new DatosRespuestaUsuario(usuario));
	}

	@GetMapping
	public ResponseEntity<Page<DatosListadoUsuario>> listadoCursos(@PageableDefault(size = 4) Pageable paginacion) {
		return ResponseEntity.ok(usuarioRepository.findByActivoTrue(paginacion).map(DatosListadoUsuario::new));

	}

	@PutMapping
	@Transactional
	public ResponseEntity<DatosRespuestaUsuario> actualizarUusario(@RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario) {
		Usuario usuario = usuarioRepository.getReferenceById(datosActualizarUsuario.id());
		usuario.actualizarDatosCurso(datosActualizarUsuario);
		return ResponseEntity.ok(new DatosRespuestaUsuario(usuario));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity eliminarUsuario(@PathVariable Long id) {
		Usuario usuario = usuarioRepository.getReferenceById(id);
		usuario.desactivarUsuario();
		return ResponseEntity.noContent().build();
	}

}

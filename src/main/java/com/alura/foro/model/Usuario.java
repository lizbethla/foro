package com.alura.foro.model;

import com.alura.foro.dto.DatosActualizarUsuario;
import com.alura.foro.dto.DatosRegistroUsuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String email;
	private String clave;
	private Boolean activo;
	
	public Usuario(DatosRegistroUsuario datosRegistroUsuario) {
		this.nombre = datosRegistroUsuario.nombre();
		this.email = datosRegistroUsuario.email();
		this.clave = datosRegistroUsuario.clave();
		this.activo = true;
	}
	
    public void desactivarUsuario(){
        this.activo = false;
    }

	public void actualizarDatosCurso(DatosActualizarUsuario datosActualizarUsuario) {
        if(datosActualizarUsuario.nombre()!=null){
            this.nombre= datosActualizarUsuario.nombre();
        }
        if(datosActualizarUsuario.email()!=null){
            this.email= datosActualizarUsuario.email();
        }
        if(datosActualizarUsuario.clave()!=null){
            this.clave= datosActualizarUsuario.clave();
        }
		
	}
}

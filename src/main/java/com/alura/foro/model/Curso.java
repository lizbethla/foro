package com.alura.foro.model;

import com.alura.foro.dto.DatosActualizarCurso;
import com.alura.foro.dto.DatosRegistroCurso;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String categoria;
	private Boolean activo;
	
	public Curso(DatosRegistroCurso datosRegistroCurso) {
	  this.nombre = datosRegistroCurso.nombre();
	  this.categoria = datosRegistroCurso.categoria();
	  this.activo = true;
	}

    public void actualizarDatosCurso(DatosActualizarCurso datosActualizarCurso){
        if(datosActualizarCurso.nombre()!=null){
            this.nombre= datosActualizarCurso.nombre();
        }
        if(datosActualizarCurso.categoria()!=null){
            this.categoria= datosActualizarCurso.categoria();
        }
    }
    
    public void desactivarCurso(){
        this.activo = false;
    }
}

package com.club.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class PersonaDTO implements Serializable {
    private Long id;
    private String nombre;
    private String apellidos;
    private String correo;
    private String telefono;
    private Boolean socio;

    public PersonaDTO(Long id, String nombre, String apellidos, String correo,String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
    }
}

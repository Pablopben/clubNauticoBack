package com.club.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@DiscriminatorValue(value = "Si")
public class Socio extends Persona{

    @OneToMany(mappedBy = "dueño", cascade = CascadeType.ALL)
    @Setter
    public List<Barco> barcos;

    public Socio() {

    }

    public Socio(String nombre, String apellidos, String correo, String telefono) {
        this.setNombre(nombre);
        this.setApellidos(apellidos);
        this.setCorreo(correo);
        this.setTelefono(telefono);
    }



}

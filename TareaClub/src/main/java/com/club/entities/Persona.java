package com.club.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name = "PERSONA")
@DiscriminatorColumn(
        name="socio",
        discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue(value = "No")
public class Persona implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "personaGenerator")
    @SequenceGenerator(name="personaGenerator", initialValue = 5)
    @Column(name = "PERSONA_ID")
    private Long id;

    @Column(name = "NOMBRE")
    @NotBlank(message = "La persona debe de tener un nombre")
    private String nombre;

    @Column(name = "APELLIDOS")
    @NotBlank(message = "La persona debe de tener un apellido")
    private String apellidos;

    @Column(name = "CORREO")
    @Email
    @NotBlank(message = "La persona debe de tener un correo")
    private String correo;

    @Column(name = "TELEFONO")
    @Pattern(regexp = "[67][0-9]{8}", message = "El teléfono debe de comenzar por 6 o por 7 y tener 9 cifras")
    @NotNull
    private String telefono;

    @OneToMany(mappedBy = "patron", cascade = CascadeType.ALL)
    private List<Salida> salidas;

    public Persona() {
    }

    public Persona(String nombre, String apellidos, String correo, String telefono) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return this.getNombre() + " " + this.getApellidos();
    }

}

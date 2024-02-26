package com.club.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="BARCO")
public class Barco implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "BARCO_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "barcoGenerator")
    @SequenceGenerator(name="barcoGenerator", initialValue = 5)
    private Long id;


    @Column(name = "MATRICULA", unique = true)
    @Pattern(regexp = "[0-9]{5}-[A-Z]", message = "La matrícula debe de estar formada por cinco números," +
            "un guión y una letra mayúscula")
    private String matricula;


    @Column(name="NOMBRE")
    @NotBlank(message = "El barco necesita un nombre")
    private String nombre;


    @Column(name = "NUM_AMARRE")
    @Positive(message = "El numero de amarre debe ser positivo")
    private Integer numeroAmarre;


    @Column(name = "CUOTA")
    @Positive(message = "La cuota debe ser positiva")
    private Integer cuota;


    @OneToMany(mappedBy = "barco", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Salida> salidas;


    @ManyToOne
    @JoinColumn(name = "SOCIO_ID", nullable = false)
    private Socio dueño;

    public Barco() {
    }

    public Barco(String matricula, String nombre, Integer numeroAmarre, Integer cuota, Socio dueño) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.numeroAmarre = numeroAmarre;
        this.cuota = cuota;
        this.dueño = dueño;
    }

    @Override
    public String toString() {
        return "Barco{" +
                "matricula=" + matricula +
                ", nombre='" + nombre + '\'' +
                '}';
    }

}

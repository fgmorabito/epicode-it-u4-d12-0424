package org.epicode;

import jakarta.persistence.*;

@Entity
@Table(name="documenti")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero")
    private String numero;

    @Column(name = "tipo")
    private String tipo;

    @OneToOne(mappedBy = "documento") //il nome della proprietà di unione in Persona
    private Persona proprietario;


    //Getters, Costruttori e Setters del caso

}

package org.epicode;

import jakarta.persistence.*;

@Entity
@Table (name = "telefoni")
public class Telefono {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String numero;

    @ManyToOne  //relazione molti a uno verso le persone
    //ogni telefono ha un solo intestatario
    @JoinColumn(name = "persona_id")
    //Colonna di unione nella tabella telefoni
    //in telefoni ho FK persona_id (Colonna della tabella)
    private Persona persona;
}

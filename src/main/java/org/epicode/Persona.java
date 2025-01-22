package org.epicode;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe Persona rappresenta un'entità persistente mappata alla tabella "studenti" nel database.
 * Ogni istanza di questa classe corrisponde a una riga nella tabella "studenti".
 */
@Entity
@Table(name = "studenti") // Mappa la classe Persona sulla tabella "studenti"
public class Persona {

    /**
     * L'identificativo univoco della persona. Mappato come chiave primaria nella tabella "studenti".
     * L'ID viene generato automaticamente dal database grazie alla strategia GenerationType.IDENTITY.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome della persona. Mappato alla colonna "nome" della tabella "studenti".
     */
    @Column(name = "nome")
    private String nome;

    /**
     * Cognome della persona. Mappato alla colonna "cognome" della tabella "studenti".
     */
    @Column(name = "cognome")
    private String cognome;

    /**
     * Data di nascita della persona. Mappato alla colonna "data_nascita" della tabella "studenti".
     */
    @Column(name = "data_nascita")
    private LocalDate dataNascita;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="documento_id")    // ho la FK rispetto l'entità a cui mi collego
    private Documento documento;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Telefono> telefoni = new ArrayList<>();


    /**
     * Costruttore di default necessario per JPA.
     */
    public Persona() {
    }

    /**
     * Costruttore con parametri per creare una nuova istanza di Persona.
     *
     * @param n   Nome della persona.
     * @param c   Cognome della persona.
     * @param dataN Data di nascita della persona.
     */
    public Persona(String n, String c, LocalDate dataN) {
        this.nome = n;
        this.cognome = c;
        this.dataNascita = dataN;
    }

    /**
     * Ottiene l id della persona
     *
     * @return Id della persona
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Ottiene il nome della persona.
     *
     * @return Nome della persona.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome della persona.
     *
     * @param nome Nuovo nome della persona.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Ottiene il cognome della persona.
     *
     * @return Cognome della persona.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta il cognome della persona.
     *
     * @param cognome Nuovo cognome della persona.
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Ottiene la data di nascita della persona.
     *
     * @return Data di nascita della persona.
     */
    public LocalDate getDataNascita() {
        return dataNascita;
    }

    /**
     * Imposta la data di nascita della persona.
     *
     * @param dataNascita Nuova data di nascita della persona.
     */
    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public void addTelefono(Telefono telefono) {
        telefoni.add(telefono);
        telefono.setPersona(this);
    }

    public List<Telefono> getTelefoni() {
        return telefoni;
    }

}
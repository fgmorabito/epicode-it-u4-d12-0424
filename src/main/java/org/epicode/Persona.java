package org.epicode;

import jakarta.persistence.*;
import java.time.LocalDate;

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
}
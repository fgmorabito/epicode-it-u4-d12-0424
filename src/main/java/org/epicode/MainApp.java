package org.epicode;

import jakarta.persistence.EntityManager;
import java.time.LocalDate;

/**
 * Classe principale di prova per l'applicazione.
 * <p>
 * Questa classe esegue un test per interagire con il database utilizzando JPA (Hibernate).
 * <ul>
 *     <li>Avvia una transazione per creare una nuova entità Persona.</li>
 *     <li>Salva l'entità nella tabella "studenti" del database "Formazione".</li>
 *     <li>Gestisce eventuali errori con un rollback e stampa il tracciato degli errori.</li>
 * </ul>
 * <p>
 * La classe utilizza la classe di utilità {@link EntityManagerUtil} per gestire le operazioni di persistenza.
 */
public class MainApp {

    /**
     * Metodo principale per eseguire il test di persistenza.
     * <p>
     * Questo metodo:
     * <ol>
     *     <li>Inizializza un {@link EntityManager} per gestire le entità.</li>
     *     <li>Crea una nuova entità {@link Persona} e la salva nel database.</li>
     *     <li>Gestisce eventuali errori con un rollback e chiude le risorse al termine.</li>
     * </ol>
     *
     * @param args Argomenti della riga di comando (non utilizzati).
     */
    public static void main(String[] args) {

        // Creazione di un EntityManager per interagire con il database
        EntityManager em = EntityManagerUtil.getEntityManager();

        /*
         * Eseguiamo una transazione per:
         * - Creare una nuova entità Persona
         * - Salvarla nella tabella "studenti" del database "Formazione"
         */

        try {
            // Avvio di una nuova transazione
            em.getTransaction().begin();

            // Creazione di una nuova istanza di Persona
            Persona mariaGiovanna = new Persona("Maria Giovanna", "Violetti", LocalDate.of(1988, 12, 26));

            // Persistenza della nuova entità nel database
            em.persist(mariaGiovanna);

            // Commit della transazione (invio dei comandi al database)
            em.getTransaction().commit();

        } catch (Exception e) {
            // Gestione degli errori
            if (em.getTransaction().isActive()) {
                // Rollback della transazione in caso di errore
                em.getTransaction().rollback();
            }
            // Stampa del tracciato dell'errore
            e.printStackTrace();
        } finally {
            // Chiusura dell'EntityManager per liberare le risorse
            em.close();
        }
    }
}
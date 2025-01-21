package org.epicode;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Classe di utilità per gestire l'interazione con il database tramite JPA (Hibernate).
 * <p>
 * Questa classe si occupa di:
 * <ol>
 *     <li>Creare e gestire una connessione al database utilizzando l'unità di persistenza configurata.</li>
 *     <li>Fornire metodi per ottenere un'istanza di {@link EntityManager}, necessaria per effettuare operazioni CRUD.</li>
 *     <li>Chiudere correttamente le risorse associate al {@link EntityManagerFactory} una volta terminato il lavoro.</li>
 * </ol>
 * <p>
 * I metodi principali includono:
 * <ul>
 *     <li>{@code getEntityManager()}: Crea un'istanza di {@link EntityManager} per gestire le entità persistenti.</li>
 *     <li>{@code close()}: Chiude l'istanza di {@link EntityManagerFactory} per liberare le risorse.</li>
 * </ul>
 */
public class EntityManagerUtil {

    /**
     * Factory per la creazione di {@link EntityManager}.
     * Inizializzata con l'unità di persistenza configurata nel file persistence.xml.
     */
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("gestioneFormazionePU");

    /**
     * Restituisce un'istanza di {@link EntityManager} per interagire con il database.
     *
     * @return Un nuovo {@link EntityManager}.
     */
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Chiude l'istanza di {@link EntityManagerFactory}.
     * <p>
     * Questo metodo deve essere chiamato alla fine del ciclo di vita dell'applicazione per liberare
     * correttamente le risorse allocate.
     */
    public static void close() {
        emf.close();
    }
}
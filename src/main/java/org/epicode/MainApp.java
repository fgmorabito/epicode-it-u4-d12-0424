package org.epicode;

import jakarta.persistence.EntityManager;
import org.epicode.DAO.PersonaDAO;
import org.epicode.DAO.PersonaDaoImpl;

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

        Persona mario = new Persona("Mario", "Rossi", LocalDate.of(1982,10,04));

        //Mi creo un'istanza di DAO
        PersonaDAO personaDAO = new PersonaDaoImpl();

        //caso 1 : debbo salvare una persona nel db

        personaDAO.save(mario);
        System.out.println("Persona salvata! ID: " + mario.getId());

        //caso 2: update
        mario.setCognome("Gialli");
        personaDAO.save(mario);

        //caso 3: find by id (PK)
        Persona personaDaTrovare = personaDAO.findById(3L);
        //scenario 1: ha trovato la persona
        //scenario 2: non l ha trovata, quindi abbiamo NULL
        if (personaDaTrovare !=null)
            System.out.println("Persona trovata, nome: " +personaDaTrovare.getNome());
        else {
            System.out.println("Id non trovato");
        }

        //caso 4: delete
        personaDAO.deleteById(2L);


        //caso 5:
        /*
        * ho implementato le relazioni, provo a salvare una persona che ha più numeri di telefono
        *
        */

        Persona niccolo = new Persona("Niccolò", "Albanese",LocalDate.of(1990,7,24));
        niccolo.addTelefono(new Telefono("3280000000"));
        niccolo.addTelefono(new Telefono("3350000000"));
        //save, grazie alla relazione definita, salverà lo studente e i suoi telefoni
        //"gestendo" le pk e fk
        personaDAO.save(niccolo);

        Persona personaTrovata = personaDAO.findById(niccolo.getId());
        if (personaTrovata!= null) {
            System.out.println("Persona trovata");
            personaTrovata.getTelefoni().forEach( telefono ->
                    System.out.println("Telefono : " + telefono.getNumero())
            );
        }




        // Creazione di un EntityManager per interagire con il database
        // EntityManager em = EntityManagerUtil.getEntityManager();

        /*
         * Eseguiamo una transazione per:
         * - Creare una nuova entità Persona
         * - Salvarla nella tabella "studenti" del database "Formazione"
         */

        /*try {
            // Avvio di una nuova transazione
            em.getTransaction().begin();

            // Creazione di una nuova istanza di Persona
            Persona mariaGiovanna = new Persona("Maria Giovanna", "Violetti", LocalDate.of(1988, 12, 26));

            // Persistenza della nuova entità nel database
            em.persist(mariaGiovanna);

            // Commit della transazione (invio dei comandi al database)
            em.getTransaction().commit();

            System.out.println("Persona creata con ID: " + mariaGiovanna.getId());

            // Recupero di una persona per ID
            Long personaId = mariaGiovanna.getId(); // Usare l'ID generato

            em.getTransaction().begin();
            Persona personaRecuperata = em.find(Persona.class, personaId);  //passo l id della persona da recuperare
            em.getTransaction().commit();

            if (personaRecuperata != null) {
                System.out.println("Persona trovata: " + personaRecuperata.getNome() + " " + personaRecuperata.getCognome());
            } else {
                System.out.println("Persona con ID " + personaId + " non trovata.");
            }

            // Eliminazione di una persona per ID
            em.getTransaction().begin();
            Persona personaDaEliminare = em.find(Persona.class, personaId);
            if (personaDaEliminare != null) {
                em.remove(personaDaEliminare);  //elimina
                System.out.println("Persona con ID " + personaId + " eliminata.");
            } else {
                System.out.println("Persona con ID " + personaId + " non trovata per l'eliminazione.");
            }
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

         */
    }
}
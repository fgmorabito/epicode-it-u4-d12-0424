package org.epicode.DAO;

import jakarta.persistence.EntityManager;
import org.epicode.EntityManagerUtil;
import org.epicode.Persona;

import java.util.List;

public class PersonaDaoImpl implements PersonaDAO  {

    @Override
    public void save(Persona persona) {
        //chiamo l entity manager
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            //provo a salvare
            em.getTransaction().begin();
           // em.persist(persona);
            if(persona.getId() == null) //non l hai mai salvata prima ora nel db , quindi è un save
                em.persist(persona); //mando in persistenza
            else //la persona ha già un id, quindi significa che è stata già salvata nel db, update
                em.merge(persona);  //refresh, update

            em.getTransaction().commit();

        } catch (Exception e) {
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    @Override
    public Persona findById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Persona personaTrovata = em.find(Persona.class,id);
            em.getTransaction().commit();
            return personaTrovata;
        } catch (Exception e) {
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
        finally {
            em.close();
        }
    }

    @Override
    public void deleteById(Long id) {
        //entity manager
        EntityManager em = EntityManagerUtil.getEntityManager();
        //try catch
        try {
            em.getTransaction().begin();
            Persona persona = findById(id);
            //Persona persona = em.find(Persona.class,id);
            if(persona!=null)
                em.remove(persona);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            em.close();
        }

    }

    @Override
    public List<Persona> findAll() {
        EntityManager em = EntityManagerUtil.getEntityManager();

        /*
        *
        * eseguiamo una query custom sul db, e la restituisce come una List
         */
        try {
           // return em.createQuery("SELECT * FROM studenti ",Persona.class).getResultList();
            return em.createQuery("SELECT * FROM studenti WHERE cognome = :cognome", Persona.class)
                    .setParameter("cognome", "Morabito").getResultList();
        }
        finally {
            em.close();
        }

    }

}

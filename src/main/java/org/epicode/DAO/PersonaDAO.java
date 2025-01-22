package org.epicode.DAO;
import org.epicode.Persona;

import java.util.List;

public interface PersonaDAO {

    //metodo per il salvataggio
    void save (Persona persona);

    //metodo per recuperare una persona by id
    Persona findById(Long id);

    //metodo per cancellare un entità   PErsona
    void deleteById(Long id);

    //trovare tutte le persone, magari con un query custom
    List<Persona> findAll();

}

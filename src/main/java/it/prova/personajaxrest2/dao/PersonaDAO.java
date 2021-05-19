package it.prova.personajaxrest2.dao;


import it.prova.personajaxrest2.model.Persona;

import java.util.List;

public interface PersonaDAO extends IBaseDAO<Persona>{
    List<Persona> findByExample(Persona example);
}

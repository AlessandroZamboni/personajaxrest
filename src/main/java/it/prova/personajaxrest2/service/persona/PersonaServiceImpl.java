package it.prova.personajaxrest2.service.persona;


import it.prova.personajaxrest2.dao.PersonaDAO;
import it.prova.personajaxrest2.model.Persona;
import it.prova.personajaxrest2.web.listener.LocalEntityManagerFactoryListener;

import javax.persistence.EntityManager;
import java.util.List;

public class PersonaServiceImpl implements PersonaService {

    private PersonaDAO personaDAO;

    @Override
    public List<Persona> listAllElements() throws Exception {
        // questo è come una connection
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

        try {
            // uso l'injection per il dao
            personaDAO.setEntityManager(entityManager);

            // eseguo quello che realmente devo fare
            return personaDAO.list();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
        }
    }

    @Override
    public Persona caricaSingoloElemento(Long id) throws Exception {
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

        try {
            // uso l'injection per il dao
            personaDAO.setEntityManager(entityManager);

            // eseguo quello che realmente devo fare
            return personaDAO.findOne(id).get();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
        }
    }

    @Override
    public void aggiorna(Persona personaInstance) throws Exception {
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

        try {
            // questo è come il MyConnection.getConnection()
            entityManager.getTransaction().begin();

            // uso l'injection per il dao
            personaDAO.setEntityManager(entityManager);

            // eseguo quello che realmente devo fare
            personaDAO.update(personaInstance);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        } finally {
            LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
        }
    }

    @Override
    public void inserisciNuovo(Persona personaInstance) throws Exception {
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

        try {
            // questo è come il MyConnection.getConnection()
            entityManager.getTransaction().begin();

            // uso l'injection per il dao
            personaDAO.setEntityManager(entityManager);

            // eseguo quello che realmente devo fare
            personaDAO.insert(personaInstance);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        } finally {
            LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
        }
    }

    @Override
    public void rimuovi(Persona personaInstance) throws Exception {
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

        try {
            // questo è come il MyConnection.getConnection()
            entityManager.getTransaction().begin();

            // uso l'injection per il dao
            personaDAO.setEntityManager(entityManager);

            // eseguo quello che realmente devo fare
            personaDAO.delete(personaInstance);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        } finally {
            LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
        }
    }

    @Override
    public List<Persona> findByExample(Persona example) throws Exception {
        EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

        try {
            // uso l'injection per il dao
            personaDAO.setEntityManager(entityManager);

            // eseguo quello che realmente devo fare
            return personaDAO.findByExample(example);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
        }
    }

    @Override
    public void setPersonaDAO(PersonaDAO personaDAO) {
        this.personaDAO = personaDAO;
    }
}

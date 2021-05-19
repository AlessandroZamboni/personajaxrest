package it.prova.personajaxrest2.service;


import it.prova.personajaxrest2.dao.PersonaDAO;
import it.prova.personajaxrest2.dao.PersonaDAOImpl;
import it.prova.personajaxrest2.service.persona.PersonaService;
import it.prova.personajaxrest2.service.persona.PersonaServiceImpl;

public class MyServiceFactory {
    private static PersonaService PERSONA_SERVICE_INSTANCE;
    private static PersonaDAO PERSONA_DAO_INSTANCE;

    public static PersonaService getPersonaServiceInstance() {
        if (PERSONA_SERVICE_INSTANCE == null)
            PERSONA_SERVICE_INSTANCE = new PersonaServiceImpl();


        if (PERSONA_DAO_INSTANCE == null)
            PERSONA_DAO_INSTANCE = new PersonaDAOImpl();

        PERSONA_SERVICE_INSTANCE.setPersonaDAO(PERSONA_DAO_INSTANCE);
        return PERSONA_SERVICE_INSTANCE;
    }
}

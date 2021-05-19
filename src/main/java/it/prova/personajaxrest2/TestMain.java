package it.prova.personajaxrest2;


import it.prova.personajaxrest2.model.Persona;
import it.prova.personajaxrest2.service.MyServiceFactory;
import it.prova.personajaxrest2.service.persona.PersonaService;
import it.prova.personajaxrest2.web.listener.LocalEntityManagerFactoryListener;

import java.util.Date;

public class TestMain {

    public static void main(String[] args) throws Exception {
        PersonaService personaServiceInstance = MyServiceFactory.getPersonaServiceInstance();

        personaServiceInstance.inserisciNuovo(new Persona("Alessandro","Zamboni",new Date()));

    }
}

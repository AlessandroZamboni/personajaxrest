package it.prova.personajaxrest2.dao;

import it.prova.personajaxrest2.model.Persona;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.*;

public class PersonaDAOImpl implements PersonaDAO {

    private EntityManager entityManager;

    @Override
    public List<Persona> list() throws Exception {
        return entityManager.createQuery("select p from Persona p", Persona.class).getResultList();
    }

    @Override
    public Optional<Persona> findOne(Long id) throws Exception {
        Persona result = entityManager.find(Persona.class, id);
        return result != null ? Optional.of(result) : Optional.empty();
    }

    @Override
    public void update(Persona input) throws Exception {
        if (input == null) {
            throw new Exception("Problema valore in input");
        }

        input = entityManager.merge(input);
    }

    @Override
    public void insert(Persona input) throws Exception {
        if (input == null) {
            throw new Exception("Problema valore in input");
        }

        entityManager.persist(input);
    }

    @Override
    public void delete(Persona input) throws Exception {
        if (input == null) {
            throw new Exception("Problema valore in input");
        }
        entityManager.remove(entityManager.merge(input));
    }

    @Override
    public List<Persona> findByExample(Persona example) {
        Map<String, Object> paramaterMap = new HashMap<String, Object>();
        List<String> whereClauses = new ArrayList<String>();

        StringBuilder queryBuilder = new StringBuilder("select p from Persona p where p.id = p.id ");

        if (StringUtils.isNotEmpty(example.getNome())) {
            whereClauses.add(" p.nome  like :nome ");
            paramaterMap.put("nome", "%" + example.getNome() + "%");
        }
        if (StringUtils.isNotEmpty(example.getCognome())) {
            whereClauses.add(" p.cognome like :cognome ");
            paramaterMap.put("cognome", "%" + example.getCognome() + "%");
        }

        if (example.getDataNascita() != null) {
            whereClauses.add("p.dataNascita >= :dataNascita ");
            paramaterMap.put("dataNascita", example.getDataNascita());
        }

        queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
        queryBuilder.append(StringUtils.join(whereClauses, " and "));
        TypedQuery<Persona> typedQuery = entityManager.createQuery(queryBuilder.toString(), Persona.class);

        for (String key : paramaterMap.keySet()) {
            typedQuery.setParameter(key, paramaterMap.get(key));
        }

        return typedQuery.getResultList();
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

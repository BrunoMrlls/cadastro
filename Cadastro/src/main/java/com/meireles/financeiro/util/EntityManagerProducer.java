package com.meireles.financeiro.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * Classe produtora de EntityManager pq precisa ele precisa ser injetado de alguma forma, e
 * criei essa classe somente para injetar o EntityManager
 */
@ApplicationScoped
public class EntityManagerProducer {
    private EntityManagerFactory factory;


    public EntityManagerProducer(){
        this.factory = Persistence.createEntityManagerFactory(
                "financeiroPU"
        );
    }

    @Produces
    @RequestScoped
    public EntityManager createEntityManager(){
        return factory.createEntityManager();
    }

    /**
     * Este método será chamado automaticamente quando o contexto onde o objeto foi
     * produzido for encerrado.
     * @param manager
     */
    public void closeEntityManager(@Disposes EntityManager manager){
        manager.close();
    }

}
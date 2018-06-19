package com.meireles.repository;

import com.meireles.financeiro.model.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

public class Pessoas implements Serializable {

    private static final long serialVersionUID = 1L;
    private EntityManager manager;

    public Pessoas(EntityManager manager){
        this.manager = manager;
    }

    public Pessoa findById(Long id){
        return manager.find(Pessoa.class, id);
    }

    public List<Pessoa> todas(){
        TypedQuery<Pessoa> qry = manager.createQuery(
                "from Pessoa", Pessoa.class );
        return qry.getResultList();
    }
}

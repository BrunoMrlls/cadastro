package com.meireles.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.meireles.financeiro.model.Lancamento;

public class Lancamentos implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject
	public Lancamentos(EntityManager manager) {
		this.manager = manager;
	}


	public void adicionar(Lancamento lancamento){
	    this.manager.persist(lancamento);
	}

	public List<Lancamento> todos(){
		TypedQuery<Lancamento> qry = manager.createQuery(
				"from Lancamento", Lancamento.class);
		
		return qry.getResultList();
	}

    public List<String> descricoesQueContem(String s){
        TypedQuery<String> qry = manager.createQuery(
                "select distinct descricao from Lancamento " +
                        "where upper(descricao) like upper(:descricao) ",
                String.class);
        qry.setParameter("descricao", "%" + s + "%");
        return qry.getResultList();
    }
}

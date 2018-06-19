package com.meireles.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.meireles.financeiro.model.Lancamento;

public class Lancamentos implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
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

}

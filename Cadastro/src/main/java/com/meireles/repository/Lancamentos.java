package com.meireles.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.meireles.financeiro.model.Lancamento;
import com.meireles.financeiro.model.Pessoa;
import com.meireles.service.NegocioException;

public class Lancamentos implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject
	public Lancamentos(EntityManager manager) {
		this.manager = manager;
	}


	public void salvar(Lancamento lancamento) throws NegocioException {
	    guardar(lancamento);
	}

	public List<Lancamento> todos(){
		TypedQuery<Lancamento> qry = manager.createQuery(
				"from Lancamento", Lancamento.class);
		
		return qry.getResultList();
	}

	public Lancamento findById(Long id){
        return manager.find(Lancamento.class, id);
	}

	public Lancamento guardar(Lancamento lancamento){
	    return this.manager.merge(lancamento);
    }

    public void remove(Lancamento lancamento){
	    this.manager.remove(lancamento);
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

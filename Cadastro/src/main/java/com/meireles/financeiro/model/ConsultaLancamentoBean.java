package com.meireles.financeiro.model;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.meireles.financeiro.util.JpaUtil;
import com.meireles.repository.Lancamentos;

@ManagedBean
@ViewScoped
public class ConsultaLancamentoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<Lancamento> lancamentos;
	
	public void consultar() {
		EntityManager manager = JpaUtil.getEntityManager();
		Lancamentos lancamentos = new Lancamentos(manager);
		
		this.lancamentos = lancamentos.todos();
		
		manager.close();
	}
	
	public List<Lancamento> getLancamentos(){
		return lancamentos;
	}
}

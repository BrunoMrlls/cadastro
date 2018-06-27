package com.meireles.financeiro.model;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.meireles.financeiro.util.JpaUtil;
import com.meireles.repository.Lancamentos;

@Named
@javax.faces.view.ViewScoped //do pcte view para funcionar o CDI Injection
public class ConsultaLancamentoBean implements Serializable{

	@Inject
	private Lancamentos lancamentosRepository;

	private static final long serialVersionUID = 1L;
	private List<Lancamento> lancamentos;
	
	public void consultar() {
		this.lancamentos = this.lancamentosRepository.todos();
	}

	public List<Lancamento> getLancamentos(){
		return lancamentos;
	}
}

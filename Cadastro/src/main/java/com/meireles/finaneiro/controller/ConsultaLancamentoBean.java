package com.meireles.finaneiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.meireles.financeiro.model.Lancamento;
import com.meireles.financeiro.util.JpaUtil;
import com.meireles.repository.Lancamentos;
import com.meireles.service.CadastroLancamentos;
import com.meireles.service.NegocioException;

@Named
@javax.faces.view.ViewScoped //do pcte view para funcionar o CDI Injection
public class ConsultaLancamentoBean implements Serializable{

	@Inject
	private Lancamentos lancamentosRepository;

	@Inject
	private CadastroLancamentos cadastroLancamentos;
	private Lancamento lancamentoSelecionado;
	private static final long serialVersionUID = 1L;
	private List<Lancamento> lancamentos;
	
	public void consultar() {
		this.lancamentos = this.lancamentosRepository.todos();
	}

	public void excluir(){
		FacesContext context = FacesContext.getCurrentInstance();
		try{

			this.cadastroLancamentos.excluir(this.lancamentoSelecionado);
			this.consultar();

			context.addMessage(null, new FacesMessage(
			"Lançamento excluído com sucesso."));

		}catch (NegocioException e){

			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}


	public List<Lancamento> getLancamentos(){
		return lancamentos;
	}

	public Lancamento getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}

	public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}
}

package com.meireles.financeiro.model;

import com.meireles.financeiro.util.JpaUtil;
import com.meireles.repository.Lancamentos;
import com.meireles.repository.Pessoas;
import com.meireles.service.CadastroLancamentos;
import com.meireles.service.NegocioException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "cadastroLancamentoBean")
@ViewScoped
public class CadastroLancamentoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Lancamento lancamento = new Lancamento();
    private List<Pessoa> todasPessoas;

    public void prepararCadastro(){
        EntityManager manager = JpaUtil.getEntityManager();
        try{
            Pessoas pessoas = new Pessoas(manager);
            this.todasPessoas = pessoas.todas();
        }finally {
            manager.close();
        }
    }

    public void salvar(){
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        FacesContext context = FacesContext.getCurrentInstance();
        try{
            transaction.begin();
            CadastroLancamentos cadastroLancamentos = new CadastroLancamentos(
                    new Lancamentos(manager));
            cadastroLancamentos.salvar(this.lancamento);
        }catch (NegocioException e){

            transaction.rollback();

            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);

        }finally {
            manager.close();
        }
    }

    public List<Pessoa> getTodasPessoas(){
        return this.todasPessoas;
    }

    public TipoLancamento[] getTiposLancamentos(){
        return TipoLancamento.values();
    }

    public Lancamento getLancamento(){
        return this.lancamento;
    }

    public void setLancamento(Lancamento lancamento){
        this.lancamento = lancamento;
    }

}

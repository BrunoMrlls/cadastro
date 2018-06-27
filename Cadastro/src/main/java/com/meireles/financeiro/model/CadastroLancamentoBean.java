package com.meireles.financeiro.model;

import com.meireles.repository.Lancamentos;
import com.meireles.repository.Pessoas;
import com.meireles.service.CadastroLancamentos;
import com.meireles.service.NegocioException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@javax.faces.view.ViewScoped //do pacote view pra funcionar o CDI Injection
public class CadastroLancamentoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private CadastroLancamentos cadastro;

    @Inject
    private Pessoas pessoas;

    @Inject
    private Lancamentos lancamentos;

    private Lancamento lancamento = new Lancamento();
    private List<Pessoa> todasPessoas;

    public void prepararCadastro(){
        this.todasPessoas = this.pessoas.todas();
    }

    public void salvar(){
        FacesContext context = FacesContext.getCurrentInstance();
        try{

            this.cadastro.salvar(this.lancamento);

            this.lancamento = new Lancamento();

            context.addMessage(null, new FacesMessage(
                    "Lançamento realizado com sucesso."));

        }catch (NegocioException e){

            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);

        }
    }

    public List<String> pesquisarDescricoes(String s){
        return this.lancamentos.descricoesQueContem(s);
    }
    public void dataDeVencimentoAlterada(AjaxBehaviorEvent event){
        if (this.lancamento.getDtPag() == null && this.lancamento.getDtVenc() != null){
            this.lancamento.setDtPag(this.lancamento.getDtVenc());
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


package com.meireles.service;

import com.meireles.financeiro.model.Lancamento;
import com.meireles.financeiro.util.Transational;
import com.meireles.repository.Lancamentos;

import javax.inject.Inject;
import javax.transaction.TransactionScoped;
import java.io.Serializable;
import java.util.Date;

public class CadastroLancamentos implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private Lancamentos lancamentos;

    @Transational //Anotação para injetar a Transaction e EntityManager
    public void salvar(Lancamento lancamento) throws NegocioException {

        if (lancamento.getDtPag() != null &&
                    lancamento.getDtPag().after(new Date())){
            throw new NegocioException("Data de Pagamento não pode ser uma data futura.");
        }

        this.lancamentos.salvar(lancamento);

    }

    @Transational
    public void excluir(Lancamento lancamento) throws NegocioException{
        lancamento = this.lancamentos.findById(lancamento.getId()); //recupera objeto totalmente

        if ( lancamento.getDtPag() != null ){
            throw  new NegocioException("Não é possível excluir titulo que foi pago.");
        }

        this.lancamentos.remove(lancamento);
    }
}

package com.meireles.service;

import com.meireles.financeiro.model.Lancamento;
import com.meireles.repository.Lancamentos;

import java.io.Serializable;
import java.util.Date;

public class CadastroLancamentos implements Serializable {
    private static final long serialVersionUID = 1L;

    private Lancamentos lancamentos;

    public CadastroLancamentos(Lancamentos lancamentos){
        this.lancamentos = lancamentos;
    }

    public void salvar(Lancamento lancamento) throws NegocioException {

        if (lancamento.getDtPag() != null &&
                    lancamento.getDtPag().after(new Date())){
            throw new NegocioException("Data de Pagamento não pode ser uma data futura.");
        }

        this.lancamentos.adicionar(lancamento);

    }
}

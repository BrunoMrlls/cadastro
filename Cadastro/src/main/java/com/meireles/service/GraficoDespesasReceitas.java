package com.meireles.service;

import com.meireles.financeiro.util.JpaUtil;
import com.meireles.financeiro.util.Transational;
import com.meireles.repository.RepositoryGraficosLancamentos;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class GraficoDespesasReceitas implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Inject
    private RepositoryGraficosLancamentos repositoryGraficosLancamentos;

    @Transational
    public List<Object[]> obterListaQuatitativoReceitas(){
        List<Object[]> listagemReceitas = repositoryGraficosLancamentos.quantitativoReceitas();

        for (Object obj : listagemReceitas){

            Object[] x = (Object[])obj;
            Long quantidade = (Long)x[0];
            Date dataPagamento = (Date)x[1];

            System.out.println( quantidade + "|" + dataPagamento);

        }
        return listagemReceitas;
    }

}

package com.meireles.repository;

import com.meireles.financeiro.util.JpaUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class RepositoryGraficosLancamentos implements Serializable {
    private static final Long serialVersionUID = 1L;

    private EntityManager manager;
    @Inject
    public RepositoryGraficosLancamentos(EntityManager manager){
        this.manager = manager;
    }
    public List<Object[]> quantitativoReceitas(){

        Query q =  manager.createQuery(
                "select count(l.id), l.dtPag from Lancamento l\n" +
                        "where l.tpLanc = 'Receita'\n" +
                        "group by l.dtPag");


        return q.getResultList();
    }

//
//    public static void main(String[] args) {
//
//        EntityManager manager = JpaUtil.getEntityManager();
//        List<Object[]> retorno =  manager.createQuery(
//                        "select count(l.id), l.dtPag from Lancamento l\n" +
//                                "where l.tpLanc = 'Receita'\n" +
//                                "group by l.dtPag").getResultList();
//
//        for (Object obj : retorno){
//
//            Object[] x = (Object[])obj;
//            Long quantidade = (Long)x[0];
//            Date dataPagamento = (Date)x[1];
//
//            System.out.println( quantidade + "|" + dataPagamento);
//
//        }
//
//        manager.close();
//
//    }
}

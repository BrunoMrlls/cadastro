package com.meireles.servlet;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.meireles.financeiro.model.Lancamento;
import com.meireles.financeiro.model.Pessoa;
import com.meireles.financeiro.model.TipoLancamento;
import com.meireles.financeiro.util.JpaUtil;

public class CriaLancamentos {
	
	public static void main(String[] args) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		
		Calendar dtVenc1 = Calendar.getInstance();
		dtVenc1.set(2015, 06, 28, 0, 0, 0);
		
		Calendar dtVenc2 = Calendar.getInstance();
		dtVenc2.set(2018, 01, 12, 0, 0, 0);
		
		Pessoa cliente = new Pessoa();
		cliente.setNome("Bruno Brito Meireles");
		
		Pessoa fornecedor = new Pessoa();
		fornecedor.setNome("Spotify inc");
		
		Lancamento lanc1 = new Lancamento();
		lanc1.setDescricao("Venda de licensa de software.");
		lanc1.setPessoa(cliente);
		lanc1.setDtPag(dtVenc1.getTime());
		lanc1.setDtVenc(dtVenc1.getTime());
		lanc1.setVrLanc(new BigDecimal(103_000));
		lanc1.setTpLanc(TipoLancamento.RECEITA);
		
		
		Lancamento lanc2 = new Lancamento();
		lanc2.setDescricao("Venda de suporte anual");
		lanc2.setPessoa(cliente);
		lanc2.setDtPag(dtVenc1.getTime());
		lanc2.setDtVenc(dtVenc1.getTime());
		lanc2.setVrLanc(new BigDecimal(15_000));
		lanc2.setTpLanc(TipoLancamento.RECEITA);
		
		
		Lancamento lanc3 = new Lancamento();
		lanc3.setDescricao("Treinamento de equipe.");
		lanc3.setPessoa(fornecedor);
		lanc3.setDtPag(dtVenc2.getTime());
		lanc3.setDtVenc(dtVenc2.getTime());
		lanc3.setVrLanc(new BigDecimal(68_000));
		lanc3.setTpLanc(TipoLancamento.DESPESA);
		
		manager.persist(cliente);
		manager.persist(fornecedor);
		manager.persist(lanc1);
		manager.persist(lanc2);
		manager.persist(lanc3);
		
		transaction.commit();
		manager.close();
	System.out.println("Fim Inserção");
	}

}

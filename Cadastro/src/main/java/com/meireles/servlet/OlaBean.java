package com.meireles.servlet;

@javax.faces.bean.ManagedBean
public class OlaBean {
	
	private String nome;
	private String sobreNome;
	private String nomeCompleto;
	
	public void dizerAlo() {
		this.nomeCompleto = this.nome.toUpperCase()+" "+
				this.sobreNome.toUpperCase();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

}

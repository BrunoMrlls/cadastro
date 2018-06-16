package com.meireles.financeiro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="lanc")
public class Lancamento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="pess_id")
	private Pessoa pessoa;
	
	@Column(length=80, nullable=false)
	private String descricao;
	
	@Column(precision=10,scale=2, nullable=false)
	private BigDecimal vrLanc;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private TipoLancamento tpLanc;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dtvenc", nullable=false)
	private Date dtVenc;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dtpag", nullable=true)
	private Date dtPag;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getVrLanc() {
		return vrLanc;
	}
	public void setVrLanc(BigDecimal vrLanc) {
		this.vrLanc = vrLanc;
	}
	public TipoLancamento getTpLanc() {
		return tpLanc;
	}
	public void setTpLanc(TipoLancamento tpLanc) {
		this.tpLanc = tpLanc;
	}
	public Date getDtVenc() {
		return dtVenc;
	}
	public void setDtVenc(Date dtVenc) {
		this.dtVenc = dtVenc;
	}
	public Date getDtPag() {
		return dtPag;
	}
	public void setDtPag(Date dtPag) {
		this.dtPag = dtPag;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((dtPag == null) ? 0 : dtPag.hashCode());
		result = prime * result + ((dtVenc == null) ? 0 : dtVenc.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		result = prime * result + ((tpLanc == null) ? 0 : tpLanc.hashCode());
		result = prime * result + ((vrLanc == null) ? 0 : vrLanc.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (dtPag == null) {
			if (other.dtPag != null)
				return false;
		} else if (!dtPag.equals(other.dtPag))
			return false;
		if (dtVenc == null) {
			if (other.dtVenc != null)
				return false;
		} else if (!dtVenc.equals(other.dtVenc))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		if (tpLanc != other.tpLanc)
			return false;
		if (vrLanc == null) {
			if (other.vrLanc != null)
				return false;
		} else if (!vrLanc.equals(other.vrLanc))
			return false;
		return true;
	}
	
}

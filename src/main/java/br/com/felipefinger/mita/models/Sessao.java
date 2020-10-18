package br.com.felipefinger.mita.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_SESSAO")
public class Sessao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private long codigoPaciente;

	private Long numeroSessao;
	
	private String data;
	
	private Long eva;
	
	private String observacoes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCodigoPaciente() {
		return codigoPaciente;
	}

	public void setCodigoPaciente(long codigoPaciente) {
		this.codigoPaciente = codigoPaciente;
	}
	public Long getNumeroSessao() {
		return numeroSessao;
	}

	public void setNumeroSessao(Long numeroSessao) {
		this.numeroSessao = numeroSessao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Long getEva() {
		return eva;
	}

	public void setEva(Long eva) {
		this.eva = eva;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}

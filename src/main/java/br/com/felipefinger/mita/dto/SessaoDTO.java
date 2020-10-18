package br.com.felipefinger.mita.dto;

import java.io.Serializable;
import java.util.List;

import br.com.felipefinger.mita.models.Sessao;

public class SessaoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	private String nomePaciente;

	private List<Sessao> listaSessoes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public List<Sessao> getListaSessoes() {
		return listaSessoes;
	}

	public void setListaSessoes(List<Sessao> listaSessoes) {
		this.listaSessoes = listaSessoes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}

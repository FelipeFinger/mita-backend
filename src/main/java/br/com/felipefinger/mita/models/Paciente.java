package br.com.felipefinger.mita.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_PACIENTE")
public class Paciente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nome;

	private String cpf;
	
	private String sexo;
	
	private String nascimento;
	
	private String profissao;

	private String telefone;
	
	private String tratamentosComplementares;
	
	private String medicamentosUtilizados;
	
	private Long eva;

	private String observacoes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTratamentosComplementares() {
		return tratamentosComplementares;
	}

	public void setTratamentosComplementares(String tratamentosComplementares) {
		this.tratamentosComplementares = tratamentosComplementares;
	}

	public String getMedicamentosUtilizados() {
		return medicamentosUtilizados;
	}

	public void setMedicamentosUtilizados(String medicamentosUtilizados) {
		this.medicamentosUtilizados = medicamentosUtilizados;
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

package br.com.felipefinger.mita.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
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
}

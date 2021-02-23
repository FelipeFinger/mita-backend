package br.com.felipefinger.mita.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class PacienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;

	private String cpf;

	private String sexo;

	private String telefone;

	private String nascimento;

	private String profissao;

	private Long eva;

	private Long evaAtual;

	private String tratamentosComplementares;

	private String medicamentosUtilizados;

	private String observacoes;
}

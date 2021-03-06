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
@Table(name = "TB_SESSAO")
public class Sessao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private long codigoPaciente;

	private Long numeroSessao;
	
	private String fisioterapeuta;
	
	private String data;
	
	private Long eva;
	
	private String observacoes;
}

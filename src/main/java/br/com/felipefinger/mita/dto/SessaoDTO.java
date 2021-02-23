package br.com.felipefinger.mita.dto;

import java.io.Serializable;
import java.util.List;

import br.com.felipefinger.mita.models.Sessao;
import lombok.Data;

@Data
public class SessaoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	private String nomePaciente;

	private List<Sessao> listaSessoes;

}

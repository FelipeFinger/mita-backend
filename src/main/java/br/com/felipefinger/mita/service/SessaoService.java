package br.com.felipefinger.mita.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipefinger.mita.dto.SessaoDTO;
import br.com.felipefinger.mita.models.Sessao;
import br.com.felipefinger.mita.repository.SessaoRepository;

@Service
public class SessaoService {

	@Autowired
	SessaoRepository sessaoRepository;

	@Autowired
	PacienteService pacienteService;

	public SessaoDTO adquirir(Long codigoPaciente) throws Exception {

		SessaoDTO sessaoDTO = new SessaoDTO();

		sessaoDTO.setNomePaciente(pacienteService.adquirirNome(codigoPaciente));

		sessaoDTO.setListaSessoes(sessaoRepository.findByCodigoPacienteOrderByNumeroSessao(codigoPaciente));

		return sessaoDTO;
	}

	public Sessao salvar(Sessao sessao) throws Exception {

		Optional<Sessao> optionalSessao = sessaoRepository
				.findTopByCodigoPacienteOrderByNumeroSessaoDesc(sessao.getCodigoPaciente());

		if (!optionalSessao.isPresent()) {

			sessao.setNumeroSessao(0L);
		}

		sessao.setNumeroSessao(optionalSessao.get().getNumeroSessao() + 1L);

		return sessaoRepository.save(sessao);
	}

	public Long adquirirEvaUltimaSessaoPaciente(Long codigoPaciente) {

		Optional<Sessao> optionalSessao = sessaoRepository
				.findTopByCodigoPacienteOrderByNumeroSessaoDesc(codigoPaciente);

		if (!optionalSessao.isPresent()) {

			return -1L;
		}

		return optionalSessao.get().getEva();
	}
}

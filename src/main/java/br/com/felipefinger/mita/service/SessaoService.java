package br.com.felipefinger.mita.service;

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
	
	public SessaoDTO adquirir(Long codigoPaciente) {
		
		SessaoDTO sessaoDTO = new SessaoDTO();
		
		sessaoDTO.setNomePaciente(pacienteService.adquirirNome(codigoPaciente));
		
		sessaoDTO.setListaSessoes(sessaoRepository.findSessaoByCodigoPaciente(codigoPaciente));
		
		return sessaoDTO;
	}

	public Sessao salvar(Sessao sessao) throws Exception{
		
		Long ultimaSessao = sessaoRepository.findNumeroUtilmaSessao(sessao.getCodigoPaciente());

		if(ultimaSessao == null) {
			sessao.setNumeroSessao(1L);
		}
		else {
			sessao.setNumeroSessao(ultimaSessao + 1);
		}
		
		pacienteService.atualizarEva(sessao.getCodigoPaciente(), sessao.getEva());
		
		return sessaoRepository.save(sessao);
	}
}

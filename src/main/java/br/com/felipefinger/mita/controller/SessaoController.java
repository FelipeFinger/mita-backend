package br.com.felipefinger.mita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipefinger.mita.dto.SessaoDTO;
import br.com.felipefinger.mita.models.Sessao;
import br.com.felipefinger.mita.repository.SessaoRepository;
import br.com.felipefinger.mita.service.SessaoService;

@RestController
@RequestMapping(value = "/sessao")
public class SessaoController {

	@Autowired
	SessaoRepository sessaoRepository;

	@Autowired
	SessaoService sessaoService;

	@GetMapping("/adquirir/{id}")
	public SessaoDTO adquirir(@PathVariable(value = "id") long codigoPaciente) throws Exception{
		
		return sessaoService.adquirir(codigoPaciente);
	}
	
	@PostMapping("/salvar")
	public Sessao salvar(@RequestBody Sessao sessao) throws Exception{
				
		return sessaoService.salvar(sessao);
	}
	
}

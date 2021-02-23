package br.com.felipefinger.mita.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipefinger.mita.dto.PacienteDTO;
import br.com.felipefinger.mita.models.Paciente;
import br.com.felipefinger.mita.repository.PacienteRepository;
import br.com.felipefinger.mita.service.PacienteService;

@RestController
@RequestMapping(value = "/pacientes")
public class PacienteController {

	@Autowired
	PacienteRepository pacienteRepository;

	@Autowired
	PacienteService pacienteService;

	@GetMapping("/adquirir")
	public List<Paciente> adquirir(String busca) {

		return pacienteService.adquirirBusca(busca);
	}

	@GetMapping("/adquirirNome/{id}")
	public String adquirirNome(@PathVariable(value = "id") long id) throws Exception {

		return pacienteService.adquirirNome(id);
	}

	@GetMapping("/adquirir/{id}")
	public PacienteDTO adquirir(@PathVariable(value = "id") long id) throws Exception {

		return pacienteService.adquirir(id);
	}

	@PostMapping("/salvar")
	public Paciente salvar(@RequestBody Paciente paciente) throws Exception {

		return pacienteService.salvar(paciente);
	}

	@DeleteMapping("/excluir/{id}")
	public void excluir(@PathVariable(value = "id") long id) {

		pacienteRepository.delete(pacienteRepository.findById(id));
	}

	@PutMapping("/atualizar")
	public Paciente atualizar(@RequestBody Paciente paciente) {

		paciente.setNome(paciente.getNome().toUpperCase());
		return pacienteRepository.save(paciente);
	}
}

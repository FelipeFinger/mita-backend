package br.com.felipefinger.mita.resources;

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

import br.com.felipefinger.mita.models.Paciente;
import br.com.felipefinger.mita.repository.PacienteRepository;

@RestController
@RequestMapping(value = "/pacientes")
public class PacienteResource {

	@Autowired
	PacienteRepository pacienteRepository;
	
	@GetMapping("/adquirirTodos")
	public List<Paciente> adquirirTodos(){
		
		return pacienteRepository.findAllByOrderByIdDesc();
	}
	
	@GetMapping("/pesquisar")
	public List<Paciente> pesquisar(String nome){
		
		return pacienteRepository.findPacienteByName(nome.toUpperCase());
	}
	
	@GetMapping("/adquirir/{id}")
	public Paciente adquirir(@PathVariable(value = "id") long id){
		
		return pacienteRepository.findById(id);
	}
	
	@PostMapping("/salvar")
	public Paciente salvar(@RequestBody Paciente paciente){
		
		paciente.setNome(paciente.getNome().toUpperCase());
		return pacienteRepository.save(paciente);
	}
	
	@DeleteMapping("/excluir/{id}")
	public void excluir(@PathVariable(value = "id") long id){
		
		pacienteRepository.delete(pacienteRepository.findById(id));
	}
	
	@PutMapping("/atualizar")
	public Paciente atualizar(@RequestBody Paciente paciente) {
		
		return pacienteRepository.save(paciente);
	}
}

package br.com.felipefinger.mita.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipefinger.mita.models.Paciente;
import br.com.felipefinger.mita.repository.PacienteRepository;

@Service
public class PacienteService {
	
	@Autowired
	PacienteRepository pacienteRepository;

	public List<Paciente> adquirirBusca(String busca){
		
		List<Paciente> listaPacientes = pacienteRepository.findPacienteByNameOrderByName(busca);
		
		listaPacientes.addAll(pacienteRepository.findPacienteByCpfOrderByName(busca));

		return listaPacientes;
	}
	
	public Paciente salvar(Paciente paciente) throws Exception{
		
		paciente.setNome(this.formatarNome(paciente.getNome()));

		return pacienteRepository.save(paciente);
	}	
	
	private String formatarNome(String nomeCompleto) {
		
		nomeCompleto = nomeCompleto.toLowerCase();
		String[] nomes = nomeCompleto.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < nomes.length; i++) {
			String nome = nomes[i];
			nome = nome.substring(0, 1).toUpperCase() + nome.substring(1);			
            sb.append(" ").append(nome);    
        }
		return sb.toString().replaceFirst(" ", "");
	}
}

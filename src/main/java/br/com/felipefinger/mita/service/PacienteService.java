package br.com.felipefinger.mita.service;

import java.util.List;
import java.util.Optional;

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
		
		if(paciente.getEvaAtual() == null) {
			
			paciente.setEvaAtual(paciente.getEva());
		}

		return pacienteRepository.save(paciente);
	}	
	
	public void atualizarEva(Long codigoPaciente, Long eva) throws Exception{
		
		Optional<Paciente> paciente = pacienteRepository.findById(codigoPaciente);
		
		if(paciente.isPresent()) {
			
			paciente.get().setEvaAtual(eva);
			pacienteRepository.save(paciente.get());
		}

	}
	
	public String adquirirNome(Long codigoPaciente) {
		
		return pacienteRepository.findNameById(codigoPaciente);
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

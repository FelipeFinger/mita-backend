package br.com.felipefinger.mita.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipefinger.mita.dto.PacienteDTO;
import br.com.felipefinger.mita.models.Paciente;
import br.com.felipefinger.mita.repository.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private SessaoService sessaoService;

	@Autowired
	private ModelMapper modelMapper;

	public List<Paciente> adquirirBusca(String busca) {

		if (busca == null) {

			return pacienteRepository.findAll();
		}

		List<Paciente> listaPacientes = pacienteRepository.findByNomeContainsOrderByNome(busca);

		listaPacientes.addAll(pacienteRepository.findByCpfContainsOrderByNome(busca));

		return listaPacientes;
	}

	public PacienteDTO adquirir(Long id) throws Exception {

		Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);

		if (!optionalPaciente.isPresent()) {

			throw new Exception("Paciente não encontrado");
		}

		Long evaAtual = sessaoService.adquirirEvaUltimaSessaoPaciente(id);

		PacienteDTO pacienteDTO = modelMapper.map(optionalPaciente.get(), PacienteDTO.class);

		pacienteDTO.setEvaAtual(evaAtual == -1L ? optionalPaciente.get().getEva() : evaAtual);

		return pacienteDTO;
	}

	public Paciente salvar(Paciente paciente) throws Exception {

		paciente.setNome(this.formatarNome(paciente.getNome()));

		return pacienteRepository.save(paciente);
	}

	public String adquirirNome(Long codigoPaciente) throws Exception {

		Optional<Paciente> optionalPaciente = pacienteRepository.findById(codigoPaciente);

		if (!optionalPaciente.isPresent()) {

			throw new Exception("Paciente não encontrado");
		}

		return optionalPaciente.get().getNome();
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

package br.com.felipefinger.mita.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.felipefinger.mita.models.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

	Paciente findById(long id);

	List<Paciente> findAllByOrderByIdDesc();

	List<Paciente> findByNomeContainsOrderByNome(String nome);

	List<Paciente> findByCpfContainsOrderByNome(String cpf);

	public abstract Optional<Paciente> findById(Long id);

}

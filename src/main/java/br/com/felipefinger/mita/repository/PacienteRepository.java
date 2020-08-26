package br.com.felipefinger.mita.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.felipefinger.mita.models.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{
	
	Paciente findById(long id);

}

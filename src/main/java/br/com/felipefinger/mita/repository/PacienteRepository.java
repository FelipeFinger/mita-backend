package br.com.felipefinger.mita.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.felipefinger.mita.models.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{
	
	Paciente findById(long id);
	
	List<Paciente> findAllByOrderByIdDesc();
	
	@Query("select p from Paciente p where p.nome LIKE %?1%")
	List<Paciente> findPacienteByName(String nome);

}

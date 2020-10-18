package br.com.felipefinger.mita.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.felipefinger.mita.models.Sessao;

public interface SessaoRepository extends JpaRepository<Sessao, Long>{
	
	Sessao findById(long id);

	@Query("select s from Sessao s where s.codigoPaciente = ?1 order by numeroSessao")	
	List<Sessao> findSessaoByCodigoPaciente(Long codigoPaciente);
	
	@Query("select max(s.numeroSessao) from Sessao s where s.codigoPaciente = ?1")	
	Long findNumeroUtilmaSessao(Long codigoPaciente);

}

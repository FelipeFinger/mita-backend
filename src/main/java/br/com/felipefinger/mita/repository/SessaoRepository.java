package br.com.felipefinger.mita.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.felipefinger.mita.models.Sessao;

@Repository
public interface SessaoRepository extends PagingAndSortingRepository<Sessao, Long> {

	public abstract Sessao findById(long id);

	public abstract List<Sessao> findByCodigoPacienteOrderByNumeroSessao(Long codigoPaciente);

	public abstract Optional<Sessao> findTopByCodigoPacienteOrderByNumeroSessaoDesc(Long codigoPaciente);

}

package br.com.felipefinger.mita.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.felipefinger.mita.models.Video;

public interface VideoRepository extends JpaRepository<Video, Long>{
	
	Video findById(long id);
	
	List<Video> findAllByOrderByIdDesc();
	
	@Query("select v from Video v where lower(v.titulo) like lower(concat('%', ?1,'%'))")
	List<Video> findVideoByTituloOrderByTitulo(String titulo);

}

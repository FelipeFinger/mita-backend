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

import br.com.felipefinger.mita.models.Video;
import br.com.felipefinger.mita.repository.VideoRepository;

@RestController
@RequestMapping(value = "/videos")
public class VideoResource {

	@Autowired
	VideoRepository videoRepository;
	
	@GetMapping("/adquirir")
	public List<Video> adquirir(String titulo){
		
		if(titulo == null) {
			
			return videoRepository.findAllByOrderByIdDesc();	
		}
		 	 
		return videoRepository.findVideoByTituloOrderByTitulo(titulo.toUpperCase());
	}

	@GetMapping("/adquirir/{id}")
	public Video adquirir(@PathVariable(value = "id") long id){
		
		return videoRepository.findById(id);
	}
	
	@PostMapping("/salvar")
	public Video salvar(@RequestBody Video video){
		
		video.setTitulo(video.getTitulo().toUpperCase());
		return videoRepository.save(video);
	}
	
	@DeleteMapping("/excluir/{id}")
	public void excluir(@PathVariable(value = "id") long id){
		
		videoRepository.delete(videoRepository.findById(id));
	}
	
	@PutMapping("/atualizar")
	public Video atualizar(@RequestBody Video video) {
		
		video.setTitulo(video.getTitulo().toUpperCase());
		return videoRepository.save(video);
	}
}

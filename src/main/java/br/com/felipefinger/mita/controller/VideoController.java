package br.com.felipefinger.mita.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipefinger.mita.dto.VideoDTO;
import br.com.felipefinger.mita.models.Video;
import br.com.felipefinger.mita.repository.VideoRepository;
import br.com.felipefinger.mita.service.VideoService;

@RestController
@RequestMapping(value = "/videos")
public class VideoController {

	@Autowired
	VideoRepository videoRepository;
	
	@Autowired
	VideoService videoService;

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
	
	@PostMapping(value="/salvar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Video salvar(@ModelAttribute VideoDTO videoDTO) throws Exception{
		
		return videoService.uploadVideo(videoDTO);
	}
	
	@PostMapping("/salvarlink")
	public Video salvarLink(@RequestBody Video video) throws Exception{
		
		return videoService.salvarVideo(video);
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

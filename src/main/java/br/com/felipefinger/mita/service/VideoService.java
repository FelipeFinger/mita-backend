package br.com.felipefinger.mita.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipefinger.mita.dto.VideoDTO;
import br.com.felipefinger.mita.models.Video;
import br.com.felipefinger.mita.repository.VideoRepository;

@Service
public class VideoService {
	
	@Autowired
	VideoRepository videoRepository;

	@SuppressWarnings("unused")
	public Video uploadVideo(VideoDTO videoDTO) throws Exception{
		
		File arqVideo = new File( videoDTO.getVideo().getOriginalFilename());
		videoDTO.getVideo().transferTo(arqVideo);

		String url = UploadVideoService.uploadVideo(arqVideo, videoDTO.getTitulo(), videoDTO.getDescricao());

		if(url != null) {
			
			Video video = new Video();
			video.setTitulo(videoDTO.getTitulo().toUpperCase());
			video.setDescricao(videoDTO.getDescricao());
			video.setUrl(url);
			
			return videoRepository.save(video);
		}
		
		return new Video();
	}
	
	public Video salvarVideo(Video video){

		return videoRepository.save(video);		
	}
	
}

package br.com.felipefinger.mita.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.felipefinger.mita.dto.VideoDTO;
import br.com.felipefinger.mita.models.Video;
import br.com.felipefinger.mita.repository.VideoRepository;

@Service
public class VideoService {

	@Autowired
	VideoRepository videoRepository;

	@SuppressWarnings("unused")
	public Video uploadVideo(VideoDTO videoDTO) throws Exception {
		
		videoDTO.setTitulo(this.formatarTitulo(videoDTO.getTitulo()));

		File arqVideo = this.convert(videoDTO.getVideo());

		String url = UploadVideoService.uploadVideo(arqVideo, videoDTO.getTitulo(), videoDTO.getDescricao());

		if (url != null) {

			Video video = new Video();
			video.setTitulo(videoDTO.getTitulo().toUpperCase());
			video.setDescricao(videoDTO.getDescricao());
			video.setUrl(url);

			return videoRepository.save(video);
		}

		return new Video();
	}
	
	private File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	public Video salvarVideo(Video video) {
		
		video.setTitulo(this.formatarTitulo(video.getTitulo()));

		return videoRepository.save(video);
	}

	private String formatarTitulo(String titulo) {

		titulo = titulo.toLowerCase();
		String[] palavras = titulo.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < palavras.length; i++) {
			String palavra = palavras[i];
			palavra = palavra.substring(0, 1).toUpperCase() + palavra.substring(1);
			sb.append(" ").append(palavra);
		}
		return sb.toString().replaceFirst(" ", "");
	}

}

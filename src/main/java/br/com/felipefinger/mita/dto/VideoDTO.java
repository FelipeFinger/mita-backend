package br.com.felipefinger.mita.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class VideoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	private String titulo;

	private String descricao;

	private MultipartFile video;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public MultipartFile getVideo() {
		return video;
	}

	public void setVideo(MultipartFile video) {
		this.video = video;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

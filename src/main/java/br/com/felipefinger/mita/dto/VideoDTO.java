package br.com.felipefinger.mita.dto;

import java.io.Serializable;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class VideoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	private String titulo;

	private String descricao;

	private MultipartFile video;
}

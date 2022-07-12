package org.serratec.sistemacompetenciaback.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TecnologiaDTO {

	
	@NotBlank(message = "Digite o nome da tecnologia")
	@Size(max = 255)
	private String nome;
	
	@NotBlank(message = "Digite a categoria da tecnologia")
	@Size(max = 255)
	private String categoria;

	public TecnologiaDTO() {
	}

	public TecnologiaDTO(String nome, String categoria) {
		this.nome = nome;
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}

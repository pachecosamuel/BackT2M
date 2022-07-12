package org.serratec.sistemacompetenciaback.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class CategoriaDTO {
	
	@NotBlank(message = "Digite o nome da categoria")
	@Size(max = 255)
	private String nome;

	@NotBlank(message = "Digite o nome da tecnologia")
	@Size(max = 255)
	private String tecnologias;

	public CategoriaDTO() {
	}

	public CategoriaDTO(String nome, String tecnologias) {
		this.nome = nome;
		this.tecnologias = tecnologias;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTecnologias() {
		return tecnologias;
	}

	public void setTecnologias(String tecnologias) {
		this.tecnologias = tecnologias;
	}
	
	
}

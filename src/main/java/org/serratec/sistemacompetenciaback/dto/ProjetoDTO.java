package org.serratec.sistemacompetenciaback.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ProjetoDTO {

	@NotBlank
	@Size(max = 255, message = "Tamanho máximo: 255 caracteres")
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}

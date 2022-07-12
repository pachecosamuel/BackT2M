package org.serratec.sistemacompetenciaback.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//@JsonIgnoreProperties
public class ConhecimentoDTO {

	@NotBlank
	@Size(max = 255, message = "Tamanho máximo: 255 caracteres")
	private String nome;

	@NotBlank
	@Size(max = 255, message = "Tamanho máximo: 255 caracteres")
	private String descricao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
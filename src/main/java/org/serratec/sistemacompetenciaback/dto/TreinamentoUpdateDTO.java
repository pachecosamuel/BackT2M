package org.serratec.sistemacompetenciaback.dto;

import javax.validation.constraints.Size;

public class TreinamentoUpdateDTO {

	@Size(max = 255, message = "Tamanho máximo: 255 caracteres")
	private String posicao;

	@Size(max = 255)
	private String descricao;

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

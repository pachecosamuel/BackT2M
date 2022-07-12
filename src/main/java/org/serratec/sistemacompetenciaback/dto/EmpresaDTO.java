package org.serratec.sistemacompetenciaback.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EmpresaDTO {
	
	
	@NotBlank(message = "informe o nome da empresa")
	@Size(max = 255)
	private String nome;
	
	@NotBlank(message = "informe o cnpj")
	@Size(max = 255)
	private String cnpj;
	
	@NotBlank(message = "informe a razão social")
	@Size(max = 255)
	private String razaoSocial;
	
	@NotBlank(message = "informe uma area de atuação")
	@Size(max = 225)
	private String areaAtuacao;
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(String areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}

}

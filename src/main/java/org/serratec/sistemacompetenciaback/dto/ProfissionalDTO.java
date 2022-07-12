package org.serratec.sistemacompetenciaback.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProfissionalDTO {

	@NotBlank
	@Size(max = 255, message = "Tamanho máximo: 255 caracteres")
	private String nome;
	
	@NotBlank
	@Size(max = 255, message = "Tamanho máximo: 255 caracteres")
	private String posicao;
	
	@NotBlank
	@Size(max = 255, message = "Tamanho máximo: 255 caracteres")
	private String projeto;
	
	@NotBlank
	@Size(max = 255, message = "Tamanho máximo: 255 caracteres")
	private String funcao;

	@NotBlank
	@Size(max = 255, message = "Tamanho máximo: 255 caracteres")
	private String email; // No mínimo 2. Mas por agora, apenas um.

	@NotNull
	private LocalDate dataDeNascimento;

	@NotBlank
	@Size(max = 255, message = "Tamanho máximo: 255 caracteres")
	private String cpf;

	@NotBlank
	@Size(max = 255, message = "Tamanho máximo: 255 caracteres")
	private String telefone;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public String getProjeto() {
		return projeto;
	}

	public void setProjeto(String projeto) {
		this.projeto = projeto;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}

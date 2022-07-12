package org.serratec.sistemacompetenciaback.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.serratec.sistemacompetenciaback.enums.PermisaoEnum;

public class UsuarioDTO {

	@NotBlank
	@Size(max = 255, message = "Tamanho máximo: 255 caracteres")
	private String login;

	@NotBlank
	@Size(max = 255, message = "Tamanho máximo: 255 caracteres")
	private String nome;

	@NotBlank
	@Size(max = 255, message = "Tamanho máximo: 255 caracteres")	
	private String email;

	@NotNull
	private Integer codigo;

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private PermisaoEnum permissao;

	@NotBlank
	@Size(max = 255, message = "Tamanho máximo: 255 caracteres")
	private String senha;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public PermisaoEnum getPermissao() {
		return permissao;
	}

	public void setPermissao(PermisaoEnum permissao) {
		this.permissao = permissao;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}

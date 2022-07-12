package org.serratec.sistemacompetenciaback.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.serratec.sistemacompetenciaback.enums.PermisaoEnum;
import org.serratec.sistemacompetenciaback.enums.TipoUsuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id;

	@Column(length = 255, nullable = false, unique = true)
	private String login;

	@Column(length = 255, nullable = false)
	private String nome;

	@Column(length = 255, nullable = false, unique = true)
	private String email;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfil = new HashSet<>();

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private PermisaoEnum permissao;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(length = 255, nullable = false)
	private String senha; // Não consta no texto que o João passou no WhatsApp, mas coloquei aqui

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Set<TipoUsuario> getPerfil() {
		return perfil.stream().map(x -> TipoUsuario.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(TipoUsuario novoPerfil) {
		perfil.add(novoPerfil.getCod());
	}

	public void updatePerfil(TipoUsuario novoPerfil) {
		perfil.clear();
		perfil.add(novoPerfil.getCod());
	}

}

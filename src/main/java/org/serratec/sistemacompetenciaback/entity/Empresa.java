package org.serratec.sistemacompetenciaback.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", length = 255, nullable = false)
	private String nome;

	@Column(name = "cnpj", length = 255, nullable = false)
	private String cnpj;

	@Column(name = "razaoSocial", length = 255, nullable = false)
	private String razaoSocial;

	@Column(name = "areaAtuacao", length = 225, nullable = false)
	private String areaAtuacao;

	@JsonIgnoreProperties("empresas")
	@ManyToMany
	@JoinTable(name = "empresa_profissional", joinColumns = @JoinColumn(name = "id_empresa"),
	inverseJoinColumns = @JoinColumn(name = "id_profissional"))
	private List<Profissional> profissionais = new ArrayList<>();


	public Empresa() {
	}

	public Empresa(Long id, String nome, String cnpj, String razaoSocial, String areaAtuacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.razaoSocial = razaoSocial;
		this.areaAtuacao = areaAtuacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<Profissional> getProfissionais() {
		return profissionais;
	}

	public void setProfissionais(List<Profissional> profissionais) {
		this.profissionais = profissionais;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		return Objects.equals(id, other.id);
	}
}

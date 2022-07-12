package org.serratec.sistemacompetenciaback.entity;

import java.io.Serializable;
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
public class Conhecimento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_conhecimento")
	private Long id;

	@Column(length = 255, nullable = false)
	private String nome;

	@Column(length = 255, nullable = false)
	private String descricao;

	@JsonIgnoreProperties("conhecimentos")
	@ManyToMany(mappedBy = "conhecimentos")
	private List<Requisito> requisitos = new ArrayList<>();

	@JsonIgnoreProperties("conhecimentos")
	@ManyToMany
	@JoinTable(name = "conhecimento_possivel_treinamento", joinColumns = @JoinColumn(name = "id_conhecimento"), inverseJoinColumns = @JoinColumn(name = "id_possivel_treinamento"))
	private List<PossivelTreinamento> possiveisTreinamentos = new ArrayList<>();

	public Conhecimento() {
	}

	public Conhecimento(Long id, String nome, String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Requisito> getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(List<Requisito> requisitos) {
		this.requisitos = requisitos;
	}

	public List<PossivelTreinamento> getPossiveisTreinamentos() {
		return possiveisTreinamentos;
	}

	public void setPossiveisTreinamentos(List<PossivelTreinamento> possiveisTreinamentos) {
		this.possiveisTreinamentos = possiveisTreinamentos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		Conhecimento other = (Conhecimento) obj;
		return Objects.equals(id, other.id);
	}

}

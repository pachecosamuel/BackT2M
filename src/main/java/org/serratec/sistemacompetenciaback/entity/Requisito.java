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
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Requisito implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_requisito")
	private Long id;

	@Column(length = 255, nullable = false)
	private String nome;

	@Column(length = 255, nullable = false)
	private String descricao;

	@JsonIgnoreProperties("requisitos")
	@JsonManagedReference
	@ManyToMany(mappedBy = "requisitos")
	private List<Treinamento> treinamentos;

	@JsonIgnoreProperties("requisitos")
	@ManyToMany
	@JoinTable(name = "requisito_conhecimento", joinColumns = @JoinColumn(name = "id_requisito"), inverseJoinColumns = @JoinColumn(name = "id_conhecimento"))
	List<Conhecimento> conhecimentos = new ArrayList<>();

	public Requisito() {
	}

	public Requisito(Long id, String nome, String descricao) {
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

	public List<Treinamento> getTreinamentos() {
		return treinamentos;
	}

	public void setTreinamentos(List<Treinamento> treinamentos) {
		this.treinamentos = treinamentos;
	}

	public List<Conhecimento> getConhecimentos() {
		return conhecimentos;
	}

	public void setConhecimentos(List<Conhecimento> conhecimentos) {
		this.conhecimentos = conhecimentos;
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
		Requisito other = (Requisito) obj;
		return Objects.equals(id, other.id);
	}

}

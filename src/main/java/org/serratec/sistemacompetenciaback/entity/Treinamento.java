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
public class Treinamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_treinamento")
	private Long id;

	@Column(length = 255, nullable = false)
	private String posicao;

	@Column(length = 255, nullable = false)
	private String descricao;

	@JsonIgnoreProperties("treinamentos")
	@ManyToMany
	@JoinTable(name = "treinamento_requisito", joinColumns = @JoinColumn(name = "id_treinamento"), inverseJoinColumns = @JoinColumn(name = "id_requisito"))
	private List<Requisito> requisitos = new ArrayList<>();

	
    //@JsonIgnore
    //@ManyToMany(mappedBy = "treinamento_id", cascade = CascadeType.ALL)
    //private Empresas empresas;


	public Treinamento() {
	}

	public Treinamento(Long id, String posicao, String descricao) {
		super();
		this.id = id;
		this.posicao = posicao;
		this.descricao = descricao;
		//this.empresas = empresas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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


	//public Empresas getEmpresas() {
	//	return empresas;
	//}

	//public void setEmpresas(Empresas empresas) {
	//	this.empresas = empresas;
	//}
	

	public List<Requisito> getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(List<Requisito> requisitos) {
		this.requisitos = requisitos;
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
		Treinamento other = (Treinamento) obj;
		return Objects.equals(id, other.id);

	} 

}

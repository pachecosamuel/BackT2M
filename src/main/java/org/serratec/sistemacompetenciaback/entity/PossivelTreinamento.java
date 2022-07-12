package org.serratec.sistemacompetenciaback.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class PossivelTreinamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_possivel_treinamento")
	private Long id;

	@Column(length = 255, nullable = false)
	private String nome;

	@Column(length = 255, nullable = false)
	private String descricao;

	@JsonIgnoreProperties("possiveisTreinamentos")
	@ManyToMany(mappedBy = "possiveisTreinamentos")
	private List<Conhecimento> conhecimentos;

	public PossivelTreinamento() {
	}

	public PossivelTreinamento(Long id, String nome, String descricao) {
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
		PossivelTreinamento other = (PossivelTreinamento) obj;
		return Objects.equals(id, other.id);
	}

}

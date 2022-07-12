package org.serratec.sistemacompetenciaback.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.serratec.sistemacompetenciaback.enums.PermisaoEnum;

@Entity
public class Colaborador implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_colaborador")
	private Long id;

	@Column(length = 255, nullable = false)
	private String nome;

	@Column(length = 255, nullable = false, unique = true)
	private String usuario; // Diferente da tabela 'Usuario', certo?

	@Column(length = 255, nullable = false, unique = true)
	private String email; // No mínimo 2. Mas por agora, apenas um.

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private PermisaoEnum permissao;

	@Column(length = 255, nullable = false)
	private String posicao; // Mudar para 'Treinamento', certo?

	@ManyToMany
	@JoinTable(
			name = "colaborador_projeto",
			joinColumns = @JoinColumn(name = "id_colaborador"),
			inverseJoinColumns = @JoinColumn(name = "id_projeto")
	)
	private List<Projeto> projetos = new ArrayList<>();

	@Column(length = 255, nullable = false)
	private String funcao; // Verificar depois se será uma tabela

	public Colaborador() {
	}

	public Colaborador(Long id, String nome, String usuario, String email, PermisaoEnum permissao, String posicao,
			String funcao) {
		super();
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.email = email;
		this.permissao = permissao;
		this.posicao = posicao;
		this.funcao = funcao;
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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
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
		Colaborador other = (Colaborador) obj;
		return Objects.equals(id, other.id);
	}

}

package org.serratec.sistemacompetenciaback.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;

import org.serratec.sistemacompetenciaback.entity.Projeto;
import org.serratec.sistemacompetenciaback.enums.PermisaoEnum;

public class ColaboradorUpdateDTO {

	@Size(max = 255, message = "Tamanho máximo: 255 caracteres")
	private String nome;

	@Size(max = 255, message = "Tamanho máximo: 255 caracteres")
	private String usuario; // Diferente da tabela 'Usuario', certo?

	@Size(max = 255, message = "Tamanho máximo: 255 caracteres")
	private String email; // No mínimo 2. Mas por agora, apenas um.

	@Enumerated(EnumType.ORDINAL)
	private PermisaoEnum permissao;

	@Size(max = 255, message = "Tamanho máximo: 255 caracteres")
	private String posicao; // Mudar para 'Treinamento', certo?

	private List<Projeto> projetos = new ArrayList<>();

	@Size(max = 255, message = "Tamanho máximo: 255 caracteres")
	private String funcao; // Verificar depois se será uma tabela

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

}

package org.serratec.sistemacompetenciaback.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.serratec.sistemacompetenciaback.enums.PermisaoEnum;

public class ColaboradorDTO {

	@NotBlank
	@Size(max = 255, message = "Tamanho máximo: 255 caracteres")
	private String nome;

	@NotBlank
	@Size(max = 255, message = "Tamanho máximo: 255 caracteres")
	private String usuario; // Diferente da tabela 'Usuario', certo?

	@NotBlank
	@Size(max = 255, message = "Tamanho máximo: 255 caracteres")
	private String email; // No mínimo 2. Mas por agora, apenas um.

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private PermisaoEnum permissao;

	@NotBlank
	@Size(max = 255, message = "Tamanho máximo: 255 caracteres")
	private String posicao; // Mudar para 'Treinamento', certo?

	/*
	 * @NotNull //@Size(min = 1, message = "Tamanho mínimo: 1 projeto") private
	 * List<Projeto> projetos = new ArrayList<>();
	 */

	/*
	 * @NotNull // Ver se não é redundante private Set<@Min(1) Long> idsProjetos =
	 * new HashSet<>();
	 */

	@NotBlank
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

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

}

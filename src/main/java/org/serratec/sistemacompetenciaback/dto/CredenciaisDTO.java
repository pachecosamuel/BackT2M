package org.serratec.sistemacompetenciaback.dto;

public class CredenciaisDTO {

	private String login;
	private String senha;
	public String getLogin() {
		return login;
	}
	public CredenciaisDTO() {
		
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

}

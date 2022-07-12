package org.serratec.sistemacompetenciaback.enums;

public enum PermisaoEnum {
	TECNOLOGIA(0), TREINAMENTO(1), TECNOLOGIA_TREINAMENTO(2), ADMIN(3);
	
	private int permisao;

	PermisaoEnum(int permisao) {
		this.setPermisao(permisao);
	}

	public int getPermisao() {
		return permisao;
	}

	public void setPermisao(int permisao) {
		this.permisao = permisao;
	}

}

package org.serratec.sistemacompetenciaback.enums;

public enum PerfilEnum {
	USER(0), ADM(1);

	private int perfil;

	PerfilEnum(int perfil) {
		this.setPerfil(perfil);
	}

	public int getPerfil() {
		return perfil;
	}

	public void setPerfil(int perfil) {
		this.perfil = perfil;
	}

}

package org.iaff.csiaff.model;

public enum EstadoCivil {

	NI("Não informado"),
	SO("Solteiro"),
	CA("Casado"),
	SE("Separado"),
	DI("Divorciado"),
	VI("Viúvo");

	private String descricao;
	
	EstadoCivil(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}


package org.iaff.csiaff.model;

public enum Sexo {

	M("Masculino"),
	F("Feminino");

	private String descricao;
	
	Sexo(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}


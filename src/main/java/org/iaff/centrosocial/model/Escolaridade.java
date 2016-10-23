package org.iaff.centrosocial.model;

public enum Escolaridade {

	E01("Não informada"),
	E02("Analfabeto"),
	E03("Lê e escreve"),
	E04("Ensino Fundamental completo"),
	E05("Ensino Fundamental incompleto"),
	E06("Ensino Médio completo"),
	E07("Ensino Médio incompleto"),
	E08("Superior completo"),
	E09("Superior incompleto");

	private String descricao;
	
	Escolaridade(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}


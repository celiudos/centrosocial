package org.iaff.csiaff.model;

public enum TipoCampo {

	T("Text"),
	C("Combo"),
	R("Radio");

	private String descricao;
	
	TipoCampo(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}


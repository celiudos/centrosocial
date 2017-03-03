package org.iaff.csiaff.model;

public enum TipoItem {

	A("Anamnese"),
	F("Ficha"),
	P("Procedimento");

	private String descricao;
	
	TipoItem(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}


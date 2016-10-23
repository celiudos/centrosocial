package org.iaff.centrosocial.model;

public enum TipoDocIdentidade {

	REG("Registro Geral"),
	CCL("Conselho de Classe"),
	CTR("Carteira de Trabalho"),
	CDI("Dispensa de Incorporação"),
	CRE("Certificado de Reservista"),
	PPO("Passaporte"),
	CNH("Carteira Nacional de Habilitaçao"),
	IFU("Identidade Funcional"),
	IDE("Estrangeiros - Min. Justiça");

	private String descricao;
	
	TipoDocIdentidade(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}


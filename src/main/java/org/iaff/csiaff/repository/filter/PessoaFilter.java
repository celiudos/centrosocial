package org.iaff.csiaff.repository.filter;

import org.iaff.csiaff.model.TipoDocumento;

public class PessoaFilter {

	private String nome;
	private String numeroDocumento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Object getNumeroDocumentoSemFormatacao() {
		return TipoDocumento.removerFormatacao(this.numeroDocumento);
	}

}

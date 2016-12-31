package org.iaff.csiaff.model;

import org.iaff.csiaff.model.validation.group.CpfGroup;
import org.iaff.csiaff.model.validation.group.OutrosGroup;

public enum TipoDocumento {

	// http://turing.com.br/material/regex/introducao.html
	// https://support.office.com/pt-br/article/Controlar-formatos-de-entrada-de-dados-com-m%C3%A1scaras-de-entrada-e125997a-7791-49e5-8672-4a47832de8da#__toc292266520
	NI("Não informado", "Documento", "", OutrosGroup.class) {
		@Override
		public String formatar(String numeroDocumento) {
			return numeroDocumento;
		}
	},
	CPF("CPF", "CPF", "000.000.000-00", CpfGroup.class) {
		@Override
		public String formatar(String numeroDocumento) {
			return numeroDocumento.replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1.$2.$3-");
		}
	}, 
	TITULOELEITORAL("Título Eleitoral", "Título Eleitor", "0000 0000 0000", OutrosGroup.class) {
		@Override
		public String formatar(String numeroDocumento) {
			return numeroDocumento.replaceAll("(\\d{4})(\\d{4})(\\d{4})", "$1 $2 $3");
		}
	},
	RG("Registro Geral", "RG", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", OutrosGroup.class) {
		@Override
		public String formatar(String numeroDocumento) {
			return numeroDocumento;
		}
	},
	CERTIDAONASCIMENTO("Certidão de Nascimento", "Certidão de Nascimento", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", OutrosGroup.class) {
		@Override
		public String formatar(String numeroDocumento) {
			return numeroDocumento;
		}
	},
	CNH("Carteira Nacional de Habilitação", "CNH", "00000000000", OutrosGroup.class) {
		@Override
		public String formatar(String numeroDocumento) {
			return numeroDocumento;
		}
	},
	CTPS("Carteira de Trabalho", "CTPS", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", OutrosGroup.class) {
		@Override
		public String formatar(String numeroDocumento) {
			return numeroDocumento;
		}
	},
	// http://fato.info/consulta-do-certificado-militar/
	CAM("Certificado de Alistamento Militar", "CAM", "00 000 000000 0", OutrosGroup.class) {
		@Override
		public String formatar(String numeroDocumento) {
			return numeroDocumento.replaceAll("(\\d{2})(\\d{3})(\\d{6})(\\d{1})", "$1 $2 $3 $4");
		}
	};

	private String descricao;
	private String documento;
	private String mascara;
	private Class<?> grupo;

	TipoDocumento(String descricao, String documento, String mascara, Class<?> grupo) {
		this.descricao = descricao;
		this.documento = documento;
		this.mascara = mascara;
		this.grupo = grupo;
	}
	
	public abstract String formatar(String numeroDocumento);

	public String getDescricao() {
		return descricao;
	}

	public String getDocumento() {
		return documento;
	}

	public String getMascara() {
		return mascara;
	}

	public Class<?> getGrupo() {
		return grupo;
	}
	
	public static String removerFormatacao(String numeroDocumento) {
		if(numeroDocumento != null){
			return numeroDocumento.replaceAll("\\.|-|/| ", "").toUpperCase();
		}
		
		return null;
	}

}

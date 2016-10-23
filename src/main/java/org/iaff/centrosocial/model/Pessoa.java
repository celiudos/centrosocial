package org.iaff.centrosocial.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPessoa;

	// incio cadastro basico
	@NotEmpty(message = "Nome obrigatório")
	private String nome;

	@Column(length = 1)
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	@NotNull(message = "Date de nascimento é obrigatória")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	private String naturalidade;

	@Enumerated(EnumType.ORDINAL)
	private EstadoCivil estadoCivil;
	
	private String nomePai;

	private String nomeMae;
	
	private String profissao;
	
	@Enumerated(EnumType.ORDINAL)
	private Escolaridade escolaridade;
	// fim cadastro basico

	// inicio documento de identificacao
	@Enumerated(EnumType.ORDINAL)
	private TipoDocIdentidade tipoDocIdentidade;
	private String numDocIdentidade;
	private String orgaoExpedidor; 
	// fim documento de identificacao
	
	// inicio endereco
	private String endereco;
	private String bairro;
	private String cidade;

	@Column(length = 2)
	@Enumerated(EnumType.STRING)
	private UF uf;

	// http://api.postmon.com.br/v1/cep/71676135
	private String cep;
	// fim endereco

	// inicio contato
	private String foneFixo;
	private String foneCelular;
	private String foneRecado;
	private String foneEmergencia;
	private String email;
	// fim contato

	// indicador de cadastro completo
	private boolean completo;
	
	// inicio getters and setters
	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public Escolaridade getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}

	public TipoDocIdentidade getTipoDocIdentidade() {
		return tipoDocIdentidade;
	}

	public void setTipoDocIdentidade(TipoDocIdentidade tipoDocIdentidade) {
		this.tipoDocIdentidade = tipoDocIdentidade;
	}

	public String getNumDocIdentidade() {
		return numDocIdentidade;
	}

	public void setNumDocIdentidade(String numDocIdentidade) {
		this.numDocIdentidade = numDocIdentidade;
	}

	public String getOrgaoExpedidor() {
		return orgaoExpedidor;
	}

	public void setOrgaoExpedidor(String orgaoExpedidor) {
		this.orgaoExpedidor = orgaoExpedidor;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getFoneFixo() {
		return foneFixo;
	}

	public void setFoneFixo(String foneFixo) {
		this.foneFixo = foneFixo;
	}

	public String getFoneCelular() {
		return foneCelular;
	}

	public void setFoneCelular(String foneCelular) {
		this.foneCelular = foneCelular;
	}

	public String getFoneRecado() {
		return foneRecado;
	}

	public void setFoneRecado(String foneRecado) {
		this.foneRecado = foneRecado;
	}

	public String getFoneEmergencia() {
		return foneEmergencia;
	}

	public void setFoneEmergencia(String foneEmergencia) {
		this.foneEmergencia = foneEmergencia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isCompleto() {
		return completo;
	}

	public void setCompleto(boolean completo) {
		this.completo = completo;
	}
	// fim getters and setters

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPessoa == null) ? 0 : idPessoa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (idPessoa == null) {
			if (other.idPessoa != null)
				return false;
		} else if (!idPessoa.equals(other.idPessoa))
			return false;
		return true;
	}

}

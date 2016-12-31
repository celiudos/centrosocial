package org.iaff.csiaff.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import org.hibernate.validator.group.GroupSequenceProvider;
import org.iaff.csiaff.model.validation.group.CpfGroup;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.iaff.csiaff.model.validation.PessoaGroupSequenceProvider;

@Entity
@Table(name = "pessoa")
@GroupSequenceProvider(PessoaGroupSequenceProvider.class)
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotBlank(message = "Nome é obrigatório")
	private String nome;

	@NotNull(message = "Obrigatório informar o sexo")
	@Column(length = 1)
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	@NotNull(message = "Data de nascimento obrigatória")
	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;
	
	@Transient
	private Estado estadoNascimento;
	
	@ManyToOne
	@JoinColumn(name = "codigo_naturalidade")
	private Cidade naturalidade;
	
	private String nomePai;

	private String nomeMae;
	
	@Enumerated(EnumType.ORDINAL)
	private EstadoCivil estadoCivil;

	@Enumerated(EnumType.ORDINAL)
	private Escolaridade escolaridade;
	
	private String profissao;
	
	//@NotNull(message = "Tipo de documento é obrigatório")
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "tipo_documento")
	private TipoDocumento tipoDocumento;

	//@NotBlank(message = "Documento obrigatório")
	@CPF(groups = CpfGroup.class)
	@Column(name = "numero_documento")
	private String numeroDocumento;

	@NotBlank(message = "Obrigatório informar o telefone")
	private String telefone;

	@Email(message = "E-mail inválido")
	private String email;

	@Valid
	@JsonIgnore
	@Embedded
	private Endereco endereco;
	
	// indicador de cadastro completo
	private boolean completo;
	
	@PrePersist @PreUpdate
	private void prePersistPreUpdate() {
			this.numeroDocumento = retornaNumeroDocumentoSemFormatacao();
	}
	
	@PostLoad
	private void postLoad() {
		if(!StringUtils.isEmpty(this.numeroDocumento))
			this.numeroDocumento = this.tipoDocumento.formatar(this.numeroDocumento);
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public boolean isNova() {
		return codigo == null;
	}
	
	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    this.dataNascimento = LocalDate.parse(dataNascimento,formatter);
	}
	
	public Estado getEstadoNascimento() {
		return estadoNascimento;
	}

	public void setEstadoNascimento(Estado estadoNascimento) {
		this.estadoNascimento = estadoNascimento;
	}

	public Cidade getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(Cidade naturalidade) {
		this.naturalidade = naturalidade;
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

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Escolaridade getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}
	
	public boolean isCompleto() {
		return completo;
	}

	public void setCompleto(boolean completo) {
		this.completo = completo;
	}
	
	public String getTipoComDocumento () {
		if(!StringUtils.isEmpty(this.numeroDocumento)) {
			return this.tipoDocumento.getDocumento() + " / " + this.numeroDocumento;
		}
		
		return null;
	}

	public String getDataNascimentoString () {
		return "" + this.dataNascimento.getDayOfMonth() + "/" + this.dataNascimento.getMonthValue() + "/" + this.dataNascimento.getYear();  
		// return "" + this.dataNascimento.getDayOfMonth() + "/" + this.dataNascimento.getMonthOfYear() + "/" + this.dataNascimento.getYear();
	}
	
	public String retornaNumeroDocumentoSemFormatacao() {
		return TipoDocumento.removerFormatacao(this.numeroDocumento);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}

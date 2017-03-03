package org.iaff.csiaff.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "itemprontuario")
public class ItemProntuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	/* bidirecional
	@ManyToOne(optional = false)
	@JoinColumn(name="codigo_paciente")
	private Paciente paciente;
	*/
	
	// unidirecional
	@ManyToOne
	@JoinColumn(name="codigo_tipoitemprontuario", nullable=false)
	private TipoItemProntuario tipoitemprontuario;
	
	@NotBlank(message = "Valor é obrigatório")
	private String Valor;
	
	private LocalDateTime dataLancamento;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	*/

	public TipoItemProntuario getTipoitemprontuario() {
		return tipoitemprontuario;
	}

	public void setTipoitemprontuario(TipoItemProntuario tipoitemprontuario) {
		this.tipoitemprontuario = tipoitemprontuario;
	}

	public String getValor() {
		return Valor;
	}

	public void setValor(String valor) {
		Valor = valor;
	}

	public LocalDateTime getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDateTime dataLancamento) {
		this.dataLancamento = dataLancamento;
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
		ItemProntuario other = (ItemProntuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
}

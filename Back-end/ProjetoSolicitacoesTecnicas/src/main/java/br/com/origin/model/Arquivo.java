package br.com.origin.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name="arquivos")
public class Arquivo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idarquivo;
	
	@Column (nullable = false)
	private Long idchamado;
	
	@Column (nullable = false)
	private String nomearquivo;
	
	@Column (nullable = false)
	private String tipoarquivo;
	
	@Column (nullable = false)
	private String urlarquivo;
	
	@Column (nullable = false)
	private Long tamanho;

	public Arquivo() {}

	public Long getIdarquivo() {
		return idarquivo;
	}

	public void setIdarquivo(Long idarquivo) {
		this.idarquivo = idarquivo;
	}

	public Long getIdchamado() {
		return idchamado;
	}

	public void setIdchamado(Long idchamado) {
		this.idchamado = idchamado;
	}

	public String getNomearquivo() {
		return nomearquivo;
	}

	public void setNomearquivo(String nomearquivo) {
		this.nomearquivo = nomearquivo;
	}

	public String getTipoarquivo() {
		return tipoarquivo;
	}

	public void setTipoarquivo(String tipoarquivo) {
		this.tipoarquivo = tipoarquivo;
	}

	public String getUrlarquivo() {
		return urlarquivo;
	}

	public void setUrlarquivo(String urlarquivo) {
		this.urlarquivo = urlarquivo;
	}

	public Long getTamanho() {
		return tamanho;
	}

	public void setTamanho(Long tamanho) {
		this.tamanho = tamanho;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idarquivo == null) ? 0 : idarquivo.hashCode());
		result = prime * result + ((idchamado == null) ? 0 : idchamado.hashCode());
		result = prime * result + ((nomearquivo == null) ? 0 : nomearquivo.hashCode());
		result = prime * result + ((tamanho == null) ? 0 : tamanho.hashCode());
		result = prime * result + ((tipoarquivo == null) ? 0 : tipoarquivo.hashCode());
		result = prime * result + ((urlarquivo == null) ? 0 : urlarquivo.hashCode());
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
		Arquivo other = (Arquivo) obj;
		if (idarquivo == null) {
			if (other.idarquivo != null)
				return false;
		} else if (!idarquivo.equals(other.idarquivo))
			return false;
		if (idchamado == null) {
			if (other.idchamado != null)
				return false;
		} else if (!idchamado.equals(other.idchamado))
			return false;
		if (nomearquivo == null) {
			if (other.nomearquivo != null)
				return false;
		} else if (!nomearquivo.equals(other.nomearquivo))
			return false;
		if (tamanho == null) {
			if (other.tamanho != null)
				return false;
		} else if (!tamanho.equals(other.tamanho))
			return false;
		if (tipoarquivo == null) {
			if (other.tipoarquivo != null)
				return false;
		} else if (!tipoarquivo.equals(other.tipoarquivo))
			return false;
		if (urlarquivo == null) {
			if (other.urlarquivo != null)
				return false;
		} else if (!urlarquivo.equals(other.urlarquivo))
			return false;
		return true;
	}
	
}

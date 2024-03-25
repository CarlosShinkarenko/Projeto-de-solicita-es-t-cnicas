package br.com.origin.model.vo;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

public class ArquivoVo extends RepresentationModel<ArquivoVo> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idarquivo;
	private Long idchamado;
	private String nomearquivo;
	private Long tamanho;
	private String tipoarquivo;
	private String urlarquivo;
	
	public ArquivoVo() {}

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

	public Long getTamanho() {
		return tamanho;
	}

	public void setTamanho(Long tamanho) {
		this.tamanho = tamanho;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArquivoVo other = (ArquivoVo) obj;
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

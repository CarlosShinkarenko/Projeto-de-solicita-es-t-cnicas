package br.com.origin.model.vo;

import java.io.Serializable;

public class UploadFileResponseVo implements Serializable{

	private static final long serialVersionUID = 1L;

	//private Long idarquivo;
	//private Long idchamado;
	private String nomearquivo;
	private String tipoarquivo;
	private String urlarquivo;
	private Long tamanho;
	
	public UploadFileResponseVo() {}

	/*public UploadFileResponseVo(Long idarquivo, Long idchamado, String nomearquivo, String tipoarquivo,
		String urlarquivo, String tamanho) {
		this.idarquivo = idarquivo;
		this.idchamado = idchamado;
		this.nomearquivo = nomearquivo;
		this.tipoarquivo = tipoarquivo;
		this.urlarquivo = urlarquivo;
		this.tamanho = tamanho;
	}*/
	
	public UploadFileResponseVo(String nomearquivo, String tipoarquivo,
			String urlarquivo, Long tamanho) {
		this.nomearquivo = nomearquivo;
		this.tipoarquivo = tipoarquivo;
		this.urlarquivo = urlarquivo;
		this.tamanho = tamanho;
	}

	/*public Long getIdarquivo() {
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
	}*/

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
	
}

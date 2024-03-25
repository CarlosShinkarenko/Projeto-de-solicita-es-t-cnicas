package br.com.origin.model;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "permissao")
public class Permissao implements GrantedAuthority, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idpermissao;
	
	@Column
	private String nomepermissao;
	
	public Permissao() {}

	@Override
	public String getAuthority() {
		
		return this.nomepermissao;

	}
	
	public Long getIdpermissao() {
		return idpermissao;
	}

	public void setIdpermissao(Long idpermissao) {
		this.idpermissao = idpermissao;
	}

	public String getNomepermissao() {
		return nomepermissao;
	}

	public void setNomepermissao(String nomepermissao) {
		this.nomepermissao = nomepermissao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idpermissao == null) ? 0 : idpermissao.hashCode());
		result = prime * result + ((nomepermissao == null) ? 0 : nomepermissao.hashCode());
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
		Permissao other = (Permissao) obj;
		if (idpermissao == null) {
			if (other.idpermissao != null)
				return false;
		} else if (!idpermissao.equals(other.idpermissao))
			return false;
		if (nomepermissao == null) {
			if (other.nomepermissao != null)
				return false;
		} else if (!nomepermissao.equals(other.nomepermissao))
			return false;
		return true;
	}

}

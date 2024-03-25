package br.com.origin.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name= "usuario", schema = "public")
public class Usuario implements UserDetails, Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idusuario;
	
	@Column (name= "nome")
	private String nome;
	
	@Column (name= "email")
	private String email;
	
	@Column (name= "senha")
	private String senha;
	
	@Column (name= "account_non_expired")
	private Boolean account_non_expired;
	
	@Column (name= "account_non_locked")
	private Boolean account_non_locked;
	
	@Column (name= "credential_non_expired")
	private Boolean credential_non_expired;
	
	@Column (name= "enabled")
	private Boolean enabled;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="permissao_usuario", joinColumns = {@JoinColumn (name = "idusuario")},
			inverseJoinColumns = {@JoinColumn (name= "idpermissao")}
	)

	private List<Permissao> permissoes;
	
	public List<String> getRoles(){
		List<String> roles = new ArrayList<>();
		for(Permissao permissao: permissoes) {
			roles.add(permissao.getNomepermissao());
		}
		return roles;
	}

	public Usuario() {}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return this.permissoes;
	}

	@Override
	public String getPassword() {

		return this.senha;
	
	}

	@Override
	public String getUsername() {

		return this.email;
	
	}

	@Override
	public boolean isAccountNonExpired() {

		return this.account_non_expired;
	
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return this.account_non_locked;

	}

	@Override
	public boolean isCredentialsNonExpired() {

		return this.credential_non_expired;
	
	}

	@Override
	public boolean isEnabled() {

		return this.enabled;
	
	}

	public Long getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Long idusuario) {
		this.idusuario = idusuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getAccount_non_expired() {
		return account_non_expired;
	}

	public void setAccount_non_expired(Boolean account_non_expired) {
		this.account_non_expired = account_non_expired;
	}

	public Boolean getAccount_non_locked() {
		return account_non_locked;
	}

	public void setAccount_non_locked(Boolean account_non_locked) {
		this.account_non_locked = account_non_locked;
	}

	public Boolean getCredential_non_expired() {
		return credential_non_expired;
	}

	public void setCredential_non_expired(Boolean credential_non_expired) {
		this.credential_non_expired = credential_non_expired;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account_non_expired == null) ? 0 : account_non_expired.hashCode());
		result = prime * result + ((account_non_locked == null) ? 0 : account_non_locked.hashCode());
		result = prime * result + ((credential_non_expired == null) ? 0 : credential_non_expired.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result + ((idusuario == null) ? 0 : idusuario.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((permissoes == null) ? 0 : permissoes.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		Usuario other = (Usuario) obj;
		if (account_non_expired == null) {
			if (other.account_non_expired != null)
				return false;
		} else if (!account_non_expired.equals(other.account_non_expired))
			return false;
		if (account_non_locked == null) {
			if (other.account_non_locked != null)
				return false;
		} else if (!account_non_locked.equals(other.account_non_locked))
			return false;
		if (credential_non_expired == null) {
			if (other.credential_non_expired != null)
				return false;
		} else if (!credential_non_expired.equals(other.credential_non_expired))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled == null) {
			if (other.enabled != null)
				return false;
		} else if (!enabled.equals(other.enabled))
			return false;
		if (idusuario == null) {
			if (other.idusuario != null)
				return false;
		} else if (!idusuario.equals(other.idusuario))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (permissoes == null) {
			if (other.permissoes != null)
				return false;
		} else if (!permissoes.equals(other.permissoes))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

}

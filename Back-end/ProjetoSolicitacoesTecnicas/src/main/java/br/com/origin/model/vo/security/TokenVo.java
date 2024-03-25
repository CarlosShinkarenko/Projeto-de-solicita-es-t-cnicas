package br.com.origin.model.vo.security;

import java.io.Serializable;
import java.util.Date;

public class TokenVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idusuario;
	private String nome;
	private String email;
	private String roles;
	private Boolean authentidated;
	private Date created;
	private Date expiration;
	private String accessToken;
	private String refreshToken;
	
	public TokenVo () {}

	public TokenVo(Long idusuario, String nome, String email, String roles, Boolean authentidated, Date created,
			Date expiration, String accessToken, String refreshToken) {
		this.idusuario = idusuario;
		this.nome = nome;
		this.email = email;
		this.roles = roles;
		this.authentidated = authentidated;
		this.created = created;
		this.expiration = expiration;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
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

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Boolean getAuthentidated() {
		return authentidated;
	}

	public void setAuthentidated(Boolean authentidated) {
		this.authentidated = authentidated;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accessToken == null) ? 0 : accessToken.hashCode());
		result = prime * result + ((authentidated == null) ? 0 : authentidated.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((expiration == null) ? 0 : expiration.hashCode());
		result = prime * result + ((idusuario == null) ? 0 : idusuario.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((refreshToken == null) ? 0 : refreshToken.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
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
		TokenVo other = (TokenVo) obj;
		if (accessToken == null) {
			if (other.accessToken != null)
				return false;
		} else if (!accessToken.equals(other.accessToken))
			return false;
		if (authentidated == null) {
			if (other.authentidated != null)
				return false;
		} else if (!authentidated.equals(other.authentidated))
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (expiration == null) {
			if (other.expiration != null)
				return false;
		} else if (!expiration.equals(other.expiration))
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
		if (refreshToken == null) {
			if (other.refreshToken != null)
				return false;
		} else if (!refreshToken.equals(other.refreshToken))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		return true;
	}
	
	/*public TokenVo(Long idusuario, String nome, String email, Boolean authentidated, Date created, Date expiration,
	String accessToken, String refreshToken) {
		this.idusuario = idusuario;
		this.nome = nome;
		this.email = email;
		this.authentidated = authentidated;
		this.created = created;
		this.expiration = expiration;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
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
		
		public Boolean getAuthentidated() {
		return authentidated;
		}
		
		public void setAuthentidated(Boolean authentidated) {
		this.authentidated = authentidated;
		}
		
		public Date getCreated() {
		return created;
		}
		
		public void setCreated(Date created) {
		this.created = created;
		}
		
		public Date getExpiration() {
		return expiration;
		}
		
		public void setExpiration(Date expiration) {
		this.expiration = expiration;
		}
		
		public String getAccessToken() {
		return accessToken;
		}
		
		public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
		}
		
		public String getRefreshToken() {
		return refreshToken;
		}
		
		public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
		}
		
		@Override
		public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accessToken == null) ? 0 : accessToken.hashCode());
		result = prime * result + ((authentidated == null) ? 0 : authentidated.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((expiration == null) ? 0 : expiration.hashCode());
		result = prime * result + ((idusuario == null) ? 0 : idusuario.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((refreshToken == null) ? 0 : refreshToken.hashCode());
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
		TokenVo other = (TokenVo) obj;
		if (accessToken == null) {
			if (other.accessToken != null)
				return false;
		} else if (!accessToken.equals(other.accessToken))
			return false;
		if (authentidated == null) {
			if (other.authentidated != null)
				return false;
		} else if (!authentidated.equals(other.authentidated))
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (expiration == null) {
			if (other.expiration != null)
				return false;
		} else if (!expiration.equals(other.expiration))
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
		if (refreshToken == null) {
			if (other.refreshToken != null)
				return false;
		} else if (!refreshToken.equals(other.refreshToken))
			return false;
		return true;
		}*/

}

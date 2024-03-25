package br.com.origin.security.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.origin.exceptions.InvalidJwtAuthenticationException;
import br.com.origin.model.vo.security.TokenVo;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtTokenProvider {

	@Value("${security.jwt.token.secret-key:secret}")
	private String secretKey = "secret";
	
	@Value("${security.jwt.token.expire-length:3600000}")
	private long validityInMilliseconds = 3600000;

	@Autowired
	private UserDetailsService userDetailsService;
	
	Algorithm algorithm = null;
	
	@PostConstruct
	protected void init() {
		
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
		algorithm = Algorithm.HMAC256(secretKey.getBytes());
		
	}
	
	public TokenVo createAccessToken(Long id,String nome, String email, List<String> roles) {
		
		Date now = new Date();
		
		Date validity = new Date(now.getTime() + validityInMilliseconds);
		
		var accessToken = getAccessToken(email, roles, now, validity);
		
		var refreshToken = getRefreshToken(email, roles, now);
		
		var role = roles.get(0);
		
		return new TokenVo(id, nome ,email, role, true, now, validity, accessToken, refreshToken);
		
	}
	
	public TokenVo refreshToken(Long id, String nome, String refreshToken) {
		
		if (refreshToken.contains("")) refreshToken = refreshToken.substring("Bearer ".length());
		
		JWTVerifier verifier = JWT.require(algorithm).build();
		
		DecodedJWT decodedJWT = verifier.verify(refreshToken);
		
		String email = decodedJWT.getSubject();
		
		List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
		
		return createAccessToken(id, nome, email, roles);
		
	}

	private String getAccessToken(String email, List<String> roles, Date now, Date validity) {

		String issuerUrl = ServletUriComponentsBuilder
				.fromCurrentContextPath().build().toUriString();
		
		return JWT.create()
				.withClaim("roles", roles)
				.withIssuedAt(now)
				.withExpiresAt(validity)
				.withSubject(email)
				.withIssuer(issuerUrl)
				.sign(algorithm)
				.strip();
	
	}
	
	private String getRefreshToken(String email, List<String> roles, Date now) {
		
		Date validityRefreshToken = new Date(now.getTime() + (validityInMilliseconds * 3 ));
		
		return JWT.create()
			.withClaim("roles", roles)
			.withIssuedAt(now)
			.withExpiresAt(validityRefreshToken)
			.withSubject(email)
			.sign(algorithm)
			.strip();
	
	}
	
	public Authentication getAuthentication(String token) {
		
		DecodedJWT decodedJWT = decodedToken(token);
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(decodedJWT.getSubject());
		
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
		
	}

	private DecodedJWT decodedToken(String token) {
		
		Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
		
		JWTVerifier verifier = JWT.require(alg).build();
		
		DecodedJWT decodedJWT = verifier.verify(token);
		
		return decodedJWT;
	
	}
	
	public String resolveToken(HttpServletRequest req) {
		
		String bearerToken = req.getHeader("Authorization");
		
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring("Bearer ".length());
		}
		
		return null;
		
	}
	
	public boolean validateToken(String token) {
		
		DecodedJWT decodedJWT = decodedToken (token);
		
		try {
			if(decodedJWT.getExpiresAt().before(new Date())) {
				return false;
			}
			return true;
		} catch(Exception e) {
			throw new InvalidJwtAuthenticationException("Expired or invalid JWT Token!");
		}
		
	}
	
}
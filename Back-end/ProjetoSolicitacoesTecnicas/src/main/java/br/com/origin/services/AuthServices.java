package br.com.origin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.origin.model.vo.security.AccountCredentialsVo;
import br.com.origin.model.vo.security.TokenVo;
import br.com.origin.repositories.UserRepository;
import br.com.origin.security.jwt.JwtTokenProvider;

@Service
public class AuthServices {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private UserRepository repository;
	
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity signIn(AccountCredentialsVo data) {
		try {
			
			var username = data.getEmail();
			
			var password = data.getSenha();
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
			System.out.println("email: "+username);
			System.out.println("password: "+password);
						
			var user = repository.findByUsername(username);
			
			var tokenResponse = new TokenVo();
			
			if( user!= null) {
			
				tokenResponse = tokenProvider.createAccessToken(user.getIdusuario(), user.getNome() ,username, user.getRoles());
			
			} else {
			
				throw new UsernameNotFoundException("Email " + username + " Não cadastrado");
			
			}
			
			return ResponseEntity.ok(tokenResponse);
		} catch (Exception e) {
			
			throw new BadCredentialsException("Invalid email/password supplied!");
			
		}
	}
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity refreshToken(String email, String refreshToken) {
	
		var user = repository.findByUsername(email);
		
		var tokenResponse = new TokenVo();
		
		if(user != null ){
			
			tokenResponse = tokenProvider.refreshToken(user.getIdusuario(), user.getNome(),refreshToken);
			
		} else {
			
			throw new UsernameNotFoundException("Email " + email + " Não cadastrado");
			
		}
		
		return ResponseEntity.ok(tokenResponse);
	
	}
	
	
	
}

package br.com.origin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.origin.model.vo.RefreshTokenVo;
import br.com.origin.model.vo.security.AccountCredentialsVo;
import br.com.origin.services.AuthServices;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthServices authServices;
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/signin", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity signIn (@RequestBody AccountCredentialsVo data) {
		
		if (CheckIfParamsIsNull(data)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
		
		var token = authServices.signIn(data);
		
		if(token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
		
		return token;
		
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping(value="/refresh", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity refreshToken(@RequestBody RefreshTokenVo data) {
		
		if (checkIfParamsNull(data.getEmail(), data.getRefreshToken())) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
		
		var token = authServices.refreshToken(data.getEmail(), data.getRefreshToken());
		
		if(token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
		
		return token;
		
	}


	private boolean CheckIfParamsIsNull(AccountCredentialsVo data) {

		return data == null || data.getEmail() == null || data.getEmail().isBlank() || data.getSenha() == null || data.getSenha().isBlank();
	
	}
	
	private boolean checkIfParamsNull(String email, String refreshToken) {

		return refreshToken == null || refreshToken.isBlank() || email == null || email.isBlank();
	
	}
	
}

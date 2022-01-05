package br.com.uniciv.tarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uniciv.tarefas.controller.request.LoginRequest;
import br.com.uniciv.tarefas.controller.response.JwtResponse;
import br.com.uniciv.tarefas.services.UsuarioService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UsuarioService service;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest login) {
		JwtResponse jwtResponse = service.autenticaUsuario(login.getNome(), login.getSenha());
		
		return ResponseEntity.ok(jwtResponse);
	}
	
}

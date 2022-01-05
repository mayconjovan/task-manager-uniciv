package br.com.uniciv.tarefas.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.uniciv.tarefas.controller.response.JwtResponse;
import br.com.uniciv.tarefas.model.Role;
import br.com.uniciv.tarefas.model.Usuario;
import br.com.uniciv.tarefas.repository.RoleRepository;
import br.com.uniciv.tarefas.repository.UsuarioRepository;
import br.com.uniciv.tarefas.security.JwtUtils;
import br.com.uniciv.tarefas.security.UserDetailsImpl;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private RoleRepository roleRepo;

	public Usuario getUsuarioPorId(Integer id) {
		return repo.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}

	public List<Usuario> getTodosUsuarios() {
		return repo.findAll();
	}

	public Usuario salvar(Usuario usuario) {
		Set<Role> roles = getRoles(usuario);
		usuario.setRoles(roles);
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		return repo.save(usuario);
	}

	public Usuario atualizar(Integer id, Usuario usuario) {
		if (!repo.existsById(id))
			throw new EntityNotFoundException();

		usuario.setId(id);

		return salvar(usuario);
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

	private Set<Role> getRoles(Usuario usuario) {
		Set<Role> rolesBanco = usuario.getRoles().stream().map(role -> roleRepo.findByName(role.getName()))
				.collect(Collectors.toSet());

		return rolesBanco;
	}

	public JwtResponse autenticaUsuario(String nome, String senha) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(nome, senha));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles);
	}
}

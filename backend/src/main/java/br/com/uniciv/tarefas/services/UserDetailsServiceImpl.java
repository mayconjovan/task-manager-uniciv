package br.com.uniciv.tarefas.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.uniciv.tarefas.model.Usuario;
import br.com.uniciv.tarefas.repository.UsuarioRepository;
import br.com.uniciv.tarefas.security.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository repo;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repo.findByNome(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));

		return UserDetailsImpl.build(usuario);
	}

}

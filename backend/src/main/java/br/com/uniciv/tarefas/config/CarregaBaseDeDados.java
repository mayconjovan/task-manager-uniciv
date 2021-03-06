package br.com.uniciv.tarefas.config;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.uniciv.tarefas.model.ERole;
import br.com.uniciv.tarefas.model.Role;
import br.com.uniciv.tarefas.model.Tarefa;
import br.com.uniciv.tarefas.model.TarefaCategoria;
import br.com.uniciv.tarefas.model.TarefaStatus;
import br.com.uniciv.tarefas.model.Usuario;
import br.com.uniciv.tarefas.repository.RoleRepository;
import br.com.uniciv.tarefas.repository.TarefaCategoriaRepository;
import br.com.uniciv.tarefas.repository.TarefaRepository;
import br.com.uniciv.tarefas.repository.UsuarioRepository;

@Configuration
@Profile("dev")
public class CarregaBaseDeDados {
	
	@Autowired
	private UsuarioRepository userRepo;
	@Autowired
	private RoleRepository role;
	@Autowired
	private TarefaRepository tarefaRepo;
	@Autowired
	private TarefaCategoriaRepository tarefaCatRepo;
	@Autowired
	private PasswordEncoder encoder;
	
	
	@Bean
	CommandLineRunner executar() {
		return args -> {
			
			Role roleAdmin = new Role(ERole.ROLE_ADMIN);
			roleAdmin = role.save(roleAdmin);
			
			Usuario usuario = new Usuario();
			usuario.setNome("Admin");
			usuario.setSenha(encoder.encode("123456"));
			usuario.setRoles(Set.of(roleAdmin));
			
			userRepo.save(usuario);
			
			TarefaCategoria categoria = new TarefaCategoria();
			categoria.setNome("Estudos");
			
			tarefaCatRepo.save(categoria);
			
			Tarefa tarefa = new Tarefa();
			tarefa.setDescricao("Aprender spring boot");
			tarefa.setDataEntrega(LocalDate.now().plusDays(1));
			tarefa.setStatus(TarefaStatus.ABERTO);
			tarefa.setVisivel(true);
			tarefa.setCategoria(categoria);
			tarefa.setUsuario(usuario);			
		
			
			tarefaRepo.save(tarefa);
	
			
		};
	}
}

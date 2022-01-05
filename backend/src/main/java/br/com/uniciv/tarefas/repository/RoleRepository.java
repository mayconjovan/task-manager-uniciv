package br.com.uniciv.tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.uniciv.tarefas.model.ERole;
import br.com.uniciv.tarefas.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	Role findByName(ERole name);

}

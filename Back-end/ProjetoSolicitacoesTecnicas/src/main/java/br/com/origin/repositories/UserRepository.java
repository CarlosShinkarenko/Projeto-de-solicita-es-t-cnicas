package br.com.origin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.origin.model.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long>{

	@Query("SELECT u from Usuario u where u.email = :email")
	Usuario findByUsername(@Param("email") String email);
	
}

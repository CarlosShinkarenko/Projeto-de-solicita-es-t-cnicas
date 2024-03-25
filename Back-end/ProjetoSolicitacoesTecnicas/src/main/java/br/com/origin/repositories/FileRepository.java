package br.com.origin.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.origin.model.Arquivo;

public interface FileRepository extends JpaRepository<Arquivo, Long> {
	
	@Query("SELECT a from Arquivo a where a.idchamado = :id")
	List <Arquivo> findUrlByCall(@Param("id") Long id, Pageable pageable);

}

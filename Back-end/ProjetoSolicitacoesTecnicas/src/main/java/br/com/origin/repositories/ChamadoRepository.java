package br.com.origin.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.origin.model.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
	
	@Query("SELECT c from Chamado c where c.idusuario = :id")
	List <Chamado> findChamadoByUsuario(@Param("id") Long id, Pageable pageable);
	
	@Query("SELECT c from Chamado c where c.idusuario = :id and c.estado = :estado")
	List <Chamado> findChmdByUsrEst(@Param("id") Long id, @Param("estado") String estado, Pageable pageable);
	
	@Query("SELECT c from Chamado c where c.estado = :estado")
	List <Chamado> findAllByEst(@Param("estado") String estado, Pageable pageable);
	
	@Query("SELECT c from Chamado c")
	List <Chamado> findAll1(Pageable pageable);
	
}

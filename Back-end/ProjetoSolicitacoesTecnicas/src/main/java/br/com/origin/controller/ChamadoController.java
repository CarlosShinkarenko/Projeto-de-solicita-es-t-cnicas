package br.com.origin.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.origin.model.vo.ChamadoVo;
import br.com.origin.services.ChamadoServices;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/chamados")
public class ChamadoController {

	
	@Autowired
	ChamadoServices service;
	
	@GetMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ChamadoVo findById(@PathVariable(value = "id")String id) {
		
		return service.findById(Long.parseLong(id));
		
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<ChamadoVo> findAll(
			@RequestParam(value= "page", defaultValue= "0") Integer page,
			@RequestParam(value= "size", defaultValue = "10") Integer size,
			@RequestParam(value= "direction", defaultValue="asc") String direction){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "assunto"));
		
		return service.findAll(pageable);
		
	}
	
	@GetMapping(value="/usuario/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<ChamadoVo> findAllByUsuario(
			@PathVariable(value = "id")String id,
			@RequestParam(value= "page", defaultValue= "0") Integer page,
			@RequestParam(value= "size", defaultValue = "10") Integer size,
			@RequestParam(value= "direction", defaultValue="asc") String direction
			){
				
			var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
			
			Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "data_chamado"));
		
		return service.findAllByUsuario(Long.parseLong(id), pageable);
		
	}
	
	@GetMapping(value="/usuario/{id}/{estado}", produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<ChamadoVo> findChmdByUsrEst(
			@PathVariable(value="id")Long id, 
			@PathVariable(value="estado")String estado,
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="size", defaultValue="10") Integer size,
			@RequestParam(value="direction", defaultValue="asc") String direction
			){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection,"data_chamado"));
		
		return service.findChmdByUsrEst(id, estado, pageable);
		
	}
	
	@GetMapping(value="/estado/{estado}", produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<ChamadoVo> findAllByEst(
			@PathVariable(value="estado")String estado,
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="size", defaultValue="10") Integer size,
			@RequestParam(value="direction", defaultValue="asc") String direction
			){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection,"data_chamado"));
		
		return service.findAllByEst(estado, pageable);
		
	}
	
	@PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ChamadoVo update (@RequestBody ChamadoVo chamado ) {
		
		var dt = service.findById(chamado.getIdchamado());
		
		chamado.setData_chamado(dt.getData_chamado());
		
		return service.update(chamado);
		
	}
	
	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ChamadoVo create(@RequestBody ChamadoVo chamado) {
		
		chamado.setData_chamado(new Date());
		
		return service.create(chamado);
	
	}
	
	@DeleteMapping(value="/{id}")
	public void delete(@PathVariable(value = "id")Long id) {
		
		service.delete(id);
		
	}
	
	
	
	
	
	
}

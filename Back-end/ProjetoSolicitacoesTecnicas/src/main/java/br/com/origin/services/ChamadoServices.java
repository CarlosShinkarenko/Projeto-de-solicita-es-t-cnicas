package br.com.origin.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Service;

import br.com.origin.controller.ChamadoController;
import br.com.origin.exceptions.RequiredObjectIsNullException;
import br.com.origin.exceptions.ResourceNotFoundException;
import br.com.origin.mapper.DozerMapper;
import br.com.origin.model.Chamado;
import br.com.origin.model.vo.ChamadoVo;
import br.com.origin.repositories.ChamadoRepository;

@Service
public class ChamadoServices {

	@Autowired
	ChamadoRepository repository;
	
	@Autowired
	PagedResourcesAssembler<ChamadoVo> assembler;
	
	public ChamadoVo findById(Long id) {

		 var entity = repository.findById(id)
				 .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		var vo = DozerMapper.parseObject(entity, ChamadoVo.class);

		vo.add(linkTo(methodOn(ChamadoController.class).findById(id.toString())).withSelfRel());
		
		return vo;
		
	}

	public List<ChamadoVo> findAll(Pageable pageable){
		
		return DozerMapper.parseListObjects(repository.findAll1(pageable), ChamadoVo.class);

	}
	
	public List<ChamadoVo> findAllByUsuario(Long id, Pageable pageable){
		
		//System.out.println(DozerMapper.parseListObjects(repository.findChamadoByUsuario(id, pageable), ChamadoVo.class));
		
		return DozerMapper.parseListObjects(repository.findChamadoByUsuario(id, pageable), ChamadoVo.class);
		
	}
	
	public List <ChamadoVo> findChmdByUsrEst(Long id, String estado, Pageable pageable){
		
		return DozerMapper.parseListObjects(repository.findChmdByUsrEst(id, estado, pageable), ChamadoVo.class);
		
	}
	
	public List <ChamadoVo> findAllByEst(String estado, Pageable pageable){
		
		return DozerMapper.parseListObjects(repository.findAllByEst(estado, pageable), ChamadoVo.class);
		
	}
	
	public ChamadoVo update(ChamadoVo chamado) {
		
		if(chamado == null) throw new RequiredObjectIsNullException();
		
		Chamado entity = repository.findById(chamado.getIdchamado())
			.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setIdusuario(chamado.getIdusuario());
		entity.setAssunto(chamado.getAssunto());
		entity.setTipo(chamado.getTipo());
		entity.setDescricao(chamado.getDescricao());
		entity.setData_chamado(chamado.getData_chamado());
		entity.setObservacoes(chamado.getObservacoes());
		entity.setEstado(chamado.getEstado());
		entity.setComentarios(chamado.getComentarios());
		
		var vo = DozerMapper.parseObject(repository.save(entity), ChamadoVo.class);
		
		vo.add(linkTo(methodOn(ChamadoController.class).findById(vo.getIdchamado().toString())).withSelfRel());
		
		return vo;
		
	}
	
	public ChamadoVo create(ChamadoVo chamado) {
		
		if(chamado == null) throw new RequiredObjectIsNullException();
		
		var entity = DozerMapper.parseObject(chamado, Chamado.class);
		
		var vo = DozerMapper.parseObject(repository.save(entity), ChamadoVo.class);
		
		vo.add(linkTo(methodOn(ChamadoController.class).findById(vo.getIdchamado().toString())).withSelfRel());
		
		return vo;
		
	}
	
	public void delete(Long id) {
		
		Chamado entity = (repository.findById(id))
			.orElseThrow(() -> new ResourceNotFoundException("No records for this ID"));
		
		repository.delete(entity);
		
	}
	
	
}

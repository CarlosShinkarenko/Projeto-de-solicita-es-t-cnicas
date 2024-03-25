package br.com.origin.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.origin.config.FileStorageConfig;
import br.com.origin.exceptions.FileStorageException;
import br.com.origin.exceptions.MyFileNotFoundException;
import br.com.origin.mapper.DozerMapper;
import br.com.origin.model.Arquivo;
import br.com.origin.model.vo.ArquivoVo;
import br.com.origin.repositories.FileRepository;

@Service
public class FileStorageService {

	@Autowired
	FileRepository repository;
	
	private final Path fileStorageLocation;
	
	public FileStorageService(FileStorageConfig fileStorageConfig) {
		
		Path path = Paths.get(fileStorageConfig.getUploadDir())
				.toAbsolutePath().normalize();
		
		this.fileStorageLocation = path;
		
		try {
			
			Files.createDirectories(this.fileStorageLocation);
		
		}catch (Exception e){
			
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored!", e);
		
		}
		
	}
	
	public String storeFile(MultipartFile file, Long id) {
		
		String filename = StringUtils.cleanPath(id.toString()+file.getOriginalFilename());
		
		try {
			
			if(filename.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " +filename);
			}
			
			Path targetLocation = this.fileStorageLocation.resolve(filename);
			
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
			return filename;
			
		} catch (Exception e) {

			throw new FileStorageException("Could not store file "+filename+". Please try again! ", e);
			
		}
		
	}
	
	public Resource loadFileResource(String filename) {
		
		try {
			
			Path filePath = this.fileStorageLocation.resolve(filename).normalize();
			
			Resource resource = new UrlResource(filePath.toUri());
			
			if(resource.exists()) return resource;
			else throw new MyFileNotFoundException("File not found");
			
		}catch(Exception e) {
			
			throw new MyFileNotFoundException("File not found "+filename, e);
			
		}
		
	}
	
	public void saveFileData(String nomearquivo, String urlarquivo, String tipoarquivo, Long tamanho, Long idchamado) {
		
		Arquivo entity = new Arquivo();
		
		entity.setIdchamado(idchamado);
		entity.setNomearquivo(nomearquivo);
		entity.setTipoarquivo(tipoarquivo);
		entity.setUrlarquivo(urlarquivo);
		entity.setTamanho(tamanho);
		
		repository.save(entity);
		
	}
	
	public List<ArquivoVo> findUrlByCall(Long id, Pageable pageable){
		
		return  DozerMapper.parseListObjects(repository.findUrlByCall(id, pageable), ArquivoVo.class);
		
	}
	
}

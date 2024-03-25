package br.com.origin.controller;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.origin.model.vo.ArquivoVo;
import br.com.origin.model.vo.UploadFileResponseVo;
import br.com.origin.services.FileStorageService;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/file")
public class FileController {

	Logger logger = Logger.getLogger(FileController.class.getName());
	
	@Autowired
	private FileStorageService service;
	
	@PostMapping("/uploadFile/{id}")
	public UploadFileResponseVo uploadFile(@RequestParam("file") MultipartFile file, @PathVariable(value="id") Long id) {
		
		var filename = service.storeFile(file, id);
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/file/downloadFile/")
				.path(filename)
				.toUriString();		
		
		service.saveFileData(filename, fileDownloadUri, file.getContentType(), file.getSize(), id);
		
		return
			new UploadFileResponseVo(filename, fileDownloadUri, file.getContentType(), file.getSize());
		
	}
	
	@PostMapping("/uploadMultipleFiles/{id}")
	public List<UploadFileResponseVo> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, @PathVariable(value="id") Long id){
		
		return Arrays.asList(files)
				.stream()
				.map(file -> uploadFile(file, id))
				.collect(Collectors.toList());
		
	}
	
	@GetMapping("/downloadFile/{filename:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String filename, HttpServletRequest request){
	
		Resource resource = service.loadFileResource(filename);
		
		String contentType = "";
		
		try {
			
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
			
		} catch (Exception e) {

			logger.info("Could not determine file type!");
		
		}
		
		if(contentType.isBlank()) contentType = "application/octet-stream";
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(
					HttpHeaders.CONTENT_DISPOSITION, 
					"attachment; filename=\""+ resource.getFilename()+"\"")
				.body(resource);

	}
	
	@GetMapping("/imageSrc/{id}")
	public List<ArquivoVo> findUrlByCall(
			@PathVariable(value="id")Long id,
			@RequestParam(value = "page", defaultValue = "0")Integer page,
			@RequestParam(value = "size", defaultValue = "3")Integer size,
			@RequestParam(value = "direction", defaultValue = "asc") String direction
			){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "urlarquivo"));
		
		return service.findUrlByCall(id, pageable);
		
	}
	
}

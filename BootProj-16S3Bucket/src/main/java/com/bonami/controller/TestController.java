package com.bonami.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bonami.service.TestService;

@RestController
@RequestMapping("/api")
public class TestController {

	@Autowired
	private TestService service;
	
	@PostMapping("/upload")
	public String uploadFile(@RequestParam MultipartFile file) {
		return service.uploadFile(file);
		
	} 
	@GetMapping("/download/{fileName}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
		byte[] data = service.downloadFileFromS3(fileName);
		ByteArrayResource resource=new ByteArrayResource(data);
		
		return ResponseEntity.ok()
				.contentLength(data.length)
				.header("content-type", "application/octet-stream")
				.header("content-disposition", "attachment;filename=\""+fileName+"\"")
				.body(resource);
		
	}
	@GetMapping("/delete/{fileName}")
	public String uploadFile(@PathVariable String fileName) {
		return service.deleteFileFromS3(fileName);
		
		
	}
}

package com.codeoncewidakash.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codeoncewidakash.entity.FileStoreEntity;
import com.codeoncewidakash.exception.FileNotExistException;
import com.codeoncewidakash.service.IFileStoreService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/v1/file/store")
@Log4j2
public class FileStoreController {
	
	@Autowired
	private IFileStoreService fileStoreService;
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam(name = "file") MultipartFile multipartFile) throws IOException{
		ResponseEntity<String> response;
		try {
			String message = fileStoreService.uploadFile(multipartFile);
			response = new ResponseEntity<>(message, HttpStatus.CREATED);
		} catch (IOException ioe) {
			log.error("IOException: {}", ioe.getMessage());
			throw ioe;
		} catch (Exception e) {
			log.error("Exception: {}", e.getMessage());
			throw e;
		}
		return response;
	}
	
	@GetMapping("/download/{id}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable("id") Long id){
		FileStoreEntity fileStoreEntity;
		try {
			 fileStoreEntity = fileStoreService.downloadFle(id);
			 	return ResponseEntity.ok()
					.contentType(MediaType.parseMediaType(fileStoreEntity.getFileType()))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\""+fileStoreEntity.getFileName()+"\"")
					.body(fileStoreEntity.getFileData());
		} catch (FileNotExistException fne) {
			throw fne;
		}
	}
}

package com.codeoncewidakash.service.impl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.codeoncewidakash.entity.FileStoreEntity;
import com.codeoncewidakash.exception.FileNotExistException;
import com.codeoncewidakash.repository.FileStoreRepository;
import com.codeoncewidakash.service.IFileStoreService;
import com.codeoncewidakash.utility.FileStoreUtility;

@Service
public class FileStoreServiceImpl implements IFileStoreService {

	@Autowired
	private FileStoreRepository fileStoreRepository;
	
	@Override
	public String uploadFile(MultipartFile multipartFile) throws IOException {
		FileStoreEntity fileStoreEntity = new FileStoreEntity();
		fileStoreEntity.setFileName(multipartFile.getOriginalFilename());
		fileStoreEntity.setFileType(multipartFile.getContentType());
		fileStoreEntity.setFileData(multipartFile.getBytes());
		fileStoreEntity.setFileSize(multipartFile.getSize());
		fileStoreEntity.setFileJsonReqFormat(FileStoreUtility.convertMultipartToJsonObject(multipartFile).toCharArray());
		
		//store entity object into DB
		FileStoreEntity savedFile = fileStoreRepository.save(fileStoreEntity);
		return new StringBuilder(savedFile.getFileName()).append(" file is uploaded successfully with the id: ").append(savedFile.getId()).toString();
	}

	@Override
	public FileStoreEntity downloadFle(Long id) {
		  return fileStoreRepository.findById(id).orElseThrow(() -> new FileNotExistException("File not exist with id: "+id));
	}
}

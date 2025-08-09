package com.codeoncewidakash.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.codeoncewidakash.entity.FileStoreEntity;

public interface IFileStoreService {
	public String uploadFile(MultipartFile multipartFile) throws IOException;
	public FileStoreEntity downloadFle(Long id);
}

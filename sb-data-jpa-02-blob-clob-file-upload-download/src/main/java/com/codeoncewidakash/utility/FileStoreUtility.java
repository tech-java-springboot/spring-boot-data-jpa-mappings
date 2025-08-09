package com.codeoncewidakash.utility;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FileStoreUtility {
	public static String convertMultipartToJsonObject(MultipartFile multipartFile) throws IOException {
		Map<String, Object> json = new HashMap<>();
		json.put("fileName", multipartFile.getOriginalFilename());
		json.put("fileType", multipartFile.getContentType());
		json.put("fileSize", multipartFile.getSize());
		json.put("base64Content", Base64.getEncoder().encodeToString(multipartFile.getBytes()));
		
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(json);
	}
}

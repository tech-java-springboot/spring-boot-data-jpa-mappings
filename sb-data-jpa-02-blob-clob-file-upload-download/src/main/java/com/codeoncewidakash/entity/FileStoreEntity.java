package com.codeoncewidakash.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "FILE_STORE_TAB")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class FileStoreEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Lob
	@Column(name = "file_data", columnDefinition = "BLOB")
	private byte[] fileData; //for binary files (PDF, Image, Text files and etc)
	
	@Column(name = "file_type")
	private String fileType;
	
	@Column(name = "file_name")
	private String fileName;

	@Column(name = "file_size")
	private Long fileSize;
	
	@Lob
	@Column(name = "file_req_format", columnDefinition = "LONGTEXT")
	private char[] fileJsonReqFormat; //for large text data
}

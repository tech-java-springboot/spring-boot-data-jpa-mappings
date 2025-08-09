package com.codeoncewidakash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeoncewidakash.entity.FileStoreEntity;

public interface FileStoreRepository extends JpaRepository<FileStoreEntity, Long> {

}

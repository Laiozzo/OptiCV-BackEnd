package com.generation.opticvbeckend.model.repositories;

import com.generation.opticvbeckend.model.entities.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadedFileRepository extends JpaRepository<UploadedFile, Long> {
}

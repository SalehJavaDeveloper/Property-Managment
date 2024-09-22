package com.example.property.repository.property;

import com.example.property.entity.property.MediaFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MediaFileRepository extends JpaRepository<MediaFileEntity, UUID> {
    Boolean existsByFilePath(String filePath);
}

package ru.itis.taskmanager.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itis.taskmanager.model.FileInfo;

import java.util.Optional;
import java.util.UUID;

public interface FileInfoRepository extends CrudRepository<FileInfo, UUID> {

    Optional<FileInfo> findByStorageFileName(String storageFileName);
}

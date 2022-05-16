package ru.itis.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.taskmanager.exception.ActivityNotFoundException;
import ru.itis.taskmanager.exception.CommentNotFoundException;
import ru.itis.taskmanager.exception.FileNotFoundException;
import ru.itis.taskmanager.exception.UserNotFoundException;
import ru.itis.taskmanager.model.Activity;
import ru.itis.taskmanager.model.Comment;
import ru.itis.taskmanager.model.FileInfo;
import ru.itis.taskmanager.model.User;
import ru.itis.taskmanager.repository.ActivityRepository;
import ru.itis.taskmanager.repository.CommentRepository;
import ru.itis.taskmanager.repository.FileInfoRepository;
import ru.itis.taskmanager.repository.UserRepository;
import ru.itis.taskmanager.service.FileService;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileInfoRepository fileInfoRepository;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Value("${file.storage.path}")
    private String storagePath;

    @Override
    public void upload(MultipartFile[] files, String commentId, String username) {

        if (files != null) {
            Comment comment = commentRepository.findById(UUID.fromString(commentId))
                    .orElseThrow(CommentNotFoundException::new);
            User user = userRepository.findUserByUserName(username).orElseThrow(UserNotFoundException::new);
            for (MultipartFile file : files) {
                if (file.getSize()!=0) {
                    String extension = file.getOriginalFilename().substring(
                            file.getOriginalFilename().lastIndexOf("."));
                    String storageFileName = UUID.randomUUID() + extension;

                    FileInfo fileInfo = FileInfo.builder()
                            .storageFileName(storageFileName)
                            .contentType(file.getContentType())
                            .originalFileName(file.getOriginalFilename())
                            .size(file.getSize())
                            .comment(comment)
                            .uploadBy(user)
                            .build();
                    try {
                        Files.copy(file.getInputStream(), Paths.get(storagePath, fileInfo.getStorageFileName()));
                        fileInfoRepository.save(fileInfo);
                    } catch (IOException e) {
                        throw new IllegalArgumentException(e);
                    }
                }
            }
        }
    }

    @Override
    public void addFileToResponse(String fileName, HttpServletResponse response) {
        FileInfo file = fileInfoRepository.findByStorageFileName(fileName)
                .orElseThrow(FileNotFoundException::new);
        response.setContentLength(file.getSize().intValue());
        response.setContentType(file.getContentType());
        response.setHeader("Content-Disposition", "filename=\"" + file.getOriginalFileName() + "\"");
        try {
            IOUtils.copy(new FileInputStream(storagePath + "/" + file.getStorageFileName()), response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

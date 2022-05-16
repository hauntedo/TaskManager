package ru.itis.taskmanager.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface FileService {
    void upload(MultipartFile[] file, String taskId, String username);

    void addFileToResponse(String fileName, HttpServletResponse response);
}

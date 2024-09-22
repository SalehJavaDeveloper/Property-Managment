package com.example.property.service.property;

import com.example.property.dto.property_request.MediaFileRequestDto;
import com.example.property.dto.property_response.MediaFileResponseDto;
import com.example.property.exception.FileAlreadyExistException;
import com.example.property.exception.FileTypeNotValidException;
import com.example.property.exception.MethodArgumentNotValidException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface MediaFileService {
    String create(MediaFileRequestDto requestDto, List<MultipartFile> multipartFile,Long taskId) throws MethodArgumentNotValidException, FileAlreadyExistException, FileTypeNotValidException;

    List<MediaFileResponseDto> getAll();

    MediaFileResponseDto findById(UUID id) throws MethodArgumentNotValidException;

    void delete(UUID id) throws MethodArgumentNotValidException;

    void update(UUID id, MediaFileRequestDto requestDto) throws MethodArgumentNotValidException;
}

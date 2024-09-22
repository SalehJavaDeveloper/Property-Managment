package com.example.property.service.impl.property_impl;

import com.example.property.dto.property_request.MediaFileRequestDto;
import com.example.property.dto.property_response.MediaFileResponseDto;
import com.example.property.entity.property.MediaFileEntity;
import com.example.property.entity.property.TaskEntity;
import com.example.property.exception.FileAlreadyExistException;
import com.example.property.exception.FileTypeNotValidException;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.mapper.property.MediaFileMapper;
import com.example.property.repository.property.MediaFileRepository;
import com.example.property.repository.property.TaskRepository;
import com.example.property.service.property.MediaFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class MediaFileServiceImpl implements MediaFileService {

    @Value("${credentials.mediaFile}")
    private String uploadDir;


    private final MediaFileRepository mediaFileRepository;
    private final TaskRepository taskRepository;
    private final MediaFileMapper mediaFileMapper;

    public MediaFileServiceImpl(MediaFileRepository mediaFileRepository, TaskRepository taskRepository, MediaFileMapper mediaFileMapper) {
        this.mediaFileRepository = mediaFileRepository;
        this.taskRepository = taskRepository;
        this.mediaFileMapper = mediaFileMapper;
    }

    @Override
    public List<MediaFileResponseDto> getAll() {
        List<MediaFileEntity> all = mediaFileRepository.findAll();
        return mediaFileMapper.mapListEntityToListResponseDto(all);
    }

    @Override
    public String create(MediaFileRequestDto requestDto, List<MultipartFile> files, Long taskId) throws MethodArgumentNotValidException, FileAlreadyExistException, FileTypeNotValidException {


        for (MultipartFile file : files ) {

            // Get the filename and build the local file path
            String fileName = file.getOriginalFilename();
            String filePath = uploadDir + File.separator + fileName;

            if (mediaFileRepository.existsByFilePath(filePath)) {
                throw new FileAlreadyExistException("This file already exist on DataBase");
            }

            if (file.isEmpty()) {
                throw new MethodArgumentNotValidException("Please select a file to upload");
            }

            try {
                // Save the file to the specified location
                File dest = new File(filePath);
                file.transferTo(dest);

            } catch (IOException e) {
                e.printStackTrace();
                return "Failed to upload file";
            }

            MediaFileEntity mediaFileEntity = mediaFileMapper.mapRequestDtoToEntity(requestDto);

            mediaFileEntity.setFilePath(filePath);
            TaskEntity taskEntity = taskRepository.findById(taskId).orElseThrow();

            mediaFileEntity.setId(UUID.randomUUID());
            mediaFileEntity.setTaskEntity(taskEntity);


            mediaFileRepository.save(mediaFileEntity);

        }
        return "File uploaded successfully";
    }

    @Override
    public MediaFileResponseDto findById(UUID id) throws MethodArgumentNotValidException {
        MediaFileEntity mediaFileEntity = mediaFileRepository.findById(id).orElseThrow();
        return mediaFileMapper.mapEntityToResponseDto(mediaFileEntity);
    }

    @Override
    public void delete(UUID id) throws MethodArgumentNotValidException {
        MediaFileEntity mediaFileEntity = mediaFileRepository.findById(id).orElseThrow();
        mediaFileRepository.delete(mediaFileEntity);

    }

    @Override
    public void update(UUID id, MediaFileRequestDto requestDto) throws MethodArgumentNotValidException {
        MediaFileEntity mediaFileEntity = mediaFileRepository.findById(id).orElseThrow();
        mediaFileMapper.update(mediaFileEntity, requestDto);

        mediaFileRepository.save(mediaFileEntity);

    }


    // If you need a file format, you can use it
//    private MediaFileType getFile(String filePath) throws FileTypeNotValidException {
//        int i = filePath.lastIndexOf(".");
//        if (i != -1) {
//            String type = filePath.substring(i + 1).toLowerCase();
//            for (MediaFileType mediaType : MediaFileType.values()) {
//                if (mediaType.getExtension().equals(type)) {
//                    System.out.println(mediaType.toString());
//                    return mediaType;
//                }
//            }
//        }
//        throw new FileTypeNotValidException("File type not valid");
//    }

}

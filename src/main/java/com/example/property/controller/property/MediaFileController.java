package com.example.property.controller.property;

import com.example.property.dto.property_request.MediaFileRequestDto;
import com.example.property.dto.property_response.MediaFileResponseDto;
import com.example.property.exception.FileAlreadyExistException;
import com.example.property.exception.FileTypeNotValidException;
import com.example.property.handler.SuccessDetails;
import com.example.property.service.impl.property_impl.MediaFileServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/media-file")
public class MediaFileController {

    private final MediaFileServiceImpl mediaFileService;

    public MediaFileController(MediaFileServiceImpl mediaFileService) {
        this.mediaFileService = mediaFileService;
    }

    @GetMapping
    @Operation(summary = "Get All Media file")
    public ResponseEntity<SuccessDetails<List<MediaFileResponseDto>>> getAll() {
        return ResponseEntity.ok(new SuccessDetails<>(mediaFileService.getAll(), HttpStatus.OK.value(), true));
    }

    @GetMapping("find-by-id{uuid}")
    @Operation(summary = "Get By Id Media file")
    public ResponseEntity<SuccessDetails<MediaFileResponseDto>> findById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(new SuccessDetails<>(mediaFileService.findById(uuid), HttpStatus.OK.value(), true));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(description = "Save operation",
            summary = "This is the summary for save media file data",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized: Invalid Token", responseCode = "403")
            })
    public ResponseEntity<SuccessDetails<String>> create(@RequestParam Long taskId, @ModelAttribute @Valid MediaFileRequestDto requestDto, @Parameter(description = "File to upload") @RequestPart(value = "files",required = true)
     List<MultipartFile> file) throws FileAlreadyExistException, FileTypeNotValidException {
        mediaFileService.create(requestDto, file, taskId);
        return ResponseEntity.ok(new SuccessDetails<>("File save Successfully!", HttpStatus.OK.value(), true));

    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Media")
    public ResponseEntity<SuccessDetails<String>> update(@PathVariable UUID id, MediaFileRequestDto updateRequestDto) {
        mediaFileService.update(id, updateRequestDto);
        return ResponseEntity.ok(new SuccessDetails<>("File update Successfully!", HttpStatus.OK.value(), true));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Media")
    public ResponseEntity<SuccessDetails<String>> delete(@PathVariable UUID id) {
        mediaFileService.delete(id);
        return ResponseEntity.ok(new SuccessDetails<>("File deleted Successfully!", HttpStatus.OK.value(), true));
    }
}

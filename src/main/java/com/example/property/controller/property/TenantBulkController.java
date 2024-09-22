package com.example.property.controller.property;

import com.example.property.handler.SuccessDetails;
import com.example.property.service.bulkInsert.TenantBulkService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/tenant")
public class TenantBulkController {

    private final TenantBulkService tenantBulkService;

    public TenantBulkController(TenantBulkService tenantBulkSerive) {
        this.tenantBulkService = tenantBulkSerive;
    }

    @PostMapping(name = "/bulk", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SuccessDetails<String>> bulkInsert(@Parameter(description = "File to upload") @RequestPart(value = "file")
                                                                 @Schema(type = "string", format = "binary") MultipartFile file) {
        String result = tenantBulkService.readExcelAndWriteToDatabase(file);
        return ResponseEntity.ok(new SuccessDetails<>(result, HttpStatus.OK.value(), true));
    }
}
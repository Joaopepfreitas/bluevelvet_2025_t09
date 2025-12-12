package com.musicstore.bluevelvet.api.controller;

import com.musicstore.bluevelvet.domain.service.CategoryExportService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryExportController {

    private final CategoryExportService exportService;

    public CategoryExportController(CategoryExportService exportService) {
        this.exportService = exportService;
    }

    @PreAuthorize("hasAnyRole('Administrator', 'Editor')")
    @GetMapping("/export")
    public ResponseEntity<ByteArrayResource> exportCategories() {
        byte[] csvBytes = exportService.generateCsv();
        String filename = exportService.generateFilename();

        ByteArrayResource resource = new ByteArrayResource(csvBytes);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .contentType(MediaType.parseMediaType("text/csv"))
                .contentLength(csvBytes.length)
                .body(resource);
    }
}

package com.musicstore.bluevelvet.domain.service;

import com.musicstore.bluevelvet.infrastructure.entity.Category;
import com.musicstore.bluevelvet.infrastructure.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CategoryExportService {

    private final CategoryRepository categoryRepository;

    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    public CategoryExportService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public byte[] generateCsv() {
        List<Category> categories = categoryRepository.findAll();

        StringBuilder sb = new StringBuilder();
        sb.append("id,name\n");

        for (Category category : categories) {
            String name = category.getName();
            if (name == null) name = "";

            // Substitui "--" por 2 espaços
            String formattedName = name.replace("--", "  ");

            sb.append(category.getId())
                    .append(",")
                    .append(formattedName)
                    .append("\n");
        }

        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }

    public String generateFilename() {
        return "categories_" + LocalDateTime.now().format(FORMAT) + ".csv";
    }
}

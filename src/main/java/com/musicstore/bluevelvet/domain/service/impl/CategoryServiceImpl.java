package com.musicstore.bluevelvet.domain.service.impl;

import com.musicstore.bluevelvet.api.request.CategoryRequest;
import com.musicstore.bluevelvet.api.response.CategoryResponse;
import com.musicstore.bluevelvet.domain.exception.CategoryNotFoundException;
import com.musicstore.bluevelvet.domain.service.CategoryService;
import com.musicstore.bluevelvet.infrastructure.entity.Category;
import com.musicstore.bluevelvet.infrastructure.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private String getBasePictureUrl(){
        return "http://localhost:32791/pics";
    }

    private final CategoryRepository repository;

    public CategoryResponse findById(Long id){
        Optional<Category> fetched = repository.findById(id);
        if (fetched.isEmpty())
            throw new CategoryNotFoundException("Categoria não existe.");
        Category existing=fetched.get();
        CategoryResponse response = new CategoryResponse();
        response.setName(existing.getName());
        response.setId(existing.getId());
        response.setParentId(existing.getParent_id());
        response.setPictureUrl(getBasePictureUrl() + existing.getPicture_uuid());
        return response;
    }

    public Page<CategoryResponse> findAll(Pageable pageable){
        return null;
    }

    public void deleteById(Long id){

    }

    public CategoryResponse createCategory(CategoryRequest request){
        return null;
    }

    public CategoryResponse updateCategory(Long id, CategoryRequest request){
        return null;
    }
}

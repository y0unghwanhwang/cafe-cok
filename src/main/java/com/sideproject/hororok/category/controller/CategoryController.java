package com.sideproject.hororok.category.controller;


import com.sideproject.hororok.category.dto.CategoryKeywordDto;
import com.sideproject.hororok.category.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/home")
    @Operation(summary = "홈 화면에 보여줄 정보를 제공")
    public ResponseEntity<CategoryKeywordDto> home() {

        return ResponseEntity.ok(categoryService.findAllCategoryAndKeyword());
    }

}
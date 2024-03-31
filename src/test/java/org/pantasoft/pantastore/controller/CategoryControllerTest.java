package org.pantasoft.pantastore.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pantasoft.pantastore.UnityTestUtils;
import org.pantasoft.pantastore.controller.dto.CategoryRequest;
import org.pantasoft.pantastore.controller.dto.CategoryResponse;
import org.pantasoft.pantastore.model.CategoryEntity;
import org.pantasoft.pantastore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerTest extends UnityTestUtils {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryController categoryController;

    @MockBean
    private CategoryService categoryService;

    private MockMvc mvc;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void findCategories_EmptyStore_ReturnsEmptyList() throws Exception {

        when(categoryService.findCategories()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", empty()));
    }

    @Test
    void findCategories_NonEmptyStore_ReturnsCategories() throws Exception {

        List<CategoryEntity> categories = givenListOf(CategoryEntity.class, 1);

        when(categoryService.findCategories()).thenReturn(categories);

        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(categories.get(0).getCategoryId().toString())))
                .andExpect(jsonPath("$[0].name", is(categories.get(0).getName())));
    }

    @Test
    void createCategory_ValidCategory_ReturnsCategory() throws Exception {
        var categoryId = givenUUID();
        var categoryRequest = givenOneObjectOf(CategoryRequest.class);
        var categoriesEntity = givenOneObjectOf(CategoryEntity.class);

        when(categoryService.createCategory(eq(categoryRequest)))
                .thenReturn(categoriesEntity);

        mockMvc.perform(post("/categories")
                        .contentType(APPLICATION_JSON)
                        .content(asJsonString(categoryRequest)))
                .andExpect(status().isOk());

        verify(categoryService).createCategory(eq(categoryRequest));
    }

    @Test
    void createCategory_InvalidCategory_ReturnsBadRequest() throws Exception {
        var categoryRequest = givenOneObjectOf(CategoryRequest.class);
        categoryRequest.setName(null);

        mockMvc.perform(post("/categories")
                        .contentType(APPLICATION_JSON)
                        .content(asJsonString(categoryRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void editCategory_ValidCategory_ReturnsCategory() throws Exception {
        var categoryRequest = givenOneObjectOf(CategoryRequest.class);
        var categoryResponse = givenOneObjectOf(CategoryResponse.class);

        when(categoryService.editCategory(eq(categoryRequest.getId()), eq(categoryRequest)))
                .thenReturn(categoryResponse);

        mockMvc.perform(post("/categories/{id}", categoryRequest.getId().toString())
                        .contentType(APPLICATION_JSON)
                        .content(asJsonString(categoryRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCategory_ValidCategory_ReturnsCategory() throws Exception {
        var categoryId = givenUUID();
        var categoryRequest = givenOneObjectOf(CategoryRequest.class);
        var categoriesEntity = givenOneObjectOf(CategoryEntity.class);

        doNothing().when(categoryService).deleteCategory(categoryId);

        mockMvc.perform(delete("/categories/{id}", categoryId.toString())
                        .contentType(APPLICATION_JSON)
                        .content(asJsonString(categoryRequest)))
                .andExpect(status().isNoContent());
    }
}
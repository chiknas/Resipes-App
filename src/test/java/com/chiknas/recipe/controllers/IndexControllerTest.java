package com.chiknas.recipe.controllers;

import com.chiknas.recipe.model.Recipe;
import com.chiknas.recipe.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Index controller unit tests.
 *
 * @author NikolaosK
 * @since 18/03/2020
 */
class IndexControllerTest {

    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    IndexController indexController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    void testMVC() throws Exception {
        MockMvc mvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void getIndex() {
        Set<Recipe> recipeSet = new HashSet<>();
        recipeSet.add(new Recipe());
        when(recipeService.getRecipes()).thenReturn(recipeSet);
        ArgumentCaptor<Set<Recipe>> recipeCaptor = ArgumentCaptor.forClass(Set.class);

        String index = indexController.getIndex(model);

        assertEquals("index", index);
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(any(), recipeCaptor.capture());
        assertEquals(recipeCaptor.getValue().size(), 1);
    }
}
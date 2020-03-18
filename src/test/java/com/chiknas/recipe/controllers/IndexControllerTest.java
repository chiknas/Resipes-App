package com.chiknas.recipe.controllers;

import com.chiknas.recipe.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    void getIndex() {
        String index = indexController.getIndex(model);

        assertEquals("index", index);

        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(any(), anySet());
    }
}
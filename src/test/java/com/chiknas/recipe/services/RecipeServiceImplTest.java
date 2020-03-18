package com.chiknas.recipe.services;

import com.chiknas.recipe.model.Recipe;
import com.chiknas.recipe.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Functionality unit testing for RecipeService class.
 *
 * @author NikolaosK
 * @since 18/03/2020
 */
class RecipeServiceImplTest {

    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void getRecipes() {
        Recipe recipe = new Recipe();
        recipe.setDescription("emptyrecipe");
        List<Recipe> results = new ArrayList<>();
        results.add(recipe);

        when(recipeRepository.findAll()).thenReturn(results);
        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }
}
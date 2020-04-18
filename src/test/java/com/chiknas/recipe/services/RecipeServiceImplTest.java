package com.chiknas.recipe.services;

import com.chiknas.recipe.model.Recipe;
import com.chiknas.recipe.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    Recipe recipe;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);

        recipe = new Recipe();
        recipe.setDescription("emptyrecipe");
    }

    @Test
    void getRecipes() {
        List<Recipe> results = new ArrayList<>();
        results.add(recipe);

        when(recipeRepository.findAll()).thenReturn(results);
        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void findById(){
        when(recipeRepository.findById(any())).thenReturn(Optional.ofNullable(recipe));
        Recipe byId = recipeService.findById(1L);
        assertEquals(byId.getDescription(), recipe.getDescription());
        verify(recipeRepository, times(1)).findById(any());

    }
}
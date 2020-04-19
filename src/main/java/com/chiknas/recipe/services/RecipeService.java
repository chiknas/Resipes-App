package com.chiknas.recipe.services;

import com.chiknas.recipe.model.Recipe;

import java.util.Set;

/**
 * com.chiknas.recipe.services.RecipeService, created on 13/12/2019 15:26 <p>
 * @author NikolaosK
 */
public interface RecipeService {
  Set<Recipe> getRecipes();
  Recipe findById(Long id);
  Recipe saveRecipe(Recipe recipe);
  void deleteRecipe(Long id);
}

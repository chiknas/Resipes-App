package com.chiknas.recipe.services;

import com.chiknas.recipe.model.Recipe;
import com.chiknas.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * com.chiknas.recipe.services.RecipeServiceImpl, created on 13/12/2019 15:27 <p>
 * @author NikolaosK
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

  private final RecipeRepository recipeRepository;

  @Autowired
  public RecipeServiceImpl(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  @Override
  public Set<Recipe> getRecipes() {
    Set<Recipe> recipeSet = new HashSet<>();
    recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
    return recipeSet;
  }

  @Override
  public Recipe findById(Long id) {
    return recipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Recipe was not found"));
  }

  @Override
  public Recipe saveRecipe(Recipe recipe){
    return recipeRepository.save(recipe);
  }

  @Override
  public void deleteRecipe(Long id) {
    recipeRepository.deleteById(id);
  }
}

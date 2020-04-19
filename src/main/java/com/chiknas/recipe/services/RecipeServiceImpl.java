package com.chiknas.recipe.services;

import com.chiknas.recipe.model.Ingredient;
import com.chiknas.recipe.model.Recipe;
import com.chiknas.recipe.repositories.IngredientRepository;
import com.chiknas.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
  private final IngredientRepository ingredientRepository;

  @Autowired
  public RecipeServiceImpl(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
    this.recipeRepository = recipeRepository;
    this.ingredientRepository = ingredientRepository;
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

  @Transactional
  @Override
  public Ingredient saveIngredient(Long recipeId, Ingredient ingredient) {
    ingredient.setRecipe(findById(recipeId));
    return ingredientRepository.save(ingredient);
  }

  @Override
  public void deleteRecipe(Long id) {
    recipeRepository.deleteById(id);
  }

  @Override
  public Ingredient findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
    Recipe recipe = recipeRepository.findById(recipeId)
            .orElseThrow(() -> new RuntimeException("The recipe this ingredient is linked to was not found!"));

    return recipe.getIngredients().stream()
            .filter(ingredient -> ingredientId.equals(ingredient.getId()))
            .findFirst().orElseThrow(() -> new RuntimeException("The ingredient for this recipe was not found!"));
  }
}

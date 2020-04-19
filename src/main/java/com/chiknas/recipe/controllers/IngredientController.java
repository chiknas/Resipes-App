package com.chiknas.recipe.controllers;

import com.chiknas.recipe.model.Ingredient;
import com.chiknas.recipe.model.Recipe;
import com.chiknas.recipe.services.RecipeService;
import com.chiknas.recipe.services.UomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final UomService uomService;

    @Autowired
    public IngredientController(RecipeService recipeService, UomService uomService) {
        this.recipeService = recipeService;
        this.uomService = uomService;
    }

    @GetMapping("/recipe/{id}/ingredients")
    public String listIngredients(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredient(@PathVariable Long recipeId,
                                 @PathVariable Long ingredientId,
                                 Model model) {
        model.addAttribute("ingredient", recipeService.findByRecipeIdAndIngredientId(recipeId, ingredientId));
        return "recipe/ingredient/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateIngredient(@PathVariable Long recipeId,
                                   @PathVariable Long ingredientId,
                                   Model model) {
        model.addAttribute("ingredient", recipeService.findByRecipeIdAndIngredientId(recipeId, ingredientId));
        model.addAttribute("uomList", uomService.findAllUoms());
        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("/recipe/{recipeId}/ingredient/save")
    public String saveUpdateIngredient(@PathVariable Long recipeId, @ModelAttribute Ingredient ingredient) {
        Ingredient savedIngredient = recipeService.saveIngredient(recipeId, ingredient);
        return "redirect:/recipe/" + savedIngredient.getRecipe().getId() + "/ingredient/"+ savedIngredient.getId() +"/show";
    }
}

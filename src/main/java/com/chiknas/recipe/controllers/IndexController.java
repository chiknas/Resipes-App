package com.chiknas.recipe.controllers;

import com.chiknas.recipe.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author nkukn - created on 14/11/2019 8:03 μ.μ.
 */
@Controller
public class IndexController {

    private final RecipeService recipeService;

    @Autowired
    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"", "/", "/index"})
    public String getIndex(Model model){
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}

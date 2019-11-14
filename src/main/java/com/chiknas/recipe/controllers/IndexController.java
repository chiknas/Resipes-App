package com.chiknas.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author nkukn - created on 14/11/2019 8:03 μ.μ.
 */
@Controller
public class IndexController {

    @GetMapping({"", "/", "/index"})
    public String getIndex(Model model){
        return "index";
    }
}

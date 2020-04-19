package com.chiknas.recipe.services;

import com.chiknas.recipe.model.UnitOfMeasure;

import java.util.List;

public interface UomService {
    List<UnitOfMeasure> findAllUoms();
}

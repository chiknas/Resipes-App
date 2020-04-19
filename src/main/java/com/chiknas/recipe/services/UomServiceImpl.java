package com.chiknas.recipe.services;

import com.chiknas.recipe.model.UnitOfMeasure;
import com.chiknas.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UomServiceImpl implements UomService {

    private final UnitOfMeasureRepository uomRepo;

    @Autowired
    public UomServiceImpl(UnitOfMeasureRepository uomRepo) {
        this.uomRepo = uomRepo;
    }

    @Override
    public List<UnitOfMeasure> findAllUoms() {
        return uomRepo.findAll();
    }
}

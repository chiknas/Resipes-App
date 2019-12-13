package com.chiknas.recipe.repositories;

import com.chiknas.recipe.model.UnitOfMeasure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * created by NikosK on 12/1/2019
 */
public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, Long> {
  Optional<UnitOfMeasure> findByDescription(String description);
}

package com.chiknas.recipe.repositories;

import com.chiknas.recipe.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * created by NikosK on 12/1/2019
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
  Optional<Category> findByDescription(String description);
}

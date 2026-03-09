package com.gerenciamento.food_gerent.domain.ingredientes;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IngredienteRepository {
  List<Ingrediente> findAll();

  Optional<Ingrediente> findById(UUID id);

  Ingrediente save(Ingrediente ingrediente);

  void delete(Ingrediente entity);
}

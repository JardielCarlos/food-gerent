package com.gerenciamento.food_gerent.domain.ingredientes.categoriasIngredientes;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoriaRepository {
  List<CategoriaIngrediente> findAll();

  Optional<CategoriaIngrediente>  findById(UUID id);

  CategoriaIngrediente save(CategoriaIngrediente categoria);

  void deleteById(UUID id); 
}

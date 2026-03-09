package com.gerenciamento.food_gerent.domain.ingredientes.tagsIngredientes;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TagIngredienteRepository {
  public List<TagIngrediente> findAll();

  public Optional<TagIngrediente> findById(UUID id);
  
  public TagIngrediente save(TagIngrediente tag);

  public void deleteById(UUID id);

  public List<TagIngrediente> findAllById(List<UUID> tagsIds);
} 

package com.gerenciamento.food_gerent.application.usecases;

import java.util.List;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.ingredientes.tagsIngredientes.TagIngredientePatchDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.tagsIngredientes.TagIngredienteRequestDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.tagsIngredientes.TagIngredienteResponseDTO;

public interface TagIngredienteUseCases {
  public List<TagIngredienteResponseDTO> getAllTags();

  public TagIngredienteResponseDTO getTagById(UUID id);

  public TagIngredienteResponseDTO createTag(TagIngredienteRequestDTO data);

  public TagIngredienteResponseDTO updateTag(UUID id, TagIngredientePatchDTO data);

  public void deleteTag(UUID id);
}

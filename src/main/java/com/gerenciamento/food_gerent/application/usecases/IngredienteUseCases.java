package com.gerenciamento.food_gerent.application.usecases;

import java.util.List;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.ingredientes.IngredientePatchtDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.IngredienteRequestDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.IngredienteResponseDTO;

public interface IngredienteUseCases {
  
  public List<IngredienteResponseDTO> getAllIngredientes();

  public IngredienteResponseDTO getIngredienteById(UUID id);

  public IngredienteResponseDTO createIngrediente(IngredienteRequestDTO ingredienteDTO);

  public IngredienteResponseDTO updateIngrediente(UUID id, IngredientePatchtDTO ingredienteDTO);

  public void deleteIngrediente(UUID id);

}

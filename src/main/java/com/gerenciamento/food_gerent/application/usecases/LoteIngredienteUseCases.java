package com.gerenciamento.food_gerent.application.usecases;

import java.util.List;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.ingredientes.lotesIngredientes.LoteIngredientePatchDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.lotesIngredientes.LoteIngredienteRequestDTO;
import com.gerenciamento.food_gerent.domain.ingredientes.lotesIngredientes.LoteIngredienteResponseDTO;

public interface LoteIngredienteUseCases {
  
  public List<LoteIngredienteResponseDTO> findAllLotesIngredientes();

  public LoteIngredienteResponseDTO findLoteIngredienteById(UUID id);

  public LoteIngredienteResponseDTO createLoteIngrediente(LoteIngredienteRequestDTO data);

  public LoteIngredienteResponseDTO updateLoteIngrediente(UUID id, LoteIngredientePatchDTO data);

  public void deleteLoteIngrediente(UUID id);

}

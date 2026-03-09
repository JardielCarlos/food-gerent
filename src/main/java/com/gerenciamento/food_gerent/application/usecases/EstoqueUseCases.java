package com.gerenciamento.food_gerent.application.usecases;

import java.util.List;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.estoques.EstoquePatchDTO;
import com.gerenciamento.food_gerent.domain.estoques.EstoqueRequestDTO;
import com.gerenciamento.food_gerent.domain.estoques.EstoqueResponseDTO;

public interface EstoqueUseCases {
  public List<EstoqueResponseDTO> findAll();

  public EstoqueResponseDTO findById(UUID id);

  public EstoqueResponseDTO save(EstoqueRequestDTO estoque);

  public EstoqueResponseDTO update(UUID id, EstoquePatchDTO estoque);

  public void deleteById(UUID id);
}

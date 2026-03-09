package com.gerenciamento.food_gerent.application.usecases;

import java.util.List;
import java.util.UUID;

import com.gerenciamento.food_gerent.domain.estoques.EstoquePatchDTO;
import com.gerenciamento.food_gerent.domain.estoques.EstoqueRequestDTO;
import com.gerenciamento.food_gerent.domain.estoques.EstoqueResponseDTO;
import com.gerenciamento.food_gerent.domain.estoques.itensEstoque.ItemEstoqueRequestDTO;
import com.gerenciamento.food_gerent.domain.estoques.itensEstoque.ItemEstoqueResponseDTO;
import com.gerenciamento.food_gerent.domain.estoques.itensEstoque.RetiradaEstoqueRequestDTO;

public interface EstoqueUseCases {
  public List<EstoqueResponseDTO> findAll();

  public EstoqueResponseDTO findById(UUID id);

  public EstoqueResponseDTO adicionarItem(UUID estoqueID, ItemEstoqueRequestDTO item);

  public EstoqueResponseDTO adicionarLote(UUID estoqueID, UUID loteID);

  public EstoqueResponseDTO registrarSaida(UUID estoqueID, RetiradaEstoqueRequestDTO retirada);

  public List<ItemEstoqueResponseDTO> consultarVencidos(UUID estoqueID);

  public List<ItemEstoqueResponseDTO> consultarProximosAVencer(UUID estoqueID, int dias);

  public EstoqueResponseDTO save(EstoqueRequestDTO estoque);

  public EstoqueResponseDTO update(UUID id, EstoquePatchDTO estoque);

  public void deleteById(UUID id);
}

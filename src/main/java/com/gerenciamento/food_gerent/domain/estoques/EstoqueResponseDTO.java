package com.gerenciamento.food_gerent.domain.estoques;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gerenciamento.food_gerent.domain.estoques.itensEstoque.ItemEstoqueResponseDTO;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;
import com.gerenciamento.food_gerent.utils.enumerated.EnumTipoEstoque;

public record EstoqueResponseDTO(
  UUID id,
  UUID lojaID,
  String nomeLoja,
  String nomeEstoque,
  EnumTipoEstoque tipoEstoque,
  List<ItemEstoqueResponseDTO> itens,
  EnumStatus status,
  @JsonFormat(pattern = "dd/MM/yyyy")
  LocalDate dataCriacao, 
  @JsonFormat(pattern = "dd/MM/yyyy")
  LocalDate dataAtualizacao

) {
  
}

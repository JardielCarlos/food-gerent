package com.gerenciamento.food_gerent.domain.estoques;

import java.util.UUID;

import com.gerenciamento.food_gerent.utils.enumerated.EnumTipoEstoque;

import jakarta.validation.constraints.NotNull;

public record EstoqueRequestDTO(

  @NotNull(message = "O id do ingrediente é obrigatório")
  UUID lojaID,

  @NotNull(message = "O campo nomeEstoque é obrigatório.")
  String nomeEstoque,
  
  @NotNull(message = "O campo tipoEstoque é obrigatório.")
  EnumTipoEstoque tipoEstoque
) {
  
}

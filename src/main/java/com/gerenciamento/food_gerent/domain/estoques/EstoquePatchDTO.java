package com.gerenciamento.food_gerent.domain.estoques;

import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;
import com.gerenciamento.food_gerent.utils.enumerated.EnumTipoEstoque;

import jakarta.validation.constraints.Size;

public record EstoquePatchDTO(

  @Size(min = 3, message = "O campo nomeEstoque deve ter no mínimo 3 caracteres")
  String nomeEstoque,

  EnumTipoEstoque tipoEstoque,
  
  EnumStatus status
) {
  
}

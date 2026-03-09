package com.gerenciamento.food_gerent.domain.estoques.itensEstoque;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RetiradaEstoqueRequestDTO(
  @NotNull(message = "O id do ingrediente é obrigatório")
  UUID ingredienteID,

  @NotNull(message = "A quantidade é obrigatória")
  @Positive(message = "A quantidade deve ser maior que zero")
  BigDecimal quantidade
) {
}

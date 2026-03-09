package com.gerenciamento.food_gerent.domain.estoques.itensEstoque;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;
import com.gerenciamento.food_gerent.utils.enumerated.EnumUnidadeMedida;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemEstoqueRequestDTO(
  UUID loteID,

  @NotNull(message = "O id do ingrediente é obrigatório")
  UUID ingredienteID,

  @NotNull(message = "A quantidade é obrigatória")
  @Positive(message = "A quantidade deve ser maior que zero")
  BigDecimal quantidade,

  BigDecimal reservado,

  @NotNull(message = "A unidade de medida é obrigatória")
  EnumUnidadeMedida unidade,

  @NotNull(message = "A data de validade é obrigatória")
  @JsonFormat(pattern = "dd/MM/yyyy")
  LocalDate dataValidade,

  EnumStatus status
) {
}

package com.gerenciamento.food_gerent.domain.estoques.itensEstoque;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;
import com.gerenciamento.food_gerent.utils.enumerated.EnumUnidadeMedida;

public record ItemEstoqueResponseDTO(
  UUID id,
  UUID loteID,
  UUID ingredienteID,
  String nomeIngrediente,
  BigDecimal quantidade,
  BigDecimal reservado,
  EnumUnidadeMedida unidade,
  LocalDate dataValidade,
  EnumStatus status,
  LocalDate dataCriacao,
  LocalDate dataAtualizacao
) {
  
}

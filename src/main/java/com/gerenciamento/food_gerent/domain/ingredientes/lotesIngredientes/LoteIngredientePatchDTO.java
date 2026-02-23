package com.gerenciamento.food_gerent.domain.ingredientes.lotesIngredientes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;
import com.gerenciamento.food_gerent.utils.enumerated.EnumUnidadeMedida;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

public record LoteIngredientePatchDTO(

  UUID ingredienteId,

  @PastOrPresent(message = "A data de fabricação não pode estar no futuro")
  LocalDate dataFabricacao,

  LocalDate dataValidade,

  @Positive(message = "A quantidade inicial deve ser maior que zero")
  BigDecimal quantidadeInicial,

  @Positive(message = "A quantidade disponível deve ser maior que zero")
  BigDecimal quantidadeDisponivel,

  EnumUnidadeMedida unidadeMedida,

  @Positive(message = "O preço total deve ser maior que zero")
  BigDecimal precoTotal,


  EnumStatus status
) {
  @AssertTrue(message = "A data de validade deve ser posterior à data de fabricação") 
  private boolean isDataValidadePosteriorFabricacao() { 
    if (dataFabricacao == null || dataValidade == null) return true; 

    return dataValidade.isAfter(dataFabricacao); 
  } 

  @AssertTrue(message = "A quantidade disponível não pode ser maior que a quantidade inicial") 
  private boolean isQuantidadeDisponivelValida() { 
    if (quantidadeInicial == null || quantidadeDisponivel == null) return true; 
    return quantidadeDisponivel.compareTo(quantidadeInicial) <= 0; 
  }
}
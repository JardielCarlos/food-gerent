package com.gerenciamento.food_gerent.domain.ingredientes.lotesIngredientes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gerenciamento.food_gerent.utils.enumerated.EnumUnidadeMedida;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

public record LoteIngredienteRequestDTO(

  @NotNull(message = "O id do ingrediente é obrigatório")
  UUID ingredienteId,

  @NotNull(message = "A data de fabricação é obrigatória")
  @PastOrPresent(message = "A data de fabricação não pode estar no futuro")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  LocalDate dataFabricacao,

  @NotNull(message = "A data de validade é obrigatória")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  LocalDate dataValidade,

  @NotNull(message = "A quantidade inicial é obrigatória")
  @Positive(message = "A quantidade inicial deve ser maior que zero")
  BigDecimal quantidadeInicial,

  @NotNull(message = "A quantidade disponível é obrigatória")
  @Positive(message = "A quantidade disponível deve ser maior que zero")
  BigDecimal quantidadeDisponivel,

  @NotNull(message = "A unidade de medida é obrigatória")
  EnumUnidadeMedida unidadeMedida,

  @NotNull(message = "O preço total é obrigatório") 
  @Positive(message = "O preço total deve ser maior que zero") 
  BigDecimal precoTotal
) {
  
  @AssertTrue(message = "A data de validade deve ser posterior à data de fabricação") private boolean isDataValidadePosteriorFabricacao() { 
    if (dataFabricacao == null || dataValidade == null) return true; 

    return dataValidade.isAfter(dataFabricacao); 
  } 

  @AssertTrue(message = "A quantidade disponível não pode ser maior que a quantidade inicial") 
  private boolean isQuantidadeDisponivelValida() { 
    if (quantidadeInicial == null || quantidadeDisponivel == null) return true; 
    return quantidadeDisponivel.compareTo(quantidadeInicial) <= 0; 
  }
}

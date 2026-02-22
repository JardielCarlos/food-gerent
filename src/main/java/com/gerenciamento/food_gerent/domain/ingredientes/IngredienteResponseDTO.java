package com.gerenciamento.food_gerent.domain.ingredientes;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;
import com.gerenciamento.food_gerent.utils.enumerated.EnumUnidadeMedida;

public record IngredienteResponseDTO(
    UUID id,
    String nome,
    EnumUnidadeMedida unidadeMedida,
    String categoriaNome,
    Set<String> tagsNomes,
    EnumStatus status,
    LocalDate dataCriacao,
    LocalDate dataAtualizacao
) {
  
}

package com.gerenciamento.food_gerent.domain.ingredientes;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;
import com.gerenciamento.food_gerent.utils.enumerated.EnumUnidadeMedida;

public record IngredienteResponseDTO(
    UUID id,
    String nome,
    EnumUnidadeMedida unidadeMedida,
    String categoriaNome,
    Set<String> tagsNomes,
    EnumStatus status,

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataCriacao,
    
    @JsonFormat(pattern = "dd/MM/yyyy") 
    LocalDate dataAtualizacao
) {
  
}

package com.gerenciamento.food_gerent.domain.ingredientes.tagsIngredientes;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

public record TagIngredienteResponseDTO(
  UUID id,
  String nome,
  EnumStatus status,

  @JsonFormat(pattern = "dd/MM/yyyy")
  String dataCriacao,
  
  @JsonFormat(pattern = "dd/MM/yyyy")
  String dataAtualizacao
) {
  
}

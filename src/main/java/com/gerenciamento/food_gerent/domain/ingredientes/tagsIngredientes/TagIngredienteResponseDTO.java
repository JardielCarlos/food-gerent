package com.gerenciamento.food_gerent.domain.ingredientes.tagsIngredientes;

import java.util.UUID;

import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

public record TagIngredienteResponseDTO(
  UUID id,
  String nome,
  EnumStatus status,
  String dataCriacao,
  String dataAtualizacao
) {
  
}

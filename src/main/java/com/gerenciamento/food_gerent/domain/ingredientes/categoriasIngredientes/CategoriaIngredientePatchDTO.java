package com.gerenciamento.food_gerent.domain.ingredientes.categoriasIngredientes;

import java.util.Optional;
import java.util.UUID;

import com.gerenciamento.food_gerent.utils.enumerated.EnumStatus;

public record CategoriaIngredientePatchDTO(
  Optional<String> nome,
  Optional<UUID> parentId,
  Optional<EnumStatus> status
) {
}
